package com.hastag.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hastag.DTO.KeywordDTO;
import com.hastag.service.KeywordService;

@RestController
@RequestMapping("/keywords")
public class KeywordController {

	 @Autowired
	    private KeywordService keywordService;

    @PostMapping("/save")
    public ResponseEntity<String> saveKeywordWithHashtags(@RequestBody KeywordDTO keywordDTO) {
        try {
            keywordService.saveKeywordWithHashtags(keywordDTO.getKeyword(), keywordDTO.getHashtags());
            return new ResponseEntity<>("Keyword and hashtags saved successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save keyword and hashtags", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @PostMapping("/savemany")
    public ResponseEntity<String> saveKeywordsWithHashtags(@RequestBody List<KeywordDTO> keywordDTOList) {
        try {
            for (KeywordDTO keywordDTO : keywordDTOList) {
              //  keywordService.saveKeywordWithHashtags(keywordDTO.getKeyword(), keywordDTO.getHashtags());
            	keywordService.saveKeywordWithHashtags2(keywordDTO);
            }
            return new ResponseEntity<>("Keywords and hashtags saved successfully", HttpStatus.CREATED);
        } catch (Exception e) {
        	System.out.println("ERRO IS"+e);
        	
            return new ResponseEntity<>("Failed to save keywords and hashtags", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findkeyword/{keyword}")
    public ResponseEntity<List<String>> getHashtagsByKeyword(@PathVariable String keyword) {
        try {
            List<String> hashtags = keywordService.getHashtagsByKeyword(keyword);
            return new ResponseEntity<>(hashtags, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findallkeywords")
    public ResponseEntity<List<String>> findAllKeywords() {
        try {
            List<String> allKeywords = keywordService.getAllKeywords();
            return new ResponseEntity<>(allKeywords, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    
}