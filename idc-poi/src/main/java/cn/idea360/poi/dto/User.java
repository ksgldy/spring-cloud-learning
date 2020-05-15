package cn.idea360.poi.dto;

import cn.idea360.poi.annotation.ExcelHeader;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @ExcelHeader(value = "账号")
    private String username;
    @ExcelHeader(value = "密码")
    private String password;
    @ExcelHeader(value = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;

}
