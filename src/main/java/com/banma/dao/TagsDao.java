package com.banma.dao;

import java.util.List;

import com.banma.bean.Tags;
import com.banma.orm.DBSessionFacory.DBSession;

public class TagsDao implements Dao<Tags>{
	
	private DBSession session;
	
	public TagsDao(DBSession session) {
		this.session = session;
	}
	
	@Override
	public List<Tags> findAll(){

		List<Tags> list = null;
		try {
			list = session.list(Tags.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int save(Tags obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Tags obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Tags obj) {
		// TODO Auto-generated method stub
		return 0;
	}
	 
	
}
