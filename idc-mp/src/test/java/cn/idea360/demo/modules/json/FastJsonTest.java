package cn.idea360.demo.modules.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class FastJsonTest {

    /**
     * {"roles":[{"roleName":"系统管理员"},{"roleName":"普通管理员"}],"userName":"admin"}
     */
    @Test
    void toJson() {
        Role role1 = new Role("系统管理员");
        Role role2 = new Role("普通管理员");
        User user = new User("admin");
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

    @Test
    void json2Bean() {
        String json = "{\"roles\":[{\"roleName\":\"系统管理员\"},{\"roleName\":\"普通管理员\"}],\"userName\":\"admin\"}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        User user = JSONObject.toJavaObject(jsonObject, User.class);
        User user1 = JSON.parseObject(json, User.class);
        System.out.println(user);
    }


}
