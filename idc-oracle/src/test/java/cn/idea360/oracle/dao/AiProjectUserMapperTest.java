package cn.idea360.oracle.dao;

import cn.idea360.oracle.model.AiProjectUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
}