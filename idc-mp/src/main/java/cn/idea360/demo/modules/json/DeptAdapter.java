package cn.idea360.demo.modules.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * @author 当我遇上你
 * @公众号 当我遇上你
 * @since 2020-06-11
 */
public class DeptAdapter implements JsonSerializer<Dept> {

    @Override
    public JsonElement serialize(Dept dept, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject deptObject = new JsonObject();
        deptObject.addProperty("dept_name", dept.getDeptName());

        return deptObject;
    }
}
