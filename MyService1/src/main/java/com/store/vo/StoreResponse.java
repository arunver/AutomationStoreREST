package com.store.vo;

import javax.xml.bind.annotation.XmlRootElement;

import com.store.exception.StoreExceptionDetails;
import com.store.model.StorePacks;

@XmlRootElement(name="StoreResponse")
public class StoreResponse {
	
	private String status;
	private StoreExceptionDetails storeException;
	private SuccessResponse successResponse;
	private StorePacks storePacks;
	
	public StorePacks getStorePacks() {
		return storePacks;
	}
	public void setStorePacks(StorePacks storePacks) {
		this.storePacks = storePacks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public StoreExceptionDetails getStoreException() {
		return storeException;
	}
	public void setStoreException(StoreExceptionDetails storeException) {
		this.storeException = storeException;
	}
	public SuccessResponse getSuccessResponse() {
		return successResponse;
	}
	public void setSuccessResponse(SuccessResponse successResponse) {
		this.successResponse = successResponse;
	}
	
	
	

}
