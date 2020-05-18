package cn.idea360.poi.web;

import cn.idea360.poi.dto.User;
import cn.idea360.poi.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
}
