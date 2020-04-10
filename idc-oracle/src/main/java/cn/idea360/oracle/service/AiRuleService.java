package cn.idea360.oracle.service;

import cn.idea360.oracle.dto.PageDTO;
import cn.idea360.oracle.dto.PageRespDTO;
import cn.idea360.oracle.model.AiRule;

public interface AiRuleService {

    boolean insert(AiRule aiRule);

    boolean updateIgnoreNullById(AiRule aiRule);

    AiRule selectById(Long id);

    boolean removeById(Long id);

    PageRespDTO page(PageDTO pageDTO);

}
