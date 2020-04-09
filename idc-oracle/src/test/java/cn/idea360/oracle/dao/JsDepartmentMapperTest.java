package cn.idea360.oracle.dao;

import cn.idea360.oracle.model.JsDepartment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class JsDepartmentMapperTest {

    @Autowired
    private JsDepartmentMapper jsDepartmentMapper;

    @Test
    public void listByDepartmentIds() throws Exception {

        List<JsDepartment> jsDepartments = jsDepartmentMapper.listByDepartmentIds(Arrays.asList(1235));
        System.out.println(jsDepartments.size());
    }

}