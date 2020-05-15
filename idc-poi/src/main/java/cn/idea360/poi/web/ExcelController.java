package cn.idea360.poi.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
public class ExcelController {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * http://localhost:8080/import
     * @param request
     * @return
     */
    @PostMapping("/import")
    public Object importExcel(HttpServletRequest request) throws IOException {
        if (request instanceof MultipartHttpServletRequest) {
            MultipartFile multipartFile = ((MultipartHttpServletRequest)request).getFile("file");
            log.info(multipartFile.getName());

            if(null == multipartFile) {
                throw new NullPointerException("请选择文件");
            }

            log.info("文件类型:{}", multipartFile.getContentType());
            String fileName = multipartFile.getOriginalFilename();
            log.info("文件名:{}", fileName);

            if(!"application/vnd.ms-excel".equals(multipartFile.getContentType())) {
                throw new RuntimeException("请选择正确的文件类型与文件!");
            }

            InputStream inputStream = multipartFile.getInputStream();
            Workbook wb = WorkbookFactory.create(inputStream);

            // 读取第一个sheet
            Sheet sheet = wb.getSheetAt(0);
            //获取最大行数(或者sheet.getLastRowNum())
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行(表头)
            Row row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i<rownum; i++) {
                Map<String,String> map = new LinkedHashMap<String,String>();
                row = sheet.getRow(i);
                // 遇到空行则结束
                if (row == null) {
                    break;
                }
                // 处理列数据
                for (int j = 0; j < colnum; j++){
                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        continue;
                    }
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
                }
            }
        }
        return "上传成功";
    }
}
