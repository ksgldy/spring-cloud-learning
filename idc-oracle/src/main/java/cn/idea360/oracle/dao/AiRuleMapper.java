package cn.idea360.oracle.dao;

import cn.idea360.oracle.dto.PageDTO;
import cn.idea360.oracle.model.AiRule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AiRuleMapper {

    int insert(AiRule aiRule);

    int updateIgnoreNullById(@Param("aiRule") AiRule aiRule);

    AiRule selectById(Long id);

    int removeById(Long id);

    List<AiRule> page(@Param("pageDTO") PageDTO pageDTO);

//    int updateRank(@Param("idup") Long id1, @Param("iddown") Long id2);

}
