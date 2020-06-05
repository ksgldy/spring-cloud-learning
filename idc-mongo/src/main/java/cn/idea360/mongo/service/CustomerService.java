package cn.idea360.mongo.service;

import cn.idea360.mongo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveUser(Customer customer) {
        mongoTemplate.save(customer);
    }

    public Customer findUserByUserName(String firstName) {
        Query query=new Query(Criteria.where("firstName").is(firstName));
        Customer user =  mongoTemplate.findOne(query , Customer.class);
        return user;
    }
}
