package com.example.miptpraktikosdarbas5.Parsers;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class FloatRatesXML {
    public static String getCurrencyRates(InputStream stream) throws IOException {
        Log.d("FloatRatesXML", "getCurrencyRates Method called!");

        StringBuilder result = new StringBuilder();
        try {
            DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlBuilder = xmlFactory.newDocumentBuilder();
            Document doc = xmlBuilder.parse(stream);

            NodeList itemNodes = doc.getElementsByTagName("item");
            for (int i = 0; i < itemNodes.getLength(); ++i) {
                Element itemNode = (Element) itemNodes.item(i);
                String currencyName = itemNode.getElementsByTagName("targetName").item(0).getFirstChild().getNodeValue();
                String currencyCode = itemNode.getElementsByTagName("targetCurrency").item(0).getFirstChild().getNodeValue();
                String rate = itemNode.getElementsByTagName("exchangeRate").item(0).getFirstChild().getNodeValue();
                result.append(String.format("Currency name: %s, code: %s, rate: %s\n", currencyName, currencyCode, rate));
            }
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}