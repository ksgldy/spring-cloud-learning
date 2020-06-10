package cn.idea360.demo.modules.json;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 当我遇上你
 * @公众号 当我遇上你
 * @since 2020-06-11
 */
@Data
@AllArgsConstructor
public class Menu {

    @SerializedName("menu_name")
    private String menuName;

}
