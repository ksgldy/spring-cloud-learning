package cn.idea360.oracle.service.impl;

import cn.idea360.oracle.dao.AiRuleMapper;
import cn.idea360.oracle.vo.AiRuleReqVO;
import cn.idea360.oracle.dto.PageDTO;
import cn.idea360.oracle.dto.PageRespDTO;
import cn.idea360.oracle.model.AiRule;
import cn.idea360.oracle.service.AiRuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AiRuleServiceImpl implements AiRuleService {

    @Autowired
    private AiRuleMapper aiRuleMapper;

    @Override
    public boolean insert(AiRule aiRule){
        if (aiRule.getId() != null) {
            aiRule.setId(null);
        }

        // todo 排序
        aiRule.setCreateTime(new Date());
        aiRule.setUpdateTime(new Date());
        aiRule.setRank(1);
        aiRule.setCreater(aiRule.getCreater());
        aiRule.setUpdater(aiRule.getUpdater());
        aiRuleMapper.insert(aiRule);
        return Boolean.TRUE;
    }

    @Override
    public boolean updateIgnoreNullById(AiRule aiRule) {
        if (aiRule.getId() == null || aiRule.getId() <= 0L) {
            return Boolean.FALSE;
        }
        AiRule savedAiRule = aiRuleMapper.selectById(aiRule.getId());
        if (savedAiRule == null) {
            return Boolean.FALSE;
        }

        BeanUtils.copyProperties(aiRule, savedAiRule);

        // todo 排序
        savedAiRule.setRank(1);
        savedAiRule.setUpdater(aiRule.getUpdater());
        savedAiRule.setUpdateTime(new Date());
        int i = aiRuleMapper.updateIgnoreNullById(savedAiRule);
        return i > 0? Boolean.TRUE: Boolean.FALSE;
    }

    @Override
    public AiRule selectById(Long id) {
        if (id == null || id <= 0) {
            return null;
        }
        // 数据不存在返回null
        AiRule aiRule = aiRuleMapper.selectById(id);
        return aiRule;
    }

    @Override
    public boolean removeById(Long id) {
        if (id == null || id <= 0L) {
            return Boolean.FALSE;
        }
        int i = aiRuleMapper.removeById(id);
        return i>0? Boolean.TRUE: Boolean.FALSE;
    }

    @Override
    public PageRespDTO page(PageDTO pageDTO) {
        List<AiRule> data = aiRuleMapper.page(pageDTO);

        PageRespDTO page = new PageRespDTO();
        page.setPageNum(pageDTO.getPage());
        page.setSize(pageDTO.getSize());
        page.setTotal(aiRuleMapper.totalRecord());
        page.setRecords(data);

        return page;
    }
}
