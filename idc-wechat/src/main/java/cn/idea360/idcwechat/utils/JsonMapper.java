package cn.idea360.idcwechat.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.StringWriter;

public class JsonMapper {

    private static final JsonMapper INSTANCE = new JsonMapper();
    private static final Logger logger = LoggerFactory.getLogger(JsonMapper.class);
    ObjectMapper mapper = new ObjectMapper();

    public <T> T fromJson(String jsonString, Class<T> clazz) {
        return fromJson(jsonString, clazz, false);
    }

    public <T> T fromJson(String jsonString, Class<T> clazz, boolean snackCase) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            if (snackCase) {
                // 下划线转驼峰
                mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            } else {
                mapper.setPropertyNamingStrategy(null);
            }
            return mapper.readValue(jsonString, clazz);

        } catch (IOException e) {
            logger.warn("parse json string error:" + jsonString, e);

            return null;
        }
    }

    public String toJson(Object object) {

        try {
            StringWriter sw = new StringWriter();
            JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
            mapper.writeValue(gen, object);
            String json = sw.toString();
            return json;
        } catch (IOException e) {
            logger.warn("write to json string error:" + object, e);
            return null;
        }
    }


    public static JsonMapper build() {
        return INSTANCE;
    }
}
