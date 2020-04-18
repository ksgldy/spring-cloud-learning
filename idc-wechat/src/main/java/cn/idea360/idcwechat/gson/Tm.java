package cn.idea360.idcwechat.gson;

import java.io.Serializable;
import java.util.List;

public class Tm implements Serializable {

    private String username;
    private List<Item> data;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Item> getData() {
        return data;
    }

    public void setData(List<Item> data) {
        this.data = data;
    }
}
