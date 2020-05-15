package cn.idea360.poi.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelHeader {

    /**
     * 表头
     * @return
     */
    String value() default "";

    /**
     * 列索引
     * @return
     */
    int columnIndex() default 0;

}
