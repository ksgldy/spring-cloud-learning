package cn.idea360.oracle.dao;

import cn.idea360.oracle.dto.AiRuleRankDTO;
import cn.idea360.oracle.dto.PageDTO;
import cn.idea360.oracle.model.AiRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class AiRuleMapperTest {

    @Autowired
    private AiRuleMapper aiRuleMapper;

    @Test
    public void insert() {
        AiRule aiRule = new AiRule();
        aiRule.setCompanyId(1);
        aiRule.setGroupId(77L);
        aiRule.setRuleName("rule1");
        aiRule.setState(1);
        aiRule.setCondition("");
        aiRule.setAction("");
        aiRule.setRank(1);
        aiRule.setCreater("1");
        aiRule.setUpdater("1");
        aiRule.setCreateTime(new Date());
        aiRule.setUpdateTime(new Date());

        aiRuleMapper.insert(aiRule);
    }

    @Test
    public void updateIgnoreNullById() {
        AiRule aiRule = new AiRule();
        aiRule.setId(1L);
        aiRule.setCompanyId(1);
        aiRule.setGroupId(77L);
        aiRule.setRuleName("rule1");
        aiRule.setState(1);
        aiRule.setCondition("");
        aiRule.setAction("");
        aiRule.setRank(1);
        aiRule.setCreater("1");
        aiRule.setUpdater("2");
        aiRule.setCreateTime(new Date());
        aiRule.setUpdateTime(new Date());
        aiRuleMapper.updateIgnoreNullById(aiRule);
    }

    @Test
    public void selectById() {
        AiRule aiRule = aiRuleMapper.selectById(2L);
        System.out.println(aiRule.toString());
    }

    @Test
    public void page() {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(1);
        pageDTO.setSize(10);

        List<AiRule> page = aiRuleMapper.page(pageDTO);
        System.out.println(page);
    }

    @Test
    public void del() {
        int i = aiRuleMapper.removeById(1L);
        System.out.println(i);
    }

    @Test
    public void updateRank() {
        AiRuleRankDTO aiRuleRankDTO = new AiRuleRankDTO();
        aiRuleRankDTO.setId(23L);
        aiRuleRankDTO.setRank(7);
        int i = aiRuleMapper.updateRank(Arrays.asList(aiRuleRankDTO));
        System.out.println(i);
    }
}