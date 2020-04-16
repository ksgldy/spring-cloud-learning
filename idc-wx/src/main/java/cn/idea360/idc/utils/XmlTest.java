package cn.idea360.idc.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

public class XmlTest {

    public static void main(String[] args) throws Exception{
        String xml = "<xml><ToUserName><![CDATA[gh_d0c6b73cc08e]]></ToUserName>\n" +
                "<FromUserName><![CDATA[oHOLJw-r6lBxSXU4pRDKpoDyqWI0]]></FromUserName>\n" +
                "<CreateTime>1586940685</CreateTime>\n" +
                "<MsgType><![CDATA[event]]></MsgType>\n" +
                "<Event><![CDATA[subscribe]]></Event>\n" +
                "<EventKey><![CDATA[]]></EventKey>\n" +
                "</xml>";


        InputSource in = new InputSource(new StringReader(xml));
        in.setEncoding("UTF-8");
        SAXReader reader = new SAXReader();
        Document document = reader.read(in);
        Element root = document.getRootElement();
        List<Element> elements = root.elements();

        for(Iterator<Element> it = elements.iterator(); it.hasNext();){
            Element element = it.next();
            System.out.println(element.getName() + " : " + element.getTextTrim());
            if("Reserve".equals(element.getName())) {
                System.out.println(element.getName() + " : " + element.getTextTrim());
            }
        }
    }
}
