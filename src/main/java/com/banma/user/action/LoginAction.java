package com.banma.user.action;

import java.util.HashMap;

import com.banma.mvc.action.SupportAction;

public class LoginAction extends SupportAction{
	
	@Override
	public String execute() {
		
		String name = params.get("name");
		String passwd = params.get("passwd");
		
		if("aaa".equals(name)&& "123".equals(passwd)) {
			session.setAttribute("username", name);
			return "ok";
		}
		request.setAttribute("error", "用户名或密码错误");
		return "no";
	}
}
