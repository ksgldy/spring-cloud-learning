package cn.idea360.demo.modules.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author 当我遇上你
 * @公众号 当我遇上你
 * @since 2020-06-10
 */
class GsonTest {

    /**
     * {"userName":"admin","roles":[{"roleName":"系统管理员"},{"roleName":"普通管理员"}]}
     */
    @Test
    void toJson() {
        Role role1 = new Role("系统管理员");
        Role role2 = new Role("普通管理员");
        User user = new User();
        user.setUserName("admin");
        user.setRoles(Arrays.asList(role1, role2));

        // 对象转json
        String json = new Gson().toJson(user);
        System.out.println(json);
    }

    /**
     * {"userName":"admin","roles":[{"roleName":"系统管理员"},{"roleName":"普通管理员"}]}
     */
    @Test
    void toJson2() {
        Role role1 = new Role("系统管理员");
        Role role2 = new Role("普通管理员");
        User user = new User();
        user.setUserName("admin");
        user.setRoles(Arrays.asList(role1, role2));

        String json = new GsonBuilder().create().toJson(user);
        System.out.println(json);
    }

    /**
     * User(userName=admin, roles=[Role(roleName=系统管理员), Role(roleName=普通管理员)])
     */
    @Test
    void fromJson() {
        String json = "{\"userName\":\"admin\",\"roles\":[{\"roleName\":\"系统管理员\"},{\"roleName\":\"普通管理员\"}]}";
        User user = new Gson().fromJson(json, User.class);
        System.out.println(user);
    }

    /**
     * admin
     * {"userName":"admin","roles":{"roleName":"超级管理员"}}
     */
    @Test
    void createJsonObject() {

        JsonObject role = new JsonObject();
        role.addProperty("roleName", "超级管理员");

        JsonObject user = new JsonObject();
        user.addProperty("userName", "admin");
        user.add("roles", role);

        String username = user.get("userName").getAsString();
        System.out.println(username);
        System.out.println(user);
    }

    /**
     * admin
     */
    @Test
    void json2Object() {

        String json = "{\"userName\":\"admin\",\"roles\":[{\"roleName\":\"系统管理员\"},{\"roleName\":\"普通管理员\"}]}";

        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        String userName = jsonObject.get("userName").getAsString();
        System.out.println(userName);
    }


    /**
     * [{"roleName":"系统管理员"},{"roleName":"普通管理员"}]
     */
    @Test
    void list2Json() {

        Role role1 = new Role("系统管理员");
        Role role2 = new Role("普通管理员");
        String json = new Gson().toJson(Arrays.asList(role1, role2));

        System.out.println(json);
    }

    /**
     * [Role(roleName=系统管理员), Role(roleName=普通管理员)]
     */
    @Test
    void json2List() {
        String json = "[{\"roleName\":\"系统管理员\"},{\"roleName\":\"普通管理员\"}]";
        List<Role> roles = new Gson().fromJson(json, new TypeToken<List<Role>>() {}.getType());

        System.out.println(roles);
    }

    /**
     * [{"roleName":"系统管理员"},{"roleName":"普通管理员"}]
     */
    @Test
    void json2Array() {
        String json = "[{\"roleName\":\"系统管理员\"},{\"roleName\":\"普通管理员\"}]";
        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();

        System.out.println(jsonArray);
    }

    /**
     * {"userName":"admin"}
     */
    @Test
    void map2Json() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userName", "admin");

        String json = new Gson().toJson(hashMap);
        System.out.println(json);
    }

    /**
     * admin
     */
    @Test
    void json2Map() {
        String json = "{\"userName\":\"admin\"}";
        HashMap hashMap = new Gson().fromJson(json, new TypeToken<HashMap<String, String>>() {}.getType());

        System.out.println(hashMap.get("userName"));
    }


    /**
     * 默认的字段转换规则，字段名不变
     * {"roleName":"超级管理员"}
     */
    @Test
    void setFieldNamingPolicy1() {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();

        Role role = new Role("超级管理员");
        String json = gson.toJson(role);
        System.out.println(json);
    }

    /**
     * 将json中的字段名转换为首字母大写的格式
     * {"RoleName":"超级管理员"}
     */
    @Test
    void setFieldNamingPolicy2() {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();

        Role role = new Role("超级管理员");
        String json = gson.toJson(role);
        System.out.println(json);
    }

    /**
     * 将json中的字段名转换为首字母大写，单词之间以空格分割的格式
     * {"Role Name":"超级管理员"}
     */
    @Test
    void setFieldNamingPolicy3() {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES)
                .create();

        Role role = new Role("超级管理员");
        String json = gson.toJson(role);
        System.out.println(json);
    }

    /**
     * 将json中的字段名转换为小写字母，单词之间以下划线分割的格式
     * {"role_name":"超级管理员"}
     */
    @Test
    void setFieldNamingPolicy4() {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Role role = new Role("超级管理员");
        String json = gson.toJson(role);
        System.out.println(json);
    }

    /**
     * 将json中的字段名转换为小写字母，单词之间以分隔线分割的格式
     * {"role-name":"超级管理员"}
     */
    @Test
    void setFieldNamingPolicy5() {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                .create();

        Role role = new Role("超级管理员");
        String json = gson.toJson(role);
        System.out.println(json);
    }

    /**
     * 将json中的字段名转换为小写字母，单词之间以点分割的格式
     * {"role.name":"超级管理员"}
     */
    @Test
    void setFieldNamingPolicy6() {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DOTS)
                .create();

        Role role = new Role("超级管理员");
        String json = gson.toJson(role);
        System.out.println(json);
    }

    /**
     * 属性重命名
     * {"menu_name":"系统管理"}
     */
    @Test
    void serializedName() {
        Menu menu = new Menu("系统管理");

        String json = new Gson().toJson(menu);
        System.out.println(json);
    }

    /**
     * {"dept_name":"产品研发部"}
     */
    @Test
    void adapter() {
        Gson gson = new GsonBuilder()
                //为Dept注册TypeAdapter
                .registerTypeAdapter(Dept.class, new DeptAdapter())
                .create();

        Dept dept = new Dept("产品研发部");
        String json = gson.toJson(dept);

        System.out.println(json);
    }

}