package com.hastag.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hastag.enitity.Notavailable;
import com.hastag.repository.NotavailableKeyRepository;

@RestController
@RequestMapping("/notavailable")
public class NotavailableController {

	
	@Autowired
    private  NotavailableKeyRepository notavailableKeyRepository;


    @GetMapping("/getall")
    public ResponseEntity<String> getAllNotavailable() {
        List<Notavailable> notavailableList = notavailableKeyRepository.findAll();
        
        // Convert the list of Notavailable entities to a comma-separated string
        String commaSeparatedValues = notavailableList.stream()
                .map(Notavailable::getKey_name)
                .collect(Collectors.joining(", "));
        
        return ResponseEntity.ok(commaSeparatedValues);
        
    }
}