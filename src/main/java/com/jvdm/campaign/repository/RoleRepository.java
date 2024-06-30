package com.jvdm.campaign.repository;

import com.jvdm.campaign.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role, Role.RoleId> {

    Optional<Role> findById(Role.RoleId name);
}
