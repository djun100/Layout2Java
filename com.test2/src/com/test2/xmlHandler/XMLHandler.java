package com.test2.xmlHandler;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.test2.TagBean;

public class XMLHandler extends DefaultHandler{

	private String tagName;
	public HashSet<TagBean> set=new HashSet<TagBean>();
	public StringBuffer buff=new StringBuffer();
	
	public XMLHandler(){
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		tagName=localName;
		if(tagName==null||"".equals(tagName))tagName=qName;
	
		String val=attributes.getValue("android:id");
		if(val!=null&&!"".equals(val)){
			TagBean tagBean=new TagBean();
			tagBean.tagName=tagName;
			tagBean.setTagId(val);
			set.add(tagBean);
			buff.append(tagName+":"+val+"\n");
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)throws SAXException {
		
	}

	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
	}
	
	
 
	
	public String  getJavaForMB(String packageName,String fileName,boolean newByView,String mb){
		StringBuffer j=new StringBuffer();
		
		StringBuffer dy=new StringBuffer();
		StringBuffer ct=new StringBuffer();
		StringBuffer adapter=new StringBuffer();
		for (TagBean  bean : set) {
			if(bean!=null){
//				String val=bean.getTagId().replace("@+id/", "");
				String val=bean.tagIdName;
				String id=bean.getTagId();
				String tagName=bean.tagName;
				if(newByView){
					dy.append("	 public "+tagName+"  "+val+";\n");
					ct.append("		 "+val+"=("+tagName+")view.findViewById(R.id."+id+");\n");
					adapter.append("		    item."+val+"=("+tagName+")view.findViewById(R.id."+id+");\n");
				}else{
					dy.append("	 private "+tagName+"  "+val+";\n");
					ct.append("		 "+val+"=("+tagName+")findViewById(R.id."+id+");\n");
					adapter.append("		    item."+val+"=("+tagName+")findViewById(R.id."+id+");\n");
				}
			}
		};
		
		String p="";
		if(packageName!=null){
			p="import "+packageName+".R;\n";
		}
		j.append(p);
		try {
			InputStream in=XMLHandler.class.getClassLoader().getResourceAsStream(mb);
			Scanner scan=new Scanner(in);
			while(scan.hasNextLine()){
				String val=scan.nextLine();
				val=val.replace("${}.dy",dy.toString());
				val=val.replace("${}.new",ct.toString());
				val=val.replace("${1}.new",adapter.toString());
				val=val.replace("${}.filename",fileName);
				j.append(val+"\n");
			}
		} catch (Exception e) {
		}
		
		return j.toString();
	}
}
