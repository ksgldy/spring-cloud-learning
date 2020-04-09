package cn.idea360.oracle.dao;

import cn.idea360.oracle.dto.PageDTO;
import cn.idea360.oracle.model.AiProjectGroup;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface AiProjectGroupMapper {

    int insert(AiProjectGroup aiProjectGroup);

    int updateIgnoreNullById(@Param("aiProjectGroup") AiProjectGroup aiProjectGroup);

    AiProjectGroup selectById(@Param("id") long id);

    List<AiProjectGroup> listByCustomerIds(@Param("cids") Collection<String> customerIds);

    List<AiProjectGroup> page(@Param("pageDTO") PageDTO pageDTO);

    int deleteById(@Param("id") long id);

    int selectLastestRank();

    int totalRecord();

}
