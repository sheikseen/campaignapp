package com.jvdm.campaign.repository;

import com.jvdm.campaign.entity.Dependent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DependentRepository extends JpaRepository<Dependent, String> {
}
