package com.banma.user.action;

import java.util.HashMap;

import com.banma.bean.User;
import com.banma.dao.UserDao;
import com.banma.mvc.action.SupportAction;
import com.banma.user.action.form.UserForm;

public class LoginAction extends SupportAction{
	
	UserDao dao;
	UserForm form;
	String name;
	String passwd;
	
	
	
	@Override
	public String execute() {
		if(dao==null || form==null) {
			return "no";
		}
		System.out.println(name+","+passwd);
		
		User loginUser = dao.login(name,passwd);
		
		if("aaa".equals(name)&& "123".equals(passwd)) {
			session.setAttribute("username", name);
			return "ok";
		}
		request.setAttribute("error", "用户名或密码错误");
		return "no";
	}
}
