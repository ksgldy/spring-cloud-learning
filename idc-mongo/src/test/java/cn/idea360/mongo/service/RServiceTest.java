package cn.idea360.mongo.service;

import cn.idea360.mongo.entity.R;
import cn.idea360.mongo.entity.ResponseData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RServiceTest {

    @Autowired
    RService rService;

    @Test
    void save() {
        ResponseData responseData = new ResponseData();
        responseData.setName("admin");
        R r = new R();
        r.setCode(200);
        r.setMsg("ok");
        r.setData(responseData);
        rService.save(r);
    }

    /**
     * [R(code=200, msg=ok, data=ResponseData(name=admin))]
     */
    @Test
    void findByCode() {
        List<R> list = rService.findByCode(200);
        System.out.println(list);
    }
}