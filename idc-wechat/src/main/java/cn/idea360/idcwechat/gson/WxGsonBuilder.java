package cn.idea360.idcwechat.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WxGsonBuilder {

    private static final GsonBuilder INSTANCE = new GsonBuilder();

    public WxGsonBuilder() {
    }

    public static Gson create() {
        return INSTANCE.create();
    }

    static {
        INSTANCE.registerTypeAdapter(Tm.class, new TmAdapter());
    }
}
