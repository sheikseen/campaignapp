package com.jvdm.campaign.repository;

import com.jvdm.campaign.dto.UserDto;
import com.jvdm.campaign.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);

    // Optional<User> findByUsername(String username);

    // boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    /*
     * @Transactional
     * 
     * @Modifying
     * 
     * @Query("UPDATE User u SET u.name = :#{#principal.name},u.phone = :#{#principal.phone}, u.username = :#{#principal.username}, u.email = :#{#principal.email} WHERE u.id = :#{#principal.id}"
     * )
     * void updateUserDetails(@Param("principal") Principal principal);
     */
}
