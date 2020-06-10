package cn.idea360.demo.modules.json;

import lombok.Data;

import java.util.List;

/**
 * @author 当我遇上你
 * @公众号 当我遇上你
 * @since 2020-06-10
 */
@Data
public class User {

    private String userName;
    private List<Role> roles;

    public User(String userName) {
        this.userName = userName;
    }
}
