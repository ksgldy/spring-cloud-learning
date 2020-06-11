package cn.idea360.demo.modules.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 当我遇上你
 * @公众号 当我遇上你
 * @since 2020-06-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    private String roleName;

}
