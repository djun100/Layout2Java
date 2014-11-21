package com.test2;

public class TagBean {

	public  String tagName;
	private String tagId;
	public  String tagIdName;
	
	
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
		this.tagIdName=tagId;
		if(tagId!=null&&tagId.length()>0){
			this.tagId=tagId.replace("@+id/", "");
			String[] array=this.tagId.split("_");
			if(array.length>1){
				this.tagIdName=array[array.length-2]+format(array[array.length-1]);
			}else{
				this.tagIdName=this.tagId;
			}
		} 
	}
	
	private String format(String str){
		StringBuffer sb = new StringBuffer(str);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}
	
}
