package cn.idea360.oracle.service.impl;

import cn.idea360.oracle.dto.AiProjectGroupReqDTO;
import cn.idea360.oracle.service.AiProjectGroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiProjectGroupServiceImplTest {

    @Autowired
    AiProjectGroupService aiProjectGroupService;

    @Test
    void pageProjectGroup() {
    }

    @Test
    void addProjectGroup() throws Exception{

        String[] userIds = {"5", "6", "7", "8"};
        AiProjectGroupReqDTO aiProjectGroupReqDTO = new AiProjectGroupReqDTO();
        aiProjectGroupReqDTO.setCompanyId(1);
        aiProjectGroupReqDTO.setGroupName("2020040912");
        aiProjectGroupReqDTO.setUserIds(Arrays.asList(userIds));
        aiProjectGroupReqDTO.setCreater("77");
        aiProjectGroupReqDTO.setUpdater("77");

        aiProjectGroupService.addProjectGroup(aiProjectGroupReqDTO);

    }
}