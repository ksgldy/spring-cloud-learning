package cn.idea360.mp.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WeixinTemplateMessage implements Serializable {
    private String toUser;
    private String templateId;
    private String url;
    private List<WeixinTemplateData> data;
}
