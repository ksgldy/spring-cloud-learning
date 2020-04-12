package cn.idea360.oracle.dao;

import cn.idea360.oracle.model.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SysUserMapperTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void insert() {
        SysUser admin = new SysUser("admin", 1);
        sysUserMapper.insert(admin);
        System.out.println(admin.getId());
    }

    @Test
    public void selectById() {
        SysUser sysUser = sysUserMapper.selectById(1);
        System.out.println(sysUser.getUserName());
    }
}