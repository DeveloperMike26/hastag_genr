package com.hastag.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hastag.enitity.Keyword;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, String> {
	  // Method to fetch hashtags by keyword
    Optional<Keyword> findByKeyword(String keyword);
    
    List<Keyword> findByHashtagsContainingIgnoreCase(String searchTerm);
    
    @Query("SELECT k FROM Keyword k WHERE LOWER(k.keyword) LIKE LOWER(concat('%', :searchTerm, '%'))")
    List<Keyword> findKeywordsBySimilarTerm(@Param("searchTerm") String searchTerm);

    
    @Query("SELECT k.keyword FROM Keyword k") // Assuming "keyword" is the field name in your Keyword entity
    List<String> findAllKeywords();
    
    
 }
    

