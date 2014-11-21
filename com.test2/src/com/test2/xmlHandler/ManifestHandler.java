package com.test2.xmlHandler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ManifestHandler extends DefaultHandler{

	private String tagName;
	public String packageName;
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		tagName=localName;
		if(tagName==null||"".equals(tagName))tagName=qName;
		if("manifest".equals(tagName)){
			packageName=attributes.getValue("package");
		}
		
	}
	
}
