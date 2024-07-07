package com.jvdm.campaign.service.impl;

import com.jvdm.campaign.dto.SignupRequest;
import com.jvdm.campaign.dto.UserDto;
import com.jvdm.campaign.entity.Role;
import com.jvdm.campaign.entity.User;
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

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setName(signupRequest.name());
        user.setPlace(signupRequest.place());
        user.setPhone(signupRequest.phone());
        user.setAge(signupRequest.age());
        user.setGender(signupRequest.gender());
        user.setEmail(signupRequest.email());

        // user.setUsername(signupRequest.username());
        user.setPassword(passwordEncoder.encode(signupRequest.password()));

        user.setRoles(Set.of(Role.RoleId.GUEST));

        User savedUser = userRepository.save(user);

    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // @Override
    // public User findUserByUsername(String username) {
    // return null;
    // }

    // @Override
    // public boolean existsByUsername(String username) {
    // return userRepository.existsByUsername(username);
    // }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> principals = userRepository.findAll();
        return principals.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user) {
        return new UserDto()
                .setUserId(user.getUserId())
                .setName(user.getName())
                .setPlace(user.getPlace())
                .setPhone(user.getPhone())
                .setEmail(user.getEmail())
                .setAge(user.getAge())
                .setGender(user.getGender());
    }

    private Role checkRoleExist() {
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
            Optional<User> optionalPrincipal = userRepository.findById(userDto.getUserId());
            if (optionalPrincipal.isPresent()) {
                User user = optionalPrincipal.get();
                // Update only if the user is found
                user.setName(userDto.getName());
                user.setPlace(userDto.getPlace());
                user.setPhone(userDto.getPhone());
                user.setAge(userDto.getAge());
                user.setGender(userDto.getGender());
                user.setEmail(userDto.getEmail());
                // cannot update id
                // you cannot change password and Role
                // update password should be a seperate method
                userRepository.save(user); // Save changes

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
        Optional<User> optionalPrincipal = userRepository.findById(userId);
        if (optionalPrincipal.isPresent()) {
            User user = optionalPrincipal.get();
            // Update only if the user is found
            userRepository.delete(user);
            return mapToUserDto(user);
        } else {
            return null;
        }
    }

}
