package cn.idea360.mongo.service;

import cn.idea360.mongo.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class FlowService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveFlow(Flow flow) {
        mongoTemplate.save(flow);
    }

    public Flow getFlowById(String id) {
        Query query=new Query(Criteria.where("id").is(id));
        Flow flow =  mongoTemplate.findOne(query , Flow.class);
        return flow;
    }
}
