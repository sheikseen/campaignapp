package com.jvdm.campaign.controller;

import com.jvdm.campaign.dto.UserDto;
import com.jvdm.campaign.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUser() {
        List<UserDto> principals = userService.findAllUsers();
        if (principals.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Users found");
        } else {
            return ResponseEntity.ok().body(principals);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable("id") @Valid String userId) {
        if (userId.isBlank()) {
            return ResponseEntity.badRequest().body("User Id cannot be null");
        }
        UserDto userDto = userService.findUserByID(userId);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Users found");
        } else {
            return ResponseEntity.ok().body(userDto);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateUserByID(@PathVariable("id") @Valid String userId,
            @Valid @RequestBody UserDto userDto) {
        if (userDto == null || userId.isBlank()) {
            return ResponseEntity.badRequest().body("Request cannot be empty ");
        }
        if (!userId.equals(userDto.getId())) {
            return ResponseEntity.badRequest().body("Ids in request don't match. You cannot update Id ");
        }
        UserDto user = userService.updateUser(userDto);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Users found");
        } else {
            return ResponseEntity.ok().body(user);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserByID(@PathVariable("id") @Valid String userId) {
        if (userId.isBlank()) {
            return ResponseEntity.badRequest().body("User ID cannot be empty");
        }

        UserDto deleted = userService.deleteUserById(userId);
        if (deleted == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + userId);
        }

        return ResponseEntity.ok().body("User deleted successfully");
    }

}
