package cn.idea360.oracle.dao;

import cn.idea360.oracle.model.JsDepartment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsDepartmentMapper {

    List<JsDepartment> listByDepartmentIds(@Param("departmentIds") List<Integer> departmentIds);
}
