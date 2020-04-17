package cn.idea360.idcwechat.bean;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public class WxTemplateMessage implements Serializable {

    private String touser;
    private String templateId;
    private String url;
    private Object data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static void main(String[] args) throws Exception{
        String data = "{\n" +
            "                   \"name\": {\n" +
            "                       \"value\":\"登高射太阳！\",\n" +
            "                       \"color\":\"#173177\"\n" +
            "                   }\n" +
            "           }";

        ObjectMapper objectMapper = new ObjectMapper();
        Object o = objectMapper.readValue(data, Object.class);

        WxTemplateMessage wxTemplateMessage = new WxTemplateMessage();
        wxTemplateMessage.setTouser("111");
        wxTemplateMessage.setUrl("http://local");
        wxTemplateMessage.setTemplateId("777");
        wxTemplateMessage.setData(o);
        System.out.println(wxTemplateMessage.toString());

        String test = " {\n" +
                "           \"touser\":\"oHOLJw-r6lBxSXU4pRDKpoDyqWI0\",\n" +
                "           \"template_id\":\"7BsKKP1MsDxbNmfYfTaNVm9guRvgwH8l2PmFbDCMip0\",\n" +
                "           \"url\":\"http://baidu.com\",         \n" +
                "           \"data\":{\n" +
                "                   \"name\": {\n" +
                "                       \"value\":\"登高射太阳！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        Object o1 = objectMapper.readValue(test, Object.class);
        System.out.println(test);
    }
}
