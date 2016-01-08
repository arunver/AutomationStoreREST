package com.store.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="StoreSuccessResponse")
public class SuccessResponse {
	
	private String message;
	private long transactionId;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	
	

}
