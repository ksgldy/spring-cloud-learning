package cn.idea360.demo.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BeanUtils {

    public static <T> Map<String, Object> obj2map(T o) throws IllegalAccessException {

        Class<?> aClass = o.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();

        Map<String,Object> map = new HashMap<String, Object>();

        for (int j = 0; j < declaredFields.length; j++) {
            Field declaredField = declaredFields[j];

            // 获取属性
            String key = declaredField.getName();
            declaredField.setAccessible(true);
            // 获取属性值
            Object value = declaredField.get(o);

            log.info("key={}, value={}", key, value == null? "" : value.toString());

            map.put(key, value);
        }
        return map;
    }

}
