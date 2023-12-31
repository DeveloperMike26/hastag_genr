package com.hastag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.hastag.enitity.Notavailable;

@Repository
public interface NotavailableKeyRepository extends JpaRepository<Notavailable, String> {



}
