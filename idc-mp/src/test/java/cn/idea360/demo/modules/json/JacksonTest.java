package cn.idea360.demo.modules.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author 当我遇上你
 * @公众号 当我遇上你
 * @since 2020-06-11
 */
public class JacksonTest {

    /**
     * {"userName":"admin","roles":[{"roleName":"系统管理员"},{"roleName":"普通管理员"}]}
     * @throws JsonProcessingException
     */
    @Test
    void Obj2Json() throws JsonProcessingException {
        Role role1 = new Role("系统管理员");
        Role role2 = new Role("普通管理员");
        User user = new User();
        user.setUserName("admin");
        user.setRoles(Arrays.asList(role1, role2));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);

        System.out.println(json);
    }

    /**
     * 需要无参构造方法
     * User(userName=admin, roles=[Role(roleName=系统管理员), Role(roleName=普通管理员)])
     * @throws JsonProcessingException
     */
    @Test
    void json2Obj() throws JsonProcessingException {
        String json = "{\"userName\":\"admin\",\"roles\":[{\"roleName\":\"系统管理员\"},{\"roleName\":\"普通管理员\"}]}";

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json, User.class);
        System.out.println(user);
    }

    /**
     * admin
     * @throws JsonProcessingException
     */
    @Test
    void json2Obj2() throws JsonProcessingException {
        String json = "{\"userName\":\"admin\",\"roles\":[{\"roleName\":\"系统管理员\"},{\"roleName\":\"普通管理员\"}]}";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(json);

        String userName = jsonNode.get("userName").asText();
        System.out.println(userName);
    }

    /**
     * [{"roleName":"系统管理员"},{"roleName":"普通管理员"}]
     * @throws JsonProcessingException
     */
    @Test
    void list2Json() throws JsonProcessingException {
        Role role1 = new Role("系统管理员");
        Role role2 = new Role("普通管理员");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(Arrays.asList(role1, role2));

        System.out.println(json);
    }

    /**
     * 需要无参构造方法
     * [Role(roleName=系统管理员), Role(roleName=普通管理员)]
     * @throws JsonProcessingException
     */
    @Test
    void json2List() throws JsonProcessingException {
        String json = "[{\"roleName\":\"系统管理员\"},{\"roleName\":\"普通管理员\"}]";

        ObjectMapper mapper = new ObjectMapper();
        List<Role> roles = mapper.readValue(json, new TypeReference<List<Role>>() {
        });

        System.out.println(roles);
    }
}
