package org.study.base.lang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZhengZe {
	public static void main(String[] args) {
		String str = "/**\r\n* 用户注册\r\n*\r\n* @param user 用户信息(telephone,pwd,name,eMail)\r\n* @param checkCode     手机验证码\r\n* @param checkCodeType 验证码类型\r\n*/";
		System.out.println(str);
		System.out.println("----------------------");
		String regEx = "@param " + "user" + "\\W";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		Boolean res = m.find();
		if(res){
			System.out.println(str.substring(0, m.end()));
		}
	}
}
