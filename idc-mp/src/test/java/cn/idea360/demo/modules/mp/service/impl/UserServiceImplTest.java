package cn.idea360.demo.modules.mp.service.impl;

import cn.idea360.demo.modules.mp.entity.User;
import cn.idea360.demo.modules.mp.service.UserService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    /**
     * 模拟内存中存在重复数据
     *
     * 2020-05-19 15:18:10.468 ERROR 6612 --- [           main] c.i.d.m.mp.service.impl.UserServiceImpl  : 内存中name=张三的用户已存在
     * 2020-05-19 15:18:10.475  WARN 6612 --- [           main] c.i.d.m.mp.service.impl.UserServiceImpl  : SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@304e1e4e] was not registered for synchronization because DataSource is not transactional
     * 2020-05-19 15:18:10.533  INFO 6612 --- [           main] com.alibaba.druid.pool.DruidDataSource   : {dataSource-1} inited
     * 2020-05-19 15:18:10.794  INFO 6612 --- [           main] c.i.d.m.m.s.impl.UserServiceImplTest     : {"success":[{"id":1,"name":"张三"}],"failure":[{"name":"张三"}]}
     */
    @Test
    void importBatch1() {
        User user1 = new User("张三");
        User user2 = new User("张三");
        List<User> userList = Arrays.asList(user1, user2);
        JSONObject result = userService.importBatch(userList);
        log.info(result.toJSONString());
    }

    /**
     * 模拟Redis中存在重复数据
     *
     * 2020-05-19 15:18:40.700 ERROR 13352 --- [           main] c.i.d.m.mp.service.impl.UserServiceImpl  : Redis中name=张三的用户已存在
     * 2020-05-19 15:18:40.708  WARN 13352 --- [           main] c.i.d.m.mp.service.impl.UserServiceImpl  : SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@60251ddb] was not registered for synchronization because DataSource is not transactional
     * 2020-05-19 15:18:40.768  INFO 13352 --- [           main] com.alibaba.druid.pool.DruidDataSource   : {dataSource-1} inited
     * 2020-05-19 15:18:41.043  INFO 13352 --- [           main] c.i.d.m.m.s.impl.UserServiceImplTest     : {"success":[{"id":2,"name":"李四"}],"failure":[{"name":"张三"}]}
     */
    @Test
    void importBatch2() {
        User user1 = new User("张三");
        User user2 = new User("李四");
        List<User> userList = Arrays.asList(user1, user2);
        JSONObject result = userService.importBatch(userList);
        log.info(result.toJSONString());
    }

    /**
     * 手动在MySQL中添加1条数据, 模拟MySQL中存在重复数据
     *
     * 2020-05-19 15:19:22.337 ERROR 14128 --- [           main] c.i.d.m.mp.service.impl.UserServiceImpl  : Redis中name=张三的用户已存在
     * 2020-05-19 15:19:22.339 ERROR 14128 --- [           main] c.i.d.m.mp.service.impl.UserServiceImpl  : Redis中name=李四的用户已存在
     * 2020-05-19 15:19:22.347  WARN 14128 --- [           main] c.i.d.m.mp.service.impl.UserServiceImpl  : SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@69fe8c75] was not registered for synchronization because DataSource is not transactional
     * 2020-05-19 15:19:22.405  INFO 14128 --- [           main] com.alibaba.druid.pool.DruidDataSource   : {dataSource-1} inited
     * 2020-05-19 15:19:22.609 ERROR 14128 --- [           main] c.i.d.m.mp.service.impl.UserServiceImpl  : MySQL写入冲突:cn.idea360.demo.modules.mp.mapper.UserMapper.insert (batch index #1) failed. Cause: java.sql.BatchUpdateException: Duplicate entry '王五' for key 'name'
     * ; Duplicate entry '王五' for key 'name'; nested exception is java.sql.BatchUpdateException: Duplicate entry '王五' for key 'name'
     * 2020-05-19 15:19:22.609 ERROR 14128 --- [           main] c.i.d.m.mp.service.impl.UserServiceImpl  : MySQL中name=王五的用户已存在
     * 2020-05-19 15:19:22.697  INFO 14128 --- [           main] c.i.d.m.m.s.impl.UserServiceImplTest     : {"success":[],"failure":[{"name":"张三"},{"name":"李四"},{"name":"王五"}]}
     */
    @Test
    void importBatch3() {
        User user1 = new User("张三");
        User user2 = new User("李四");
        User user3 = new User("王五");
        List<User> userList = Arrays.asList(user1, user2, user3);
        JSONObject result = userService.importBatch(userList);
        log.info(result.toJSONString());
    }
}