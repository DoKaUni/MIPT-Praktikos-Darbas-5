package com.example.miptpraktikosdarbas5.Parsers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class EuropaXML {
    public static String getCurrencyRates(InputStream stream) throws IOException {
        StringBuilder result = new StringBuilder();
        try {
            DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlBuilder = xmlFactory.newDocumentBuilder();
            Document doc = xmlBuilder.parse(stream);

            NodeList itemNodes = doc.getElementsByTagName("Cube");
            for (int i = 0; i < itemNodes.getLength(); ++i) {
                Element itemNode = (Element) itemNodes.item(i);
                String currencyCode = itemNode.getElementsByTagName("currency").item(0).getFirstChild().getNodeValue();
                String rate = itemNode.getElementsByTagName("rate").item(0).getFirstChild().getNodeValue();
                result.append(String.format("Currency code: %s, rate %s \n", currencyCode, rate));
            }
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
