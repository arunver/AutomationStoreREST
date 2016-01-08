package com.store.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import com.store.business.StoreBusiness;
import com.store.exception.StoreException;
import com.store.exception.StoreExceptionDetails;
import com.store.model.Entity;
import com.store.model.Pack;
import com.store.model.StorePacks;
import com.store.vo.StoreResponse;

public class StoreManagerImpl implements StoreManager{
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	private StoreBusiness storeBusiness;
	private StoreResponse storeResponse;
	
	

	public UriInfo getUriInfo() {
		return uriInfo;
	}

	public void setUriInfo(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public StoreResponse getStoreResponse() {
		return storeResponse;
	}

	public void setStoreResponse(StoreResponse storeResponse) {
		this.storeResponse = storeResponse;
	}

	public StoreBusiness getStoreBusiness() {
		return storeBusiness;
	}
	
	public void setStoreBusiness(StoreBusiness storeBusiness) {
		this.storeBusiness = storeBusiness;
	}



	@Override
	public String getMessage() throws WebApplicationException{
		// TODO Auto-generated method stub
		String msg="";
		try{
		msg= storeBusiness.getMessage();
		}catch(StoreException e)
		{
			throwException(e.getStoreDetails());
		}
		return msg;
	}

	@Override
	public StoreResponse getPacks() throws WebApplicationException{
		// TODO Auto-generated method stub
		//StorePacks storePacks = null;
		StoreResponse storeResponse = new StoreResponse();
		try {
			storeResponse =  storeBusiness.getPacks();
			
		} catch (StoreException e) {
			
			StoreResponse response = new StoreResponse();
			response.setStatus("FAILURE");
			response.setStoreException(e.getStoreDetails());
			response.setStorePacks(null);
			response.setSuccessResponse(null);
			
			return response;
		}
		return storeResponse;
	}

	@Override
	public StorePacks getPackByUuid(String uuid) throws WebApplicationException{
		// TODO Auto-generated method stub
		
		StorePacks storePacks = null;
		try {
			storePacks= storeBusiness.getPackByUuid(uuid);
		} catch (StoreException e) {
			// TODO Auto-generated catch block
			throwException(e.getStoreDetails());
		}
		
		return storePacks;
	}

	@Override
	public StorePacks getPackByVersion(String version) throws WebApplicationException {
		// TODO Auto-generated method stub
		StorePacks storePacks = null;
		try {
			storePacks =  storeBusiness.getPackByVersion(version);
		} catch (StoreException e) {
			// TODO Auto-generated catch block
			throwException(e.getStoreDetails());
		}
		
		return storePacks;
	}

	@Override
	public List<Entity> getEntityByPackId(long id) throws WebApplicationException{
		// TODO Auto-generated method stub
		List<Entity> entities =  new ArrayList<Entity>();
		
			try {
				entities = storeBusiness.getEntityByPackId(id);
			} catch (StoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return entities;
	}

	@Override
	public Pack getPackByPackId(long id) throws WebApplicationException{
		// TODO Auto-generated method stub
		Pack pack=null;
		
		try {
			pack = storeBusiness.getPackByPackId(id);
		} catch (StoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pack;
	}
	
	/*@Override
	public StorePacks getPackByAuthor(String authorName) throws WebApplicationException {
		// TODO Auto-generated method stub
		StorePacks storePacks=null;
		
		try
		{
			storePacks= storeBusiness.getPackByAuthor(authorName);
		}
		catch(StoreException e)
		{
			e.printStackTrace();
		}
		return null;
	}*/
	
	
	
	public static void throwException(StoreExceptionDetails storeException) {
    	StoreResponse storeResponse = new StoreResponse();
    	storeResponse.setStatus("failure");
    	storeResponse.setStoreException(storeException);
    	
        ResponseBuilder builder = Response.status(Response.Status.NOT_ACCEPTABLE);
        builder.type("application/json");
        
        builder.entity(storeResponse);
        throw new WebApplicationException(builder.build());
      }


	
	
}
