package com.jvdm.campaign.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jvdm.campaign.entity.Camp;

public interface CampRepository extends JpaRepository<Camp , String>{

    boolean existsByTitle(String title);
    boolean existsById(String id);

}
