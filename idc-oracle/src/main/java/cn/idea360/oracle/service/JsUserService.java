package cn.idea360.oracle.service;

import cn.idea360.oracle.model.JsUser;

import java.util.List;
import java.util.Map;

public interface JsUserService {

    List<JsUser> listByMap(Map<String, Object> params);

    List<JsUser> listByIds(List<String> ids);
}
