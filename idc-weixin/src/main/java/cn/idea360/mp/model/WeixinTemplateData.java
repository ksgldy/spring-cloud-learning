package cn.idea360.mp.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class WeixinTemplateData implements Serializable {
    private String key;
    private String value;
    private String color;
}
