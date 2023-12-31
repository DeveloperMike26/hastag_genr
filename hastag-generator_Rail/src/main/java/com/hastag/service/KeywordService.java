package com.hastag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hastag.DTO.KeywordDTO;
import com.hastag.enitity.Keyword;
import com.hastag.repository.KeywordRepository;
import com.hastag.repository.NotavailableKeyRepository;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KeywordService {

	@Autowired
    private KeywordRepository keywordRepository;
	
	@Autowired
	NotavailableKeyRepository notavailableKeyRepository;
	@Autowired
	NotavailableService notavailableService;

	@Transactional
	public void saveKeywordWithHashtags(String keyword, List<String> hashtags) {
	    Keyword existingKeyword = keywordRepository.findById(keyword).orElse(new Keyword());
	    existingKeyword.setKeyword(keyword);
	    existingKeyword.getHashtags().addAll(hashtags);
	    keywordRepository.save(existingKeyword);
	}
	@Transactional
	public void saveKeywordWithHashtags2(KeywordDTO keywordDTO) {
        Keyword keyword = new Keyword();
        keyword.setKeyword(keywordDTO.getKeyword());
        
        // Create a new ArrayList or HashSet to ensure a distinct collection for each keyword
        List<String> hashtags = new ArrayList<>(keywordDTO.getHashtags());
        
        
        keyword.setHashtags(hashtags);
        
        keywordRepository.save(keyword);
    }
	
	
	

	    

	public List<String> getHashtagsByKeyword(String keyword) {
        Optional<Keyword> optionalKeyword = keywordRepository.findByKeyword(keyword);

        // Direct keyword search
        if (optionalKeyword.isPresent()) {
            return optionalKeyword.get().getHashtags();
        } else {
        	
        	//adding in the not availble table
        	notavailableService.saveInNotavailableTable(keyword);
        	
        	
            // Keyword substring search
            List<String> keywordSubstrings = generateSubstrings(keyword.toLowerCase());

            for (String substring : keywordSubstrings) {
                List<Keyword> keywords = keywordRepository.findKeywordsBySimilarTerm(substring);

                for (Keyword k : keywords) {
                    if (k.getKeyword().equalsIgnoreCase(substring) || k.getKeyword().toLowerCase().contains(substring)) {
                        return k.getHashtags();
                    }
                }
            }

            // Direct hashtag search by keyword
            List<Keyword> hashtagsByKeyword = keywordRepository.findByHashtagsContainingIgnoreCase("#" + keyword);

            if (!hashtagsByKeyword.isEmpty()) {
                return hashtagsByKeyword.get(0).getHashtags();
            } else {
                // Hashtag substring search
                List<String> hashtagSubstrings = generateSubstrings(keyword.toLowerCase());

                List<String> matchedHashtags = new ArrayList<>();

                for (String substring : hashtagSubstrings) {
                    List<Keyword> hashtags = keywordRepository.findByHashtagsContainingIgnoreCase("#" + substring);

                    for (Keyword k : hashtags) {
                        matchedHashtags.addAll(k.getHashtags());
                    }
                }

                if (!matchedHashtags.isEmpty()) {
                    return matchedHashtags.stream().distinct().collect(Collectors.toList());
                }
            }
        }

        return Collections.emptyList(); // Return an empty list if no related keywords are found
    }

    // Function to generate all possible substrings
    private List<String> generateSubstrings(String keyword) {
        List<String> substrings = new ArrayList<>();
        for (int i = 0; i < keyword.length(); i++) {
            for (int j = i + 1; j <= keyword.length(); j++) {
                substrings.add(keyword.substring(i, j));
            }
        }
        return substrings;
    }
	public List<String> getAllKeywords() {
		return keywordRepository.findAllKeywords();
	}
}
	
	

	
	
	
	
	
	