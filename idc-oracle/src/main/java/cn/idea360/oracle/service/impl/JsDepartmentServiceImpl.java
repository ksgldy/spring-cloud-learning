package cn.idea360.oracle.service.impl;

import cn.idea360.oracle.dao.JsDepartmentMapper;
import cn.idea360.oracle.model.JsDepartment;
import cn.idea360.oracle.service.JsDepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class JsDepartmentServiceImpl implements JsDepartmentService {

    @Autowired
    private JsDepartmentMapper jsDepartmentMapper;


    @Override
    public List<JsDepartment> listByDepartmentIds(Collection<Integer> departmentIds) {
        return jsDepartmentMapper.listByDepartmentIds(departmentIds);
    }
}
