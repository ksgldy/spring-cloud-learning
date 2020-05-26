package cn.idea360.demo.modules.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestControllerTest {

    @Autowired
    RestTemplate restTemplate;

    /**
     * User(username=admin, password=123)
     */
    @Test
    void getForEntity() {
        ResponseEntity<User> response = restTemplate.getForEntity("http://localhost:8888/user", User.class);
        User body = response.getBody();
        System.out.println(body);
    }

    /**
     * User(username=admin, password=123)
     */
    @Test
    void getForObject() {
        User user = restTemplate.getForObject("http://localhost:8888/user", User.class);
        System.out.println(user);
    }

    /**
     * User(username=admin, password=123)
     */
    @Test
    void exchange() {

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        ResponseEntity<User> exchange = restTemplate.exchange("http://localhost:8888/user", HttpMethod.GET, httpEntity, User.class);
        User body = exchange.getBody();
        System.out.println(body);
    }

    /**
     * [User(username=admin, password=456)]
     */
    @Test
    void list1() {
        User[] users = restTemplate.getForObject("http://localhost:8888/list", User[].class);
        List<User> list = Arrays.asList(users);
        System.out.println(list);
    }

    /**
     * [User(username=admin, password=456)]
     */
    @Test
    void list2() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange("http://localhost:8888/list", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>(){});
        List<User> body = responseEntity.getBody();
        System.out.println(body);
    }
}