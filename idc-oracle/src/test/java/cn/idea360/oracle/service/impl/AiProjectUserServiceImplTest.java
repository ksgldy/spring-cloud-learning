package cn.idea360.oracle.service.impl;

import cn.idea360.oracle.service.AiProjectUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiProjectUserServiceImplTest {

    @Autowired
    private AiProjectUserService aiProjectUserService;

    @Test
    void saveOrUpdate() {

        String[] cids = {"1", "2", "3"};
        List<String> customerIdList = Arrays.asList(cids);

        aiProjectUserService.saveOrUpdate(1, 36L, customerIdList);
    }
}