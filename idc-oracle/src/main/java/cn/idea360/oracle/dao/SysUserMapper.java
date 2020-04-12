package cn.idea360.oracle.dao;

import cn.idea360.oracle.model.SysUser;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {

    int insert(@Param("sysUser") SysUser sysUser);

    SysUser selectById(Integer id);
}
