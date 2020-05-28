package cn.idea360.poi.utils;

import cn.idea360.poi.annotation.ExcelHeader;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class ExcelUtils {

    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     *
     * @param multipartFile
     * @param clz VO对象，对应Excel表头
     * @param <T>
     * @throws IOException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static <T> void importExcel(MultipartFile multipartFile, Class<T> clz) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if(null == multipartFile) {
            throw new NullPointerException("请选择文件");
        }

        log.info(multipartFile.getName());

        log.info("文件类型:{}", multipartFile.getContentType());
        String fileName = multipartFile.getOriginalFilename();
        log.info("文件名:{}", fileName);

        if(!"application/vnd.ms-excel".equals(multipartFile.getContentType())) {
            throw new RuntimeException("请选择正确的文件类型与文件!");
        }

        // 返回数据
        List<T> list = new ArrayList<>();

        InputStream inputStream = multipartFile.getInputStream();
        Workbook wb = WorkbookFactory.create(inputStream);

        // 读取第一个sheet
        Sheet sheet = wb.getSheetAt(0);
        // 获取最大行数(或者sheet.getLastRowNum())
        int rownum = sheet.getPhysicalNumberOfRows();
        // 反射获取字段
        Field[] fields = clz.getDeclaredFields();
        // 获取第一行(表头)
        Row row = sheet.getRow(0);
        // 获取最大列数
        int column = row.getPhysicalNumberOfCells();

        // 表头校验
        for (int j = 0; j < fields.length; j++){
            Field field = fields[j];
            if (field.isAnnotationPresent(ExcelHeader.class)) {
                ExcelHeader annotation = field.getAnnotation(ExcelHeader.class);
                Cell cell = row.getCell(j);
                if (cell == null || !getCellValue(cell).equals(annotation.value())) {
                    throw new RuntimeException("Excel格式错误");
                }
            }
        }

        // 处理行数据
        for (int i = 1; i<rownum; i++) {

            row = sheet.getRow(i);
            // 遇到空行则结束
            if (row == null) {
                break;
            }

            T rowData = clz.getDeclaredConstructor().newInstance();

            // 处理列数据
            for (int j = 0; j < fields.length; j++){

                Field field = fields[j];
                // 设置属性可访问
                field.setAccessible(true);

                if (field.isAnnotationPresent(ExcelHeader.class)) {

                    ExcelHeader annotation = field.getAnnotation(ExcelHeader.class);

                    // 这里默认按列顺序，也可以根据columnIndex设置列顺序
                    int columnIndex = annotation.columnIndex();

                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        continue;
                    }
                    // 获取列值
                    Object value = getCellValue(cell);
                    // 设置属性
                    setFieldValue(rowData, field, value);
                }

            }
            list.add(rowData);
        }
        log.info("上传数据={}", list.toString());
    }

    private static <T> void setFieldValue(T rowData, Field field, Object value) throws IllegalAccessException {
        
        if (field.getType() == int.class || field.getType() == Integer.class) {
            field.set(rowData, value);
        } else if (field.getType() == long.class || field.getType() == Long.class) {
            field.set(rowData, value);
        } else if (field.getType() == double.class || field.getType() == Double.class) {
            field.set(rowData, value);
        } else if (field.getType() == String.class) {
            field.set(rowData, String.valueOf(value));
        } else if (field.getType() == LocalDateTime.class) {
            field.set(rowData, LocalDateTime.parse(String.valueOf(value), dateTimeFormatter));
        }
    }
    
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;

        if (cellType == CellType._NONE) {

        } else if (cellType == CellType.NUMERIC) {
            // 数值型
            if (DateUtil.isCellDateFormatted(cell)) {
                // 日期类型
                Date d = cell.getDateCellValue();
                cellValue = dateTimeFormatter.format(LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault()));
            } else {
                double numericCellValue = cell.getNumericCellValue();
                BigDecimal bdVal = new BigDecimal(numericCellValue);
                if ((bdVal + ".0").equals(Double.toString(numericCellValue))) {
                    // 整型
                    cellValue = bdVal;
                } else if (String.valueOf(numericCellValue).contains("E10")) {
                    // 科学记数法
                    cellValue = new BigDecimal(numericCellValue).toPlainString();
                } else {
                    // 浮点型
                    cellValue = numericCellValue;
                }
            }
        } else if (cellType == CellType.STRING) {
            // 字符串型
            cellValue = cell.getStringCellValue();
        } else if (cellType == CellType.FORMULA) {
            // 公式型
        } else if (cellType == CellType.BLANK) {
            // 空值
        } else if (cellType == CellType.BOOLEAN) {
            // 布尔型
            cellValue = cell.getBooleanCellValue();
        } else if (cellType == CellType.ERROR) {
            // 错误
            cellValue = cell.getErrorCellValue();
        }

        log.info("cellType={}, cellValue={}", cellType.name(), cellValue);
        return cellValue;
    }




    /**
     *
     * @param data 需要导出的数据
     * @param clz 数据对应的实体类
     * @param <T> 泛型
     * @return Excel文件
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static <T> HSSFWorkbook exportExcel(List<T> data, Class<T> clz) throws NoSuchFieldException, IllegalAccessException {

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
            if (field.isAnnotationPresent(ExcelHeader.class)) {
                ExcelHeader annotation = field.getAnnotation(ExcelHeader.class);
                headers.add(annotation.value());
                rowHeader.createCell(h).setCellValue(annotation.value());
            }
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
