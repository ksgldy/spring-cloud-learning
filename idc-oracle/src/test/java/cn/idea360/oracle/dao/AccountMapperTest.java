package cn.idea360.oracle.dao;


import cn.idea360.oracle.common.Page;
import cn.idea360.oracle.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class AccountMapperTest {

    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void insert() {
        Account account = new Account("admin", 1);
        accountMapper.insert(account);
        System.out.println(account.getId());
    }

    @Test
    public void insertBatch() {
        List<Account> data = new ArrayList<>();
        for (int i=0; i<3; i++) {
            Account account = new Account("test" + i, i+2);
            data.add(account);
        }
        accountMapper.insertBatch(data);
    }

    @Test
    public void updateIgnoreNullById() {
        Account account = new Account("admin0", 1);
        account.setId(1);
        accountMapper.updateIgnoreNullById(account);
    }

    @Test
    public void removeById() {
        accountMapper.removeById(4);
    }

    @Test
    public void selectById() {
        Account account = accountMapper.selectById(4);
        System.out.println(account.toString());
    }

    @Test
    public void selectByMap() {
        Map<String, Object> params = new HashMap<>();
        params.put("realName", "admin");
        Account account = accountMapper.selectByMap(params);
        System.out.println(account);
    }

    @Test
    public void selectByIds() {
        Integer[] ids = {1, 2};
        List<Account> accounts = accountMapper.selectByIds(ids);
        System.out.println(accounts);
    }

    @Test
    public void selectPage() {
        Page<Account> page = new Page<>(1, 10);
        HashMap<Object, Object> condition = new HashMap<>();
        condition.put("keyword", "ad");
        page.setCondition(condition);
        List<Account> accounts = accountMapper.selectPage(page);
        page.setRecords(accounts);
        System.out.println(page);
    }

    @Test
    public void listAll() {
        List<Account> accounts = accountMapper.listAll();
        System.out.println(accounts);
    }
}