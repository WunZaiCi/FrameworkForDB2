package com.banma.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBSource {
	private String driver;
	private String url;
	private String username;
	private String password;
	
	
	public DBSource() {
		
	}
	
	public DBSource(Properties props) {
		
		this.driver = props.getProperty("driver");
		this.url = props.getProperty("url");
		this.username = props.getProperty("username");
		this.password = props.getProperty("password");
		
	}
	
	
	public Connection openConnection() throws Exception {
		Class.forName(driver);
		return DriverManager.getConnection(url,username,password);
	}
	
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
