package com.szaruga.file;

import com.szaruga.EnumStrings;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class XmlReader {
    private final DocumentBuilder documentBuilder;

    public XmlReader() {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

        try {
            this.documentBuilder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Document> createDocumentList(String path) {
        List<Document> document = new ArrayList<>();
        try {
            Document doc = documentBuilder.parse(path);
            document.add(doc);
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        return document;
    }

    private Map<String, String> readChildElements(List<Document> documentList) {
        Map<String, String> mapChildElements = new HashMap<>();

        for (Document document : documentList) {
            NodeList nodeList = document.getElementsByTagName(EnumStrings.STRING.extension);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementChild = (Element) node;

                    if (elementChild.getTagName().equals(EnumStrings.STRING.extension)) {
                        mapChildElements.put(elementChild.getAttribute(EnumStrings.NAME.extension), elementChild.getTextContent());
                    }
                }
            }
        }
        return mapChildElements;
    }

    private Map<String, String> readSubChildElements(List<Document> documentList) {
        Map<String, String> mapSubChildElements = new HashMap<>();

        for (Document document : documentList) {
            NodeList nodeList = document.getElementsByTagName(EnumStrings.PLURALS.extension);
            NodeList nodeListSub = document.getElementsByTagName(EnumStrings.ITEM.extension);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nodeChild = nodeList.item(i);

                for (int j = 0; j < nodeList.getLength(); j++) {
                    Node nodeSub = nodeListSub.item(j);

                    if (nodeChild.getNodeType() == Node.ELEMENT_NODE) {
                        Element elementChild = (Element) nodeChild;
                        Element elementSub = (Element) nodeSub;

                        if (elementChild.getTagName().equals(EnumStrings.PLURALS.extension) ||
                                elementChild.getTagName().equals(EnumStrings.ITEM.extension)) {

                            String key = elementChild.getAttribute(EnumStrings.NAME.extension)
                                    + "_" + elementSub.getAttribute(EnumStrings.QUANTITY.extension);

                            mapSubChildElements.put(key, elementSub.getTextContent());
                        }
                    }
                }
            }
        }
        return mapSubChildElements;
    }

    public Map<String, String> read(String path) {
        List<Document> documentList = createDocumentList(path);

        Map<String, String> childElements = readChildElements(documentList);
        Map<String, String> subElements = readSubChildElements(documentList);
        childElements.putAll(subElements);

        return childElements;
    }
}