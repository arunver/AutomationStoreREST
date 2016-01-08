package com.store.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class StorePacks {
	
	
	private List<Pack> wfapacks;
	
	public List<Pack> getWfapacks() {
		return wfapacks;
	}
	public void setWfapacks(List<Pack> wfapacks) {
		this.wfapacks = wfapacks;
	}
	private String timestamp;
	
	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
	

}
