package cn.idea360.idcwechat.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlUtils {

    public static Map<String, Object> xml2Map(String xmlString) {
        Map<String, Object> map = new HashMap<>(16);
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
}
