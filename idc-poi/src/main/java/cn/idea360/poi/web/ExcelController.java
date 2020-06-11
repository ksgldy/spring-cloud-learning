package cn.idea360.poi.web;

import cn.idea360.poi.dto.User;
import cn.idea360.poi.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class ExcelController {

    /**
     * http://localhost:8080/import
     * @param request
     * @return
     */
    @PostMapping("/import")
    public Object importExcel(HttpServletRequest request) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (request instanceof MultipartHttpServletRequest) {
            MultipartFile multipartFile = ((MultipartHttpServletRequest)request).getFile("file");
            ExcelUtils.importExcel(multipartFile, User.class);
        }
        return "上传成功";
    }

    /**
     * http://localhost:8080/export
     * @param response
     * @throws Exception
     */
    @GetMapping("export")
    public void export(HttpServletResponse response) throws Exception{
        // 创建模拟数据
        User user1 = new User("admin", "123456", LocalDateTime.now());
        User user2 = new User("test", "123456", LocalDateTime.now());
        List<User> users = Arrays.asList(user1, user2);
        // TODO 日期的处理可以在数据部分处理好, 然后@JsonIgnore注释LocalDateTime类型的字段即可

        HSSFWorkbook workbook = ExcelUtils.exportExcel(users, User.class);
        workbook.setSheetName(0,"sheetName");//设置sheet的Name

        String downloadName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() +".xls";
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(downloadName, StandardCharsets.UTF_8.toString()));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
    }
}
