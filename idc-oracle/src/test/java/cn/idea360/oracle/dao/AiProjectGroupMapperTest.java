package cn.idea360.oracle.dao;

import cn.idea360.oracle.dto.PageDTO;
import cn.idea360.oracle.model.AiProjectGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class AiProjectGroupMapperTest {

    @Autowired
    private AiProjectGroupMapper aiProjectGroupMapper;

    @Test
    public void insert() throws Exception {
        AiProjectGroup aiProjectGroup = new AiProjectGroup();
        aiProjectGroup.setCompanyId(123);
        aiProjectGroup.setGroupName("test-group-name-1");
        aiProjectGroup.setRank(22);
        aiProjectGroup.setCreateTime(new Date());
        aiProjectGroup.setUpdateTime(new Date());
        aiProjectGroup.setCreater("7");
        aiProjectGroup.setUpdater("7");
        aiProjectGroupMapper.insert(aiProjectGroup);
        System.out.println(aiProjectGroup.getId());
    }

    @Test
    public void updateIgnoreNullById() throws Exception {
        AiProjectGroup aiProjectGroup = new AiProjectGroup();
        aiProjectGroup.setId(74L);
        aiProjectGroup.setGroupName("update-group-name");
        aiProjectGroup.setUpdater("1");
        aiProjectGroup.setUpdateTime(new Date());
        aiProjectGroupMapper.updateIgnoreNullById(aiProjectGroup);
    }

    @Test
    public void deleteById() throws Exception {
        aiProjectGroupMapper.deleteById(28L);
    }

    @Test
    public void selectById() throws Exception {
        AiProjectGroup aiProjectGroup = aiProjectGroupMapper.selectById(1L);
        System.out.println(aiProjectGroup);
    }

    @Test
    public void page() throws Exception {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setBeginResult(1);
        pageDTO.setEndResult(2);
//        pageDTO.setKeyword("a2");
        List<AiProjectGroup> page = aiProjectGroupMapper.page(pageDTO);
        System.out.println(page);
    }

    @Test
    public void selectLastestRank() throws Exception{
        long l = aiProjectGroupMapper.selectLastestRank();
        System.out.println(l);
    }


}