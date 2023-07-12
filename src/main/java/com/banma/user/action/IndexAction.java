package com.banma.user.action;

import com.banma.mvc.action.SupportAction;

public class IndexAction extends SupportAction {
	@Override
	public String execute() {
		//判断用户是否登陆:session- username
		if(session.getAttribute("username")==null) {
			return FAIL;//返回失败的结果
		}
		return super.execute();
	}
}
