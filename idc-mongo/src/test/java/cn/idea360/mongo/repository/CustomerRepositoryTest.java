package cn.idea360.mongo.repository;

import cn.idea360.mongo.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    void save() {
        repository.save(new Customer("admin", "123456"));
    }

    @Test
    void findByFirstName() {
        Customer admin = repository.findByFirstName("admin");
        System.out.println(admin);
    }

    @Test
    void findByLastName() {
        List<Customer> admin = repository.findByLastName("admin");
        System.out.println(admin);
    }
}