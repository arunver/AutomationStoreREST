package com.store.exception;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Arun.Verma
 *
 */

@XmlRootElement(name="StoreException")
public class StoreException extends Exception implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement
	private StoreExceptionDetails storeDetails;
	
	public StoreException(StoreExceptionDetails storeDetails)
	{
		
		this.storeDetails=storeDetails;		
	}

	public StoreExceptionDetails getStoreDetails() {
		return storeDetails;
	}

	public void setStoreDetails(StoreExceptionDetails storeDetails) {
		this.storeDetails = storeDetails;
	}
	
	

}
