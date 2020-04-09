package cn.idea360.oracle.dao;

import cn.idea360.oracle.model.JsUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class JsUserMapperTest {

    @Autowired
    JsUserMapper jsUserMapper;

    @Test
    public void listByMap() throws Exception {
        HashMap<String, Object> hashMap = new HashMap<>();

//        hashMap.put("departmentId", 100);
        hashMap.put("userId", "userId");
        hashMap.put("keyword", "00blm");
        List<JsUser> jsUsers = jsUserMapper.listByMap(hashMap);
        System.out.println(jsUsers.size());
    }

}