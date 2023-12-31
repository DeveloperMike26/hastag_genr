package com.hastag.DTO;

import java.util.List;

public class KeywordDTO {
    private String keyword;
    private List<String> hashtags;
	public KeywordDTO(String keyword, List<String> hashtags) {
		super();
		this.keyword = keyword;
		this.hashtags = hashtags;
	}
	
	
	
	public KeywordDTO() {
		super();
		// TODO Auto-generated constructor stub
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
