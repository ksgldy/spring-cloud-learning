package cn.idea360.demo.modules.json;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author 当我遇上你
 * @公众号 当我遇上你
 * @since 2020-06-11
 */
@Data
public class UserGroup implements Serializable {

    private String groupName;
    private Map<String, User> idNumber;

}
