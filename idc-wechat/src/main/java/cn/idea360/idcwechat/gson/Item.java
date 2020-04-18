package cn.idea360.idcwechat.gson;

import java.io.Serializable;

public class Item implements Serializable {

    private String userName;
    private String color;
    private String value;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
