package cn.idea360.demo.modules.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastJsonTest {

    /**
     * {"roles":[{"roleName":"系统管理员"},{"roleName":"普通管理员"}],"userName":"admin"}
     */
    @Test
    void toJson() {
        Role role1 = new Role("系统管理员");
        Role role2 = new Role("普通管理员");
        User user = new User();
        user.setUserName("admin");
        user.setRoles(Arrays.asList(role1, role2));

        String userJson = JSON.toJSONString(user);
        System.out.println(userJson);
    }

    /**
     * admin
     * {"roles":[{"roleName":"系统管理员"},{"roleName":"普通管理员"}],"userName":"admin"}
     */
    @Test
    void jsonParse() {
        String json = "{\"roles\":[{\"roleName\":\"系统管理员\"},{\"roleName\":\"普通管理员\"}],\"userName\":\"admin\"}";
        JSONObject jsonObject = JSON.parseObject(json);

        System.out.println(jsonObject.getString("userName"));
        System.out.println(jsonObject);
    }

    /**
     * [{"roleName":"系统管理员"},{"roleName":"普通管理员"}]
     */
    @Test
    void list2Json() {
        Role role1 = new Role("系统管理员");
        Role role2 = new Role("普通管理员");

        String jsonString = JSON.toJSONString(Arrays.asList(role1, role2));

        System.out.println(jsonString);
    }

    /**
     * [{"roleName":"系统管理员"},{"roleName":"普通管理员"}]
     */
    @Test
    void json2List() {
        String json = "[{\"roleName\":\"系统管理员\"},{\"roleName\":\"普通管理员\"}]";

        JSONArray jsonArray = JSON.parseArray(json);
        System.out.println(jsonArray);
    }

    /**
     * [Role(roleName=系统管理员), Role(roleName=普通管理员)]
     */
    @Test
    void json2List2() {
        String json = "[{\"roleName\":\"系统管理员\"},{\"roleName\":\"普通管理员\"}]";

        List<Role> users = JSON.parseArray(json, Role.class);
        System.out.println(users);
    }

    /**
     * User(userName=admin, roles=[Role(roleName=系统管理员), Role(roleName=普通管理员)])
     */
    @Test
    void json2Bean() {
        String json = "{\"roles\":[{\"roleName\":\"系统管理员\"},{\"roleName\":\"普通管理员\"}],\"userName\":\"admin\"}";

        User user = JSON.parseObject(json, User.class);
        System.out.println(user);
    }

    /**
     * User(userName=admin, roles=[Role(roleName=系统管理员), Role(roleName=普通管理员)])
     */
    @Test
    void json2Bean2() {
        String json = "{\"roles\":[{\"roleName\":\"系统管理员\"},{\"roleName\":\"普通管理员\"}],\"userName\":\"admin\"}";

        User user = JSON.parseObject(json, new TypeReference<User>(){});
        System.out.println(user);
    }

    /**
     * {"userName":"admin"}
     */
    @Test
    void map2Json() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userName", "admin");

        String jsonString = JSON.toJSONString(hashMap);
        System.out.println(jsonString);
    }

    /**
     * admin
     */
    @Test
    void json2Map() {
        String json = "{\"userName\":\"admin\"}";

        Map<String, String> map = (Map<String, String>) JSON.parse(json);
        System.out.println(map.get("userName"));
    }

    /**
     * {"userName":"admin"}
     */
    @Test
    void json2Map2() {
        String json = "{\"userName\":\"admin\"}";

        Map map = JSON.parseObject(json);
        System.out.println(map);
    }

    /**
     * {"groupName":"分组1","idNumber":{"001":{"roles":[{"roleName":"系统管理员"},{"roleName":"普通管理员"}],"userName":"admin"}}}
     */
    @Test
    void obj2Json() {
        Role role1 = new Role("系统管理员");
        Role role2 = new Role("普通管理员");
        User user = new User();
        user.setUserName("admin");
        user.setRoles(Arrays.asList(role1, role2));

        HashMap<String, User> map = new HashMap<>();
        map.put("001", user);
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupName("分组1");
        userGroup.setIdNumber(map);

        String jsonString = JSON.toJSONString(userGroup);
        System.out.println(jsonString);
    }

    /**
     * UserGroup(groupName=分组1, idNumber={001=User(userName=admin, roles=[Role(roleName=系统管理员), Role(roleName=普通管理员)])})
     */
    @Test
    void json2Obj() {
        String json = "{\"groupName\":\"分组1\",\"idNumber\":{\"001\":{\"roles\":[{\"roleName\":\"系统管理员\"},{\"roleName\":\"普通管理员\"}],\"userName\":\"admin\"}}}";

        UserGroup userGroup = JSON.parseObject(json, UserGroup.class);
        System.out.println(userGroup);
    }

}
