package cn.idea360.oracle.dao;

import cn.idea360.oracle.model.JsDepartment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class JsDepartmentMapperTest {

    @Autowired
    private JsDepartmentMapper jsDepartmentMapper;

    @Test
    public void listByDepartmentIds() throws Exception {

        Set<Integer> set = new HashSet<>();
        set.add(0);
        List<JsDepartment> jsDepartments = jsDepartmentMapper.listByDepartmentIds(set);
        System.out.println(jsDepartments.size());
    }

}