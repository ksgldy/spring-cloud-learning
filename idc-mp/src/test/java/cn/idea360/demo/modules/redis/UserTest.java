package cn.idea360.demo.modules.redis;

import cn.idea360.demo.common.utils.BeanUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class UserTest {

    @Autowired
    private RedisTemplate<String,Object> template;

    /**
     * User{username='admin', password='123456'}
     */
    @Test
    public void saveValue() {
        User u=new User("admin", "123456");
        template.opsForValue().set(u.getUsername(), u);
        User result = (User) template.opsForValue().get(u.getUsername());
        System.out.println(result.toString());
    }

    /**
     * [123456, admin]
     */
    @Test
    public void saveHash(){

        User u=new User("admin", "123456");
        Map<String, Object> objectHashMap = new HashMap<>();
//        objectHashMap.put("username", u.getUsername());
//        objectHashMap.put("password", u.getPassword());
        try {
            objectHashMap = BeanUtils.obj2map(u);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        template.opsForHash().putAll("hash", objectHashMap);

        List<Object> myCache = template.opsForHash().values("hash");
        System.out.println(myCache);
    }

    /**
     * User{username='admin', password='123456'}
     */
    @Test
    public void saveList() {
        User user = new User("admin", "123456");
        template.opsForList().leftPush("list", user);
        Object rightPop = template.opsForList().rightPop("list");
        System.out.println(rightPop);
    }

}