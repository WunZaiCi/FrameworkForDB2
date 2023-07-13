package com.banma.user.action.form;


/**
 * 封装用户请求参数的表单实体类
 * @author WHASSUPMAN
 *
 */
public class UserForm {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	private String name;
	private String passwd;
	
}
