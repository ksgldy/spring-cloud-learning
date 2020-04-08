package cn.idea360.oracle.dao;

import cn.idea360.oracle.model.AiProjectGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class AiProjectGroupMapperTest {

    @Autowired
    private AiProjectGroupMapper aiProjectGroupMapper;

    @Test
    public void insert() {
        AiProjectGroup aiProjectGroup = new AiProjectGroup();
        aiProjectGroup.setCompanyId(123);
        aiProjectGroup.setGroupName("test-group-name-1");
        aiProjectGroup.setRank(22);
        aiProjectGroup.setCreateTime(new Date());
        aiProjectGroup.setUpdateTime(new Date());
        aiProjectGroup.setCreater("7");
        aiProjectGroup.setUpdater("7");
        aiProjectGroupMapper.insert(aiProjectGroup);
    }

    @Test
    public void selectById() {
        AiProjectGroup aiProjectGroup = aiProjectGroupMapper.selectById(1L);
        System.out.println(aiProjectGroup);
    }
}