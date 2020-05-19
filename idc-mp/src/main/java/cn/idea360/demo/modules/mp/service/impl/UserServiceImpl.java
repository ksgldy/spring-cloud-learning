package cn.idea360.demo.modules.mp.service.impl;

import cn.idea360.demo.common.utils.HashUtils;
import cn.idea360.demo.modules.mp.entity.User;
import cn.idea360.demo.modules.mp.mapper.UserMapper;
import cn.idea360.demo.modules.mp.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 当我遇上你
 * @since 2020-05-19
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 1. bitmap判断是否存在
     * 2. 内存中数据是否重复
     * 3. redis和mysql批量插入
     * 4. 数据库中是否插入失败
     * @param list
     * @return
     */
    @Override
    public JSONObject importBatch(List<User> list) {
        if (CollectionUtils.isEmpty(list)) {
            throw new NullPointerException("数据为空");
        }
        CopyOnWriteArrayList<User> importFailList = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<User> importSuccessList = new CopyOnWriteArrayList<>();

        list.stream().forEach(user -> {
            Boolean exist = redisTemplate.opsForValue().getBit("user", HashUtils.hash(user.getName()));
            if (exist) {
                log.error("Redis中name={}的用户已存在", user.getName());
                // 数据已存在，数据放入失败集合
                importFailList.add(user);
                return;
            }
            if (importSuccessList.contains(user)) {
                log.error("内存中name={}的用户已存在", user.getName());
                importFailList.add(user);
                return;
            }
            importSuccessList.add(user);
        });



        if (!CollectionUtils.isEmpty(importSuccessList)) {
            try {
                // 批量插入数据库
                this.saveBatch(importSuccessList);
            } catch (Exception e) {
                log.error("MySQL写入冲突:{}", e.getMessage());
                Iterator<User> iterator = importSuccessList.iterator();
                while (iterator.hasNext()) {
                    User user = iterator.next();
                    if (user.getId() == null) {
                        log.error("MySQL中name={}的用户已存在", user.getName());
                        importFailList.add(user);
                        importSuccessList.remove(user);
                    }
                }
            }
            // 将导入成功的数据批量写入bitmap
            redisTemplate.executePipelined(new RedisCallback<String>() {
                @Override
                public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    importSuccessList.stream().forEach(user -> {
                        redisConnection.setBit("user".getBytes(), HashUtils.hash(user.getName()), true);
                    });
                    return null;
                }
            });
        }

        JSONObject result = new JSONObject();
        result.put("success", importSuccessList);
        result.put("failure", importFailList);
        return result;
    }

}
