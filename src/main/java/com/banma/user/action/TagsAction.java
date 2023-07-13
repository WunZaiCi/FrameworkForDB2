package com.banma.user.action;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.banma.bean.Tags;
import com.banma.dao.TagsDao;
import com.banma.mvc.action.SupportAction;

public class TagsAction extends SupportAction {
	TagsDao dao;

	@Override
	public String execute() {		
		List<Tags> tagsList = dao.findAll();
		//将list对象转成json数组
		String json = JSON.toJSONString(tagsList);
		return json;
	}
}
