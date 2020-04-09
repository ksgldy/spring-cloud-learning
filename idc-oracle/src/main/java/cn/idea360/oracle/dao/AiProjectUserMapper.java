package cn.idea360.oracle.dao;

import cn.idea360.oracle.model.AiProjectUser;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface AiProjectUserMapper {

    void insertBatch(@Param("aiProjectUserList") Collection<AiProjectUser> aiProjectUserList);

    int deleteByMap(@Param("cm") Map<String, Object> columnMap);

    List<AiProjectUser> listByCustomerIds(@Param("cids") Collection<String> customerIds);

    List<AiProjectUser> listByGroupId(@Param("groupId") Long groupId);

    List<AiProjectUser> listByGroupIds(@Param("gids") Collection<Long> groupIds);

}
