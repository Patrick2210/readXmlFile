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
    private static final String nameChildElements1 = "string";
    private static final String nameChildElements2 = "plurals";
    private static final String nameSubChildElements = "item";
    private static final String nameChildAttribute1 = "name";
    private static final String nameChildAttribute2 = "quantity";
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

    private Map<String, String> readSubChildElements(List<Document> documentList) {
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
        List<Document> documentList = createDocumentList(path);

        Map<String, String> childElements = readChildElements(documentList);
        Map<String, String> subElements = readSubChildElements(documentList);
        childElements.putAll(subElements);

        return childElements;
    }

}