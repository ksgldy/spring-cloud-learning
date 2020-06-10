package cn.idea360.mongo.service;

import cn.idea360.mongo.entity.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 当我遇上你
 * @公众号 当我遇上你
 * @since 2020-06-08
 */
@Service
public class RService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(R r) {
        mongoTemplate.save(r);
    }

    public List<R> findByCode(int code) {
        Query query=new Query(Criteria.where("code").is(code));
        List<R> list = mongoTemplate.find(query, R.class);
        return list;
    }
}
