package cn.idea360.oracle.dao;

import cn.idea360.oracle.model.JsUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JsUserMapper {

    List<JsUser> listByMap(@Param("cm") Map<String, Object> columnMap) throws Exception;
}
