package cn.idea360.oracle.dao;

import cn.idea360.oracle.model.AiProjectGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AiProjectGroupMapper {

    int insert(AiProjectGroup aiProjectGroup);

    AiProjectGroup selectById(@Param("id") long id);

    List<AiProjectGroup> page() throws Exception;

}
