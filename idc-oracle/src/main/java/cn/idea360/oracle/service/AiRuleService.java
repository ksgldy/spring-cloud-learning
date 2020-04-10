package cn.idea360.oracle.service;

import cn.idea360.oracle.dto.AiRuleRankDTO;
import cn.idea360.oracle.dto.PageDTO;
import cn.idea360.oracle.dto.PageRespDTO;
import cn.idea360.oracle.model.AiRule;

import java.util.List;

public interface AiRuleService {

    boolean insert(AiRule aiRule);

    boolean updateIgnoreNullById(AiRule aiRule);

    AiRule selectById(Long id);

    boolean removeById(Long id);

    PageRespDTO page(PageDTO pageDTO);

    boolean updateRank(List<AiRuleRankDTO> arrList);

}
