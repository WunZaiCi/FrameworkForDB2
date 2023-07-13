package com.banma.user.action;

import java.util.UUID;

import com.banma.bean.User;
import com.banma.dao.UserDao;
import com.banma.mvc.action.SupportAction;

public class RegistAction extends SupportAction  {
	
	UserDao dao;
	User user;
	
	@Override
	public String execute() {
		if(dao==null||user==null) {
			session.setAttribute("error", "12001 请求失败");
			
		}
		//注册之前判断用户名是否存在 没有实现
		
		
		user.setId(UUID.randomUUID().toString().replace("-", ""));
		if(dao.save(user)>0) {
			return "login";
		}
		
		
		return "regist";
	}
	
}
