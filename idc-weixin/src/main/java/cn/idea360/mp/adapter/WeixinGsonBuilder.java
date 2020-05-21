package cn.idea360.mp.adapter;

import cn.idea360.mp.model.WeixinTemplateMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WeixinGsonBuilder {

    private static final GsonBuilder INSTANCE = new com.google.gson.GsonBuilder();

    public WeixinGsonBuilder() {
    }

    public static Gson create() {
        return INSTANCE.create();
    }

    static {
        INSTANCE.registerTypeAdapter(WeixinTemplateMessage.class, new TemplateMessageGsonAdapter());
    }
}
