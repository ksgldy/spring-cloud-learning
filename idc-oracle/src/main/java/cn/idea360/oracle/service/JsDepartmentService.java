package cn.idea360.oracle.service;

import cn.idea360.oracle.model.JsDepartment;

import java.util.Collection;
import java.util.List;

public interface JsDepartmentService {

    List<JsDepartment> listByDepartmentIds(Collection<Integer> departmentIds);

}
