package com.banma.mvc.action;

import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 业务控制器的父类
 * @author WHASSUPMAN
 *
 */
public class SupportAction {
	protected HttpServletRequest request;
	protected HttpSession session;
	protected final String SUCCESS="success";
	protected final String FAIL="fail";
	protected HashMap<String, String> params;
	
	/**
	 * 业务控制器执行的方法
	 * @return
	 */
	public String execute() {
		return SUCCESS;
	}
	
	/**
	 * 获取请求参数列表
	 * @return
	 */
	public void parseParams() {
		params = new HashMap<String, String>();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String name= parameterNames.nextElement();
			params.put(name, request.getParameter(name));
		}
	}
	
	
	/**
	 * 将请求参数封装到Form实体类中
	 * @param form
	 * @return
	 * @throws Exception  
	 */
	public Object parseParams4Form(Class<?> formCls) throws Exception {
		if(params==null|| params.isEmpty()) {
			return null;
		}
		Object formObj = formCls.newInstance();
		for (Field f : formCls.getDeclaredFields()) {
			f.setAccessible(true);
			f.set(formObj, params.get(f.getName()));
		}
		return formObj;
	}
}
