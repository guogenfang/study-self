package org.study.base.lang;

import org.apache.commons.lang.StringUtils;

public class StringTest {
	public static void main(String[] args) {
		String str = "sbc";
		System.out.println();
	}
	
	public void inset(){
		String str = "sbc";
		StringBuffer stringBuffer = new StringBuffer(str);
		str = stringBuffer.insert(str.indexOf("sb"), "aab").toString();
		System.out.println(str);
	}
	
	public void replace(){
		String desc = "package  com.xinshiyun.com; ";
		String str = StringUtils.replaceEach(desc, 
								new String[]{"package", " ", "\r\n", ";"},
								new String[]{"", "", "", ""});
		System.out.println(str);
	}
}
