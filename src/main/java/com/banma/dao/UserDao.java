package com.banma.dao;

import java.util.List;

import com.banma.bean.User;
import com.banma.orm.DBSessionFacory.DBSession;

public class UserDao implements Dao<User>{
	private DBSession session;
	public UserDao(DBSession session) {
		this.session=session;
	}
	
	public int save(User obj) {
		int rows=0;
		try {
			rows=session.save(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	public int update(User obj) {
		int rows=0;
		try {
			rows=session.update(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	public int delete(User obj) {
		int rows=0;
		try {
			rows=session.delete(obj.getClass(),obj.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	public List<User> findAll(){
		try {
			return session.list(User.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	/**
	 * 用户登陆
	 * @param name
	 * @param passwd
	 * @return
	 */
	public User login(String name, String passwd) {
		User user=new  User();
		user.setUsername(name);
		user.setPassword(passwd);
		user=session.find(user);
		return user;
	}
	
	
}
