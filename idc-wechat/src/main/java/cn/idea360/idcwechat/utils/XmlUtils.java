package cn.idea360.idcwechat.utils;

import cn.idea360.idcwechat.bean.WxXmlMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlUtils {

    private static final Logger logger = LoggerFactory.getLogger(XmlUtils.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, String> xml2Map(String xmlString) {
        Map<String, String> map = new HashMap<>(16);
        try {
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(new StringReader(xmlString));
            Element root = doc.getRootElement();
            List<Element> elements = root.elements();
            for (Element element : elements) {
                map.put(element.getName(), element.getTextTrim());
            }

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

//    public static <T> T xml2Json(String xmlString, Class<T> cls) {
//        Map<String, String> stringMap = xml2Map(xmlString);
//        try {
//            String json = objectMapper.writeValueAsString(stringMap);
//            if (StringUtils.isEmpty(json)) {
//                return null;
//            }
//            T t = JsonMapper.build().fromJson(json, cls);
//            return t;
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


    public static Map<String, Object> xml2MapByDom(String xmlString) {
        org.w3c.dom.Document document = null;
        try {
            document = loadXMLFromString(xmlString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        org.w3c.dom.Element rootElement = document.getDocumentElement(); // xml


        NodeList childNodes = rootElement.getChildNodes();

        Map<String, Object> map = new HashMap<>(16);
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element child = (org.w3c.dom.Element) node;

                String nodeName = child.getNodeName();
                String textContent = child.getTextContent();
                map.put(nodeName, textContent);
            }
        }
        return map;
    }

    private static org.w3c.dom.Document loadXMLFromString(String xml) throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }
}
