package cn.idea360.idcwechat.xml;

import com.thoughtworks.xstream.XStream;

public class Test {

//    public static void main(String[] args) {
//        String xml = "<xml>\n" +
//                "  <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
//                "</xml>";
//
//        XStream stream = new XStream();
//        stream.processAnnotations(Tm.class);
//        Tm testEntity = (Tm) stream.fromXML(xml);
//        System.out.println(testEntity);
//    }


    public static void main(String[] args) {
        XStream xStream = new XStream();
        xStream.alias("User", User.class);
        String xml = "<User>\n" +
                "  <userName>lanweihong</userName>\n" +
                "  <email>lwhhhp@gmail.com</email>\n" +
                "</User>";
        //转对象
        User user = (User)xStream.fromXML(xml);
        System.out.println(user.toString());

    }
}
