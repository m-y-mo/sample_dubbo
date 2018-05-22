package com.sample.data;

import java.io.Serializable;

public class User implements Serializable {
	
	private String id;
	private String sensitiveData;
	private String nonSensitiveData;
	
	public User(String id, String sensitiveData, String nonSensitiveData) {
		this.id = id;
		this.sensitiveData = sensitiveData;
		this.nonSensitiveData = nonSensitiveData;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSensitiveData() {
		return sensitiveData;
	}
	
	public void setSensitiveData(String sensitiveData) {
		this.sensitiveData = sensitiveData;
	}
	
	public String getNonSensitiveData() {
		return nonSensitiveData;
	}
	
	public void setNonSensitiveData(String nonSensitiveData) {
		this.nonSensitiveData = nonSensitiveData;
	}
}

