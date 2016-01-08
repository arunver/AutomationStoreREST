package com.store.dao;

import java.util.List;

import com.store.exception.StoreException;
import com.store.model.Entity;
import com.store.model.Pack;
import com.store.model.StorePacks;
import com.store.vo.StoreResponse;

public interface StoreDao {
	
	public StoreResponse getPacks() throws StoreException;
	public String getMessage();
	public StorePacks getPackByUuid(String uuid) throws StoreException;
	public StorePacks getPackByVersion(String version) throws StoreException;
	public Pack getPackByPackId(long id) throws StoreException;
	public List<Entity> getEntityByPackId(long id) throws StoreException;
	//public StorePacks getPackByAuthor(String authorName) throws StoreException;
}
