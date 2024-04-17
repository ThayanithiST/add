package com.test.example;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

public class JSONGenerate {
	 public static void main(String[] args) {
		 try {
			 ObjectMapper mapper = new ObjectMapper();
	         File targetFile = new File("D:\\Java\\target.json");
	         JsonNode targetNode = mapper.readTree(targetFile);
			 Object result = JsonPath.read(targetNode.toString(), "$.Employee[*].name");
			 System.out.println("Employee name: " + result);
		 }catch(Exception e) {
			 System.out.print(e);
		 }

	    }
}
