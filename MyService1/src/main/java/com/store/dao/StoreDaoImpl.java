package com.store.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.store.Mapper.EntityMapper;
import com.store.Mapper.PackMapper;
import com.store.bo.StoreConstants;
import com.store.exception.StoreException;
import com.store.exception.StoreExceptionDetails;
import com.store.model.Entity;
import com.store.model.Pack;
import com.store.model.StorePacks;
import com.store.vo.StoreResponse;
import com.store.vo.SuccessResponse;

public class StoreDaoImpl implements StoreDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	private StorePacks storePacks;
	private static Logger logger = Logger
			.getLogger(PackMapper.class.getName());
	
	@Autowired
	private MessageSource messages;
	
	private SuccessResponse successResponse;
	
	 public MessageSource getMessages() {
		return messages;
	}

	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}

	public SuccessResponse getSuccessResponse() {
		return successResponse;
	}

	public void setSuccessResponse(SuccessResponse successResponse) {
		this.successResponse = successResponse;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplate = new JdbcTemplate(dataSource);
	   }
	
	public StorePacks getStorePacks() {
		return storePacks;
	}

	public void setStorePacks(StorePacks storePacks) {
		this.storePacks = storePacks;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	@Override
	public StoreResponse getPacks() throws StoreException{
		
		logger.info("Entering into getPacks method of StoreDaoImpl");
		// TODO Auto-generated method stub
		StoreResponse response= new StoreResponse();
		String query="Select * from packdetails";
		//List<Pack> packList= getJdbcTemplate().query(query, new PackMapper());
		
		List<Pack> newPackList = new ArrayList<Pack>();
		try{
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
		
		for(Map row: rows)
		{
			Pack pack= new Pack();
			pack = packMapper(row,pack);
			long id= (long)row.get("id");
			String entityQuery= "Select * from packentities where packId=?";
			List<Entity> entities = getJdbcTemplate().query(entityQuery, new Object[]{id}, new EntityMapper());
			
			pack.setEntities(entities);
			newPackList.add(pack);
		}
		
		storePacks.setWfapacks(newPackList);
		storePacks.setTimestamp("11/7/2015");
		
		successResponse.setMessage(this.messages.getMessage("SUCCESS_MSG_601",null, Locale.US));
		successResponse.setTransactionId(10001);
		
		response.setStatus(StoreConstants.SUCCESS);
		response.setSuccessResponse(successResponse);
		response.setStorePacks(storePacks);		
		response.setStoreException(new StoreExceptionDetails());
		}
		catch(DataAccessException e)
		{
			logger.error("Exception occurred while executing getPacks : "+this.messages.getMessage("ERROR_MSG_601", null, Locale.US));
			StoreExceptionDetails exception = new StoreExceptionDetails();
			exception.setErrorCode(StoreConstants.ERROR_CODE_601);
			exception.setErrorMessage(this.messages.getMessage("ERROR_MSG_601", null, Locale.US));
			throw new StoreException(exception);
		}
		return response;
		
	}

	@Override
	public String getMessage() {
		
		return "This is my message";
	}

	@Override
	public StorePacks getPackByUuid(String uuid) throws StoreException {
		// TODO Auto-generated method stub
		StoreResponse response= new StoreResponse();
		List<Pack> packList = new ArrayList<Pack>();
		try{
		
			String query = "Select * from packdetails where uuid=?";
			List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{uuid});
			
			for(Map row: rows)
			{
				Pack pack= new Pack();
				pack =  packMapper(row,pack);
				
				long id= (long)row.get("id");
				String entityQuery= "Select * from packentities where packId=?";
				List<Entity> entities = getJdbcTemplate().query(entityQuery, new Object[]{id}, new EntityMapper());
				
				pack.setEntities(entities);
				packList.add(pack);
			}
			
			successResponse.setMessage(this.messages.getMessage("SUCCESS_MSG_602",null, Locale.US));
			successResponse.setTransactionId(10002);
			
		
			storePacks.setWfapacks(packList);
			storePacks.setTimestamp("");
			
			response.setStatus(StoreConstants.SUCCESS);
			response.setSuccessResponse(successResponse);
			response.setStorePacks(storePacks);
			response.setStoreException(new StoreExceptionDetails());
		}
		catch(DataAccessException obj)
		{
			StoreExceptionDetails exception = new StoreExceptionDetails();
			exception.setErrorCode(StoreConstants.ERROR_CODE_602);
			exception.setErrorMessage(this.messages.getMessage("ERROR_MSG_602", null, Locale.US));
			throw new StoreException(exception);
		}
		
		
		return storePacks;
	}
	

	@Override
	public StorePacks getPackByVersion(String version) throws StoreException{
		
		List<Pack> packList  = new ArrayList<Pack>();
		
		try{
		String query = "Select * from packdetails where version=?";
		List<Map<String, Object>> rows= getJdbcTemplate().queryForList(query, new Object[]{version});
		
		for(Map row: rows)
		{
			Pack pack= new Pack();
			pack= packMapper(row, pack);
			
			long id= (long)row.get("id");
			String entityQuery= "Select * from packentities where packId=?";
			List<Entity> entities = getJdbcTemplate().query(entityQuery, new Object[]{id}, new EntityMapper());
			
			pack.setEntities(entities);
			packList.add(pack);
		}
		
		storePacks.setWfapacks(packList);
		storePacks.setTimestamp("");
		return storePacks;
		}
		catch(DataAccessException e)
		{
			StoreExceptionDetails storeDetails =  new StoreExceptionDetails();
			storeDetails.setErrorCode(StoreConstants.ERROR_CODE_604);
			storeDetails.setErrorMessage(this.messages.getMessage("ERROR_CODE_604", null, Locale.US));
			throw new StoreException(storeDetails);
		}
	}
	
	
	@Override
	public Pack getPackByPackId(long id) throws StoreException {
		// TODO Auto-generated method stub
		logger.info("Entering into getPackByPackId method of StoreDaoImpl");
		String query="Select * from packdetails where id=?";
		try{
		Pack pack= getJdbcTemplate().queryForObject(query, new Object[]{id}, new PackMapper());
		
		String entityQuery = "Select * from packentities where packId=?";
		List<Entity> entities = getJdbcTemplate().query(entityQuery, new Object[]{id}, new EntityMapper());
		pack.setEntities(entities);
		return pack;
		}
		catch(EmptyResultDataAccessException e)
		{
			logger.error("Exception occurred while fetching pack with a given id: "+this.messages.getMessage("ERROR_MSG_603", null, Locale.US));
			StoreExceptionDetails exception = new StoreExceptionDetails();
			exception.setErrorCode(StoreConstants.ERROR_CODE_603);
			exception.setErrorMessage(this.messages.getMessage("ERROR_MSG_603", null, Locale.US));
			throw new StoreException(exception);
			
		}
		
	}
	
	public List<Entity> getEntityByPackId(long id) throws StoreException{
		logger.info("Entering into getEntityByPackId method");
		List<Entity> entities = new ArrayList<Entity>();
		try{
		String query ="Select * from packentities where packId=?";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{id});
		
		for(Map row:rows)
		{
			Entity entity= new Entity();
			entity.setCertifiedBy((String)row.get("certifiedBy"));
			entity.setDescription((String)row.get("description"));
			entity.setName((String)row.get("name"));
			entity.setVersion((String)row.get("version"));
			entity.setUuid((String)row.get("uuid"));
			
			entities.add(entity);
		}
		
		return entities;
		}
		catch(DataAccessException exception)
		{
			logger.equals("Exception occurred while fetch entity by Pack Id : "+this.messages.getMessage("ERROR_CODE_701", null, Locale.US));
			
			StoreExceptionDetails exceptionObj= new StoreExceptionDetails();
			exceptionObj.setErrorCode(StoreConstants.ERROR_CODE_701);
			exceptionObj.setErrorMessage(this.messages.getMessage("ERROR_CODE_701", null, Locale.US));
			throw new StoreException(exceptionObj);
		}
	}
	
	public Pack packMapper(Map row, Pack pack)
	{
		
		pack.setAuthor((String)row.get("author"));
		pack.setCertifiedBy((String)row.get("certifiedBy"));
		pack.setDescription((String)row.get("packDescription"));
		pack.setName((String)row.get("packName"));
		pack.setPackFilePath((String)row.get("packFilePath"));
		pack.setUuid((String)row.get("uuid"));
		pack.setVersion((String)row.get("version"));
		
		return pack;
	}

	
	/*public StorePacks getPackByAuthor(String authorName) throws StoreException {
		String query="Select * from packdetails where author=?";
		
		List<Pack> packList= new ArrayList<Pack>();
		try
		{
			//List<Map<String, Object>> rows= getJdbcTemplate().
		}
		catch(DataAccessException e)
		{
			
		}
		
		return null;
	}*/


}
