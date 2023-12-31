package com.hastag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hastag.enitity.Notavailable;
import com.hastag.repository.NotavailableKeyRepository;

import jakarta.transaction.Transactional;

@Service
public class NotavailableService {
	@Autowired
    private  NotavailableKeyRepository notavailableKeyRepository;

   

    @Transactional
    public void saveInNotavailableTable(String keyword) {
        Notavailable notavailable = new Notavailable();
        notavailable.setKey_name(keyword);
        notavailableKeyRepository.save(notavailable);
    }
}