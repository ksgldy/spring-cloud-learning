package cn.idea360.mongo.service;

import cn.idea360.mongo.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Test
    void saveUser() {
        customerService.saveUser(new Customer("test", "123456"));
    }

    @Test
    void findUserByUserName() {
        Customer test = customerService.findUserByUserName("test");
        System.out.println(test);
    }
}