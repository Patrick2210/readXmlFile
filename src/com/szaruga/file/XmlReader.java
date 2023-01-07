package com.szaruga.file;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class XmlReader {
    private final String nameChildElements1 = "string";
    private final String nameChildElements2 = "plurals";
    private final String nameSubChildElements = "item";
    private final String nameChildAttribute1 = "name";
    private final String nameChildAttribute2 = "quantity";
    private String path;
    private final DocumentBuilder documentBuilder;

    public XmlReader() {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

        try {
            this.documentBuilder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Document> createDocumentList() {
        List<Document> document = new ArrayList<>();
        try {
            Document doc = documentBuilder.parse(path);
            document.add(doc);
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        return document;
    }

    private Map<String, String> readChildElements() {
        List<Document> documentList = createDocumentList();
        Map<String, String> mapChildElements = new HashMap<>();

        for (Document document : documentList) {
            NodeList nodeList = document.getElementsByTagName(nameChildElements1);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementChild = (Element) node;
                    if (elementChild.getTagName().equals(nameChildElements1)) {
                        mapChildElements.put(elementChild.getAttribute(nameChildAttribute1), elementChild.getTextContent());
                    }
                }
            }
        }
        return mapChildElements;
    }

    private Map<String, String> readSubChildElements() {
        List<Document> documentList = createDocumentList();
        Map<String, String> mapSubChildElements = new HashMap<>();

        for (Document document : documentList) {
            NodeList nodeList = document.getElementsByTagName(nameChildElements2);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementChild = (Element) node;
                    NodeList nodeListSub = document.getElementsByTagName(nameSubChildElements);

                    for (int j = 0; j < nodeList.getLength(); j++) {
                        Node nodeSub = nodeListSub.item(j);

                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element elementSub = (Element) nodeSub;

                            if (elementChild.getTagName().equals(nameChildElements2) || elementChild.getTagName().equals(nameSubChildElements)) {
                                String key = elementChild.getAttribute(nameChildAttribute1) + "_" + elementSub.getAttribute(nameChildAttribute2);
                                mapSubChildElements.put(key, elementSub.getTextContent());
                            }
                        }
                    }
                }
            }
        }
        return mapSubChildElements;
    }

    public Map<String, String> read(String path) {
        this.path = path;
        Map<String, String> mapProperties = new HashMap<>();

        Map<String, String> childElements = readChildElements();
        Set<Map.Entry<String, String>> setChild = childElements.entrySet();
        for (Map.Entry<String, String> strChild : setChild) {
            String key = strChild.getKey();
            String value = strChild.getValue();

            mapProperties.put(key, value);
        }

        Map<String, String> subChildElements = readSubChildElements();
        Set<Map.Entry<String, String>> setSubChild = subChildElements.entrySet();
        for (Map.Entry<String, String> strSubChild : setSubChild) {
            String key = strSubChild.getKey();
            String value = strSubChild.getValue();
            mapProperties.put(key, value);
        }
        return mapProperties;
    }
}