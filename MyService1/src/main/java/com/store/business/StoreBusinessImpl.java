package com.store.business;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;

import com.store.bo.StoreConstants;
import com.store.dao.StoreDao;
import com.store.exception.StoreException;
import com.store.exception.StoreExceptionDetails;
import com.store.model.Entity;
import com.store.model.Pack;
import com.store.model.StorePacks;
import com.store.vo.StoreResponse;

public class StoreBusinessImpl implements StoreBusiness {
	
	private StoreDao storeDao;
	private MessageSource messages;
	
	public StoreDao getStoreDao() {
		return storeDao;
	}

	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return storeDao.getMessage();
	}

	@Override
	public StoreResponse getPacks() throws StoreException{
		// TODO Auto-generated method stub
		//StorePacks storePacks = null;
		StoreResponse storeResponse = new StoreResponse();
		try
		{
			storeResponse =  storeDao.getPacks();
		}
		catch(StoreException e)
		{
			throw(e);
		}
		return storeResponse;
	}

	public MessageSource getMessages() {
		return messages;
	}

	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}

	@Override
	public StorePacks getPackByUuid(String uuid) throws StoreException {
		// TODO Auto-generated method stub
		StorePacks storePacks = null;
		try {
			storePacks =storeDao.getPackByUuid(uuid);
		} catch (StoreException e) {
			// TODO Auto-generated catch block
			StoreExceptionDetails storeException = new StoreExceptionDetails();
			storeException.setErrorCode(StoreConstants.ERROR_CODE_604);
			storeException.setErrorMessage(this.messages.getMessage("ERROR_MSG_604",null, Locale.US));
			throw new StoreException(storeException);
		}
		return storePacks;
	}

	@Override
	public StorePacks getPackByVersion(String version) throws StoreException {
		// TODO Auto-generated method stub
		StorePacks storePacks = null;
		try {
			storePacks = storeDao.getPackByVersion(version);
		} catch (StoreException e) {
			// TODO Auto-generated catch block
			StoreExceptionDetails storeException = new StoreExceptionDetails();
			storeException.setErrorCode(StoreConstants.ERROR_CODE_604);
			storeException.setErrorMessage(this.messages.getMessage("ERROR_MSG_604",null, Locale.US));
			throw new StoreException(storeException);
		}
		return storePacks;
	}

	@Override
	public Pack getPackByPackId(long id) throws StoreException{
		// TODO Auto-generated method stub
		return storeDao.getPackByPackId(id);
	}

	@Override
	public List<Entity> getEntityByPackId(long id) throws StoreException {
		// TODO Auto-generated method stub
		
		return storeDao.getEntityByPackId(id);
	}

	/*@Override
	public StorePacks getPackByAuthor(String authorName) throws StoreException {
		// TODO Auto-generated method stub
		StorePacks storePacks=null;
		try
		{
			storePacks= storeDao.getPackByAuthor(authorName);
		}
		catch(StoreException e)
		{
			
		}
		return null;
	}*/

}
