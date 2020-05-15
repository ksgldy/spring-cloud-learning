package cn.idea360.poi;

import cn.idea360.poi.annotation.ExcelHeader;
import cn.idea360.poi.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Slf4j
//@SpringBootTest
class IdcPoiApplicationTests {

    @Test
    void contextLoads() throws NoSuchFieldException, IllegalAccessException, IOException {
        // 创建模拟数据
        User user1 = new User("admin", "123456");
        User user2 = new User("test", "123456");
        List<User> users = Arrays.asList(user1, user2);

        String basePath =  "E:\\example\\spring-cloud-learning\\idc-poi\\oss\\";
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() +".xls";

        HSSFWorkbook workbook = exportExcel(users, User.class);
        workbook.setSheetName(0,"sheetName");//设置sheet的Name
        workbook.write(new File(basePath + File.separator + fileName));
    }

    public <T> HSSFWorkbook exportExcel(List<T> data, Class<T> clz) throws NoSuchFieldException, IllegalAccessException, IOException {

        Field[] fields = clz.getDeclaredFields();
        List<String> headers = new LinkedList<>();
        List<String> variables = new LinkedList<>();

        // 创建工作薄对象
        HSSFWorkbook workbook=new HSSFWorkbook();//这里也可以设置sheet的Name
        // 创建工作表对象
        HSSFSheet sheet = workbook.createSheet();
        // 创建表头
        Row rowHeader = sheet.createRow(0);

        // 表头处理
        for (int h = 0; h < fields.length; h++) {
            Field field = fields[h];
            // 表头
            ExcelHeader annotation = field.getAnnotation(ExcelHeader.class);
            headers.add(annotation.value());
            rowHeader.createCell(h).setCellValue(annotation.value());

            // 字段
            variables.add(field.getName());
        }

        // 数据处理
        for (int i = 0; i < data.size() ; i++) {

            //创建工作表的行(表头占用1行, 这里从第二行开始)
            HSSFRow row = sheet.createRow(i + 1);
            // 获取一行数据
            T t = data.get(i);
            Class<?> aClass = t.getClass();
            // 填充列数据
            for (int j = 0; j < variables.size(); j++) {
                Field declaredField = aClass.getDeclaredField(variables.get(j));
                declaredField.setAccessible(true);

                String key = declaredField.getName();
                Object value = declaredField.get(t);

                row.createCell(j).setCellValue(value.toString());
            }
        }
        log.info("Excel文件创建成功");
        return workbook;
    }

}
