package cn.idea360.idcwechat.xml;

import com.thoughtworks.xstream.XStream;

public class Test {

    public static void main(String[] args) {
        String xml = "<xml>\n" +
                "  <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                "</xml>";

        XStream stream = new XStream();
        stream.processAnnotations(new Class[]{Tm.class});
        Tm testEntity = (Tm) stream.fromXML(xml);
        System.out.println(testEntity);
    }
}
