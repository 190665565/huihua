package com.partner.huihua.utils.common;

import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
/**
 * 
 * 用来解析字XML
 * <p/>
 * <example>
 * 		Document doc = XmlUtil.parseXml(xml);<p/>
 * 		String test = XmlUtil.getSingleNodeValue(doc, "//test/test");
 * </example>
 * @author 
 *
 */
public class XmlUtil {
	public static Document parseXml(String xml) {
		try {
			SAXReader reader = new SAXReader();
			return reader.read(new StringReader(xml.trim()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String getSingleNodeValue(Node doc, String path) {
		Node node = doc.selectSingleNode(path);
		if (node != null) {
			return node.getText();
		}
		return null;
	}

	public static List<Node> getNodes(Node doc, String path) {
		@SuppressWarnings("unchecked")
		List<Node> nodes = doc.selectNodes(path);
		return nodes;
	}

	public static String createXml(Map<String, String> nodeMap) {

		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("result");
		for (Iterator<String> it = nodeMap.keySet().iterator(); it.hasNext();) {
			String tempStr = it.next();
			Element node = root.addElement(tempStr);
			node.addText(nodeMap.get(tempStr));
		}

		return doc.asXML();
	}
}
