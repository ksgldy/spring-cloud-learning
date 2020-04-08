package cn.idea360.oracle.dao;

import cn.idea360.oracle.dto.PageDTO;
import cn.idea360.oracle.model.AiProjectGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AiProjectGroupMapper {

    int insert(AiProjectGroup aiProjectGroup) throws Exception;

    int updateIgnoreNullById(@Param("aiProjectGroup") AiProjectGroup aiProjectGroup) throws Exception;

    AiProjectGroup selectById(@Param("id") long id) throws Exception;

    List<AiProjectGroup> page(@Param("pageDTO") PageDTO pageDTO) throws Exception;

    int deleteById(@Param("id") long id) throws Exception;

}
