package cn.idea360.oracle.dao;

import cn.idea360.oracle.model.AiRule;
import org.apache.ibatis.annotations.Param;

public interface AiRuleMapper {

    int insert(AiRule aiRule) throws Exception;

    int updateIgnoreNullById(@Param("aiRule") AiRule aiRule) throws Exception;


}
