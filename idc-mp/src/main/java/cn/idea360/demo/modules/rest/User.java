package cn.idea360.demo.modules.rest;

import lombok.Data;

/**
 * @author 当我遇上你
 * @公众号 当我遇上你
 * @since 2020-05-27
 */
@Data
public class User {

    private String username;
    private String password;

    // 注意需要无参构造方法
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
