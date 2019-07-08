package org.study.self.jooq.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.self.jooq.model.Result;

@RestController
@RequestMapping("/*")
public class AppController {
	
	@Value("${user.account}")
	private String account;
	
	@Value("${user.pwd}")
	private String pwd;
	
	@RequestMapping("/get")
	public String get(HttpServletRequest request) {
		System.out.println(request.getHeader("account"));
		return "hello";
	}
	
	@RequestMapping("/login")
	public Result login(String name, String pwd, HttpServletRequest request) {
		boolean res = !StringUtils.isEmpty(name) && name.equals(account) && !StringUtils.isEmpty(pwd) && this.pwd.equals(pwd);
		return res == true ? Result.OK : Result.error("登录失败");
	}
}
