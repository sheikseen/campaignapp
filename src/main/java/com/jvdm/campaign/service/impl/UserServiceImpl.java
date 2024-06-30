package com.jvdm.campaign.service.impl;


import com.jvdm.campaign.dto.SignupRequest;
import com.jvdm.campaign.dto.UserDto;
import com.jvdm.campaign.entity.Role;
import com.jvdm.campaign.entity.Principal;
import com.jvdm.campaign.repository.RoleRepository;
import com.jvdm.campaign.repository.UserRepository;
import com.jvdm.campaign.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(SignupRequest signupRequest) {

        Principal principal = new Principal();
        principal.setId(UUID.randomUUID().toString());
        principal.setName(signupRequest.name());
        principal.setEmail(signupRequest.email());
        principal.setPhone(signupRequest.phone());
        principal.setUsername(signupRequest.username());
        principal.setPassword(passwordEncoder.encode(signupRequest.password()));

        principal.setRoles(Set.of(Role.RoleId.GUEST));

        Principal savedPrincipal = userRepository.save(principal);

    }

    @Override
    public Principal findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Principal findUserByUsername(String username) {
        return null;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<Principal> principals = userRepository.findAll();
        return principals.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(Principal principal){
        UserDto userDto = new UserDto()
                        .setId(principal.getId())
                        .setName(principal.getName())
                        .setUsername(principal.getUsername())
                        .setPhone(principal.getPhone())
                        .setEmail(principal.getEmail());
        return userDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public UserDto findUserByID(String userId) {
        return userRepository.findById(userId)
                .map(this::mapToUserDto)
                .orElse(null);

    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        try {
            Optional<Principal> optionalPrincipal = userRepository.findById(userDto.getId());
            if (optionalPrincipal.isPresent()) {
                Principal principal = optionalPrincipal.get();
                // Update only if the user is found
                principal.setName(userDto.getName());
                principal.setEmail(userDto.getEmail());
                principal.setPhone(userDto.getPhone());
                principal.setUsername(userDto.getUsername());
                //cannot update id
                //you cannot change password and Role
                //update password should be a seperate method
                userRepository.save(principal); // Save changes

                return userDto;
            }
        } catch (Exception e) {
            // Log the exception or handle it accordingly
            return null;
        }
        return null; // If user is not found or an exception occurs
    }

    @Override
    public UserDto deleteUserById(String userId) {
        Optional<Principal> optionalPrincipal = userRepository.findById(userId);
        if (optionalPrincipal.isPresent()) {
            Principal principal = optionalPrincipal.get();
            // Update only if the user is found
            userRepository.delete(principal);
            return mapToUserDto(principal);
        } else {
            return null;
        }
    }
 
}

