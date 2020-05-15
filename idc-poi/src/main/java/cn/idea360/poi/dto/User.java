package cn.idea360.poi.dto;

import cn.idea360.poi.annotation.ExcelHeader;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    @ExcelHeader(value = "账号")
    private String username;
    @ExcelHeader(value = "密码")
    private String password;

}
