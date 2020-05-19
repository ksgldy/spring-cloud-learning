package cn.idea360.demo.modules.mp.service;

import cn.idea360.demo.modules.mp.entity.User;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 当我遇上你
 * @since 2020-05-19
 */
public interface UserService extends IService<User> {

    /**
     * 批量导入数据，数据去重操作
     * @param list
     * @return 成功数据和失败数据
     */
    JSONObject importBatch(List<User> list);
}
