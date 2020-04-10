package cn.idea360.oracle.service.impl;

import cn.idea360.oracle.dao.JsUserMapper;
import cn.idea360.oracle.model.JsUser;
import cn.idea360.oracle.service.JsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JsUserServiceImpl implements JsUserService {

    @Autowired
    private JsUserMapper jsUserMapper;

    @Override
    public List<JsUser> listByMap(Map<String, Object> params) {
        return jsUserMapper.listByMap(params);
    }

    @Override
    public List<JsUser> listByIds(List<String> ids) {
        return jsUserMapper.listByIds(ids);
    }
}
