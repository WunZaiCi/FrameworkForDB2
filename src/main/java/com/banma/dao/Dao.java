package com.banma.dao;

import java.util.List;

public interface Dao<T> {
	public List<T> findAll();
    public int save(T obj);
    public int update(T obj);
    public int delete(T obj);
}
