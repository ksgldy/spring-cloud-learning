package cn.idea360.poi.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcelController {

    @PostMapping("/import")
    public Object importExcel() {
        return null;
    }
}
