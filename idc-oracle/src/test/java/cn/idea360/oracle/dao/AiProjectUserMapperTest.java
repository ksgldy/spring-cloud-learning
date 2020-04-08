package cn.idea360.oracle.dao;

import cn.idea360.oracle.model.AiProjectUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class AiProjectUserMapperTest {

    @Autowired
    private AiProjectUserMapper aiProjectUserMapper;

    @Test
    public void insertBatch() throws Exception {

        ArrayList<AiProjectUser> list = new ArrayList<>();
        for (int i=0; i<5; i++) {
            AiProjectUser aiProjectUser = new AiProjectUser();
            aiProjectUser.setCompanyId(1);
            aiProjectUser.setGroupId(36L);
            aiProjectUser.setCustomerId(i+"");
            list.add(aiProjectUser);
        }

        aiProjectUserMapper.insertBatch(list);

    }

    @Test
    public void deleteByMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("groupId", 36L);
        aiProjectUserMapper.deleteByMap(map);
    }

    @Test
    public void listByCustomerIds() throws Exception {
        String[] ids = {"1"};
        List<String> cids = Arrays.asList(ids);
        List<AiProjectUser> aiProjectUsers = aiProjectUserMapper.listByCustomerIds(null);
        System.out.println(aiProjectUsers.size());
    }

    @Test
    public void listByGroupId() throws Exception {
        List<AiProjectUser> aiProjectUsers = aiProjectUserMapper.listByGroupId(36L);
        System.out.println(aiProjectUsers.size());
    }
}