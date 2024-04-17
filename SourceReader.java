package com.test.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SourceReader {
	public static void main(String[] args) {
		try {
            List<String> jsonPaths = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode mappingNode = mapper.readTree(new File("D:\\Java\\Mapping.json"));
            File targetFile = new File("D:\\Java\\target.json");
            JsonNode targetNode = mapper.readTree(targetFile);

            File file = new File("D:\\Java\\test.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            for (JsonNode entry : mappingNode) {
                String source = entry.get("source").asText();
                String target = entry.get("target").asText();

                jsonPaths.add(target);
                XPath xpath = XPathFactory.newInstance().newXPath();
                XPathExpression exp = xpath.compile(source);
                NodeList nodeList = (NodeList) exp.evaluate(doc, XPathConstants.NODESET);

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node n = nodeList.item(i);
                    String val = n.getTextContent();
                }
            }
            System.out.println("Values updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
	 }
}
