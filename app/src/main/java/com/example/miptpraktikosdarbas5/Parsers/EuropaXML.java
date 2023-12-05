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

            Element rootCube = (Element) doc.getElementsByTagName("Cube").item(0);

            NodeList cubeNodes = rootCube.getElementsByTagName("Cube");
            for (int i = 1; i < cubeNodes.getLength(); ++i) {
                Element cubeNode = (Element) cubeNodes.item(i);
                String currencyCode = cubeNode.getAttribute("currency");
                String rate = cubeNode.getAttribute("rate");
                result.append(String.format("Currency code: %s, rate: %s\n", currencyCode, rate));
            }
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
