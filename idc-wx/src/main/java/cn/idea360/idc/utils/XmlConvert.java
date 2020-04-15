package cn.idea360.idc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;


public class XmlConvert {

    private static final Logger logger = LoggerFactory.getLogger(XmlConvert.class);

//    private static String xml = "<xml><ToUserName><![CDATA[gh_d0c6b73cc08e]]></ToUserName>\n" +
//            "<FromUserName><![CDATA[oHOLJw-r6lBxSXU4pRDKpoDyqWI0]]></FromUserName>\n" +
//            "<CreateTime>1586959860</CreateTime>\n" +
//            "<MsgType><![CDATA[event]]></MsgType>\n" +
//            "<Event><![CDATA[unsubscribe]]></Event>\n" +
//            "<EventKey><![CDATA[]]></EventKey>\n" +
//            "</xml>";

    private static Document loadXMLFromString(String xml) throws Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }



    public static WxXmlMessage convertXml2Message(String xml){
        Document document = null;
        try {
            document = loadXMLFromString(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Element rootElement = document.getDocumentElement(); // xml


        NodeList childNodes = rootElement.getChildNodes();

        Map<String, String> msgMap = new HashMap<>();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element child = (Element) node;

                String nodeName = child.getNodeName();
                String textContent = child.getTextContent();
                msgMap.put(nodeName, textContent);

                logger.info("key:{}, value:{}", nodeName, textContent);
            }
        }
        WxXmlMessage message = new WxXmlMessage();
        message.setToUser(msgMap.get("ToUserName"));
        message.setFromUser(msgMap.get("FromUserName"));
        message.setCreateTime(Long.valueOf(msgMap.get("CreateTime")));
        message.setMsgType(msgMap.get("MsgType"));
        message.setEvent(msgMap.get("Event"));
        message.setEventKey(msgMap.get("EventKey"));
        return message;


    }
//    public static void main(String[] args) throws Exception{
//
//        Document document = loadXMLFromString(xml);
//
//        Element rootElement = document.getDocumentElement(); // xml
//
//
//        NodeList childNodes = rootElement.getChildNodes();
//
//        for (int i = 0; i < childNodes.getLength(); i++) {
//            Node node = childNodes.item(i);
//            if (node.getNodeType() == Node.ELEMENT_NODE) {
//                Element child = (Element) node;
//
//            String nodeName = child.getNodeName();
//            String textContent = child.getTextContent();
//            System.out.println(nodeName + ":" + textContent);
//            }
//        }
//
//    }
}
