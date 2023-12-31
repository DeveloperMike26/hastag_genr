package com.hastag.enitity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Notavailable {
	
	@Id
	String key_name;

	public String getKey_name() {
		return key_name;
	}

	public void setKey_name(String key_name) {
		this.key_name = key_name;
	}

}
