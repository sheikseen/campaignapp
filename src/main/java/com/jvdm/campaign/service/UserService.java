package com.jvdm.campaign.service;


import com.jvdm.campaign.dto.SignupRequest;
import com.jvdm.campaign.dto.UserDto;
import com.jvdm.campaign.entity.Principal;

import java.util.List;


public interface UserService {

    void saveUser(SignupRequest userDto);

    Principal findUserByEmail(String email);

    Principal findUserByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    UserDto deleteUserById(String userId);
    
    List<UserDto> findAllUsers();

    UserDto findUserByID(String userId);

    UserDto updateUser(UserDto userDto);
}