package com.jvdm.campaign.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jvdm.campaign.entity.Event;

public interface EventRepository extends JpaRepository<Event, String> {

    boolean existsByTitle(String title);

    boolean existsById(String id);

}
