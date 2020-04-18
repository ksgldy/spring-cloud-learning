package cn.idea360.idcwechat.gson;

import java.io.Serializable;
import java.util.List;

public class Tm implements Serializable {

    private String userName;
    private List<Item> data;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Item> getData() {
        return data;
    }

    public void setData(List<Item> data) {
        this.data = data;
    }
}
