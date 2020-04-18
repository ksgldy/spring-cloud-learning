package cn.idea360.idcwechat.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Iterator;

public class TmAdapter implements JsonSerializer<Tm> {
    @Override
    public JsonElement serialize(Tm message, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject messageJson = new JsonObject();
        messageJson.addProperty("user_name", message.getUserName());

        JsonObject data = new JsonObject();
        messageJson.add("data", data);

        Item datum;
        JsonObject dataJson;
        for(Iterator var7 = message.getData().iterator(); var7.hasNext(); data.add(datum.getUserName(), dataJson)) {
            datum = (Item)var7.next();
            dataJson = new JsonObject();
            dataJson.addProperty("value", datum.getValue());
            dataJson.addProperty("color", datum.getColor());
        }

        return messageJson;
    }
}
