package cn.idea360.demo.modules.json;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 当我遇上你
 * @公众号 当我遇上你
 * @since 2020-06-10
 */
@Data
@AllArgsConstructor
public class Role implements Serializable {

    private String roleName;

}
