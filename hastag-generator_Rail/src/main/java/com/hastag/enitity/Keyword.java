package com.hastag.enitity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class Keyword {
	
	@Id
	private String keyword;
	
	@ElementCollection
	private List<String> hashtags;

	

	public Keyword() {
		super();
		  this.hashtags = new ArrayList<>(); // Initialize the hashtags list
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<String> hashtags) {
		this.hashtags = hashtags;
	}

	

	
	
	

}
