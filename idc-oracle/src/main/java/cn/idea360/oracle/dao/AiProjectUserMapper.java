package cn.idea360.oracle.dao;

import cn.idea360.oracle.model.AiProjectUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AiProjectUserMapper {

    void insertBatch(@Param("aiProjectUserList") List<AiProjectUser> aiProjectUserList) throws Exception;

    int deleteByMap(@Param("cm") Map<String, Object> columnMap) throws Exception;

    List<AiProjectUser> listByCustomerIds(@Param("cids") List<String> customerIds) throws Exception;

    List<AiProjectUser> listByGroupId(@Param("groupId") Long groupId) throws Exception;

}
