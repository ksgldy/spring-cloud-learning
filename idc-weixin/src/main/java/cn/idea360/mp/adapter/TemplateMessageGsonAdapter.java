package cn.idea360.mp.adapter;

import cn.idea360.mp.model.WeixinTemplateData;
import cn.idea360.mp.model.WeixinTemplateMessage;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Iterator;

public class TemplateMessageGsonAdapter implements JsonSerializer<WeixinTemplateMessage> {
    @Override
    public JsonElement serialize(WeixinTemplateMessage message, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject messageJson = new JsonObject();
        messageJson.addProperty("touser", message.getToUser());
        messageJson.addProperty("template_id", message.getTemplateId());
        messageJson.addProperty("url", message.getUrl());

        JsonObject data = new JsonObject();
        messageJson.add("data", data);

        WeixinTemplateData item;
        JsonObject dataJson;
        for(Iterator itemData = message.getData().iterator(); itemData.hasNext(); data.add(item.getKey(), dataJson)) {
            item = (WeixinTemplateData)itemData.next();
            dataJson = new JsonObject();
            dataJson.addProperty("value", item.getValue());
            dataJson.addProperty("color", item.getColor());
        }

        return messageJson;
    }
}
