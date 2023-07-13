package com.banma.bean;

import com.banma.annotation.Column;

public class Tags {
	@Column(isId = true)
	private int id;
	
	private String name;
	 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Tags [id=" + id + ", name=" + name + "]";
	}
	
}
