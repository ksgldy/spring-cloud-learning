package cn.idea360.idcwechat.gson;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        Tm tm = new Tm();
        tm.setUserName("admin");
        Item item = new Item();
        item.setUserName("name");
        item.setColor("#000");
        item.setValue("123");
        tm.setData(Arrays.asList(item));
        String s = WxGsonBuilder.create().toJson(tm);
        System.out.println(s);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        Gson gson = gsonBuilder.create();

//        Gson gson = new Gson();
        String s1 = gson.toJson(item);
        System.out.println(s1);
        Item item1 = gson.fromJson(s1, Item.class);
        System.out.println(item1);



    }
}
