package cn.idea360.idcwechat.gson;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        Tm tm = new Tm();
        tm.setUsername("admin");
        Item item = new Item();
        item.setName("name");
        item.setColor("#000");
        item.setValue("123");
        tm.setData(Arrays.asList(item));
        String s = WxGsonBuilder.create().toJson(tm);
        System.out.println(s);
    }
}
