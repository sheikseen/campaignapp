package com.jvdm.campaign.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.jvdm.campaign.dto.CampDto;
import com.jvdm.campaign.service.CampService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/camp")
public class CampController {

    @Autowired
    CampService campService;

    @PostMapping("/add")
    public ResponseEntity<?> addCamp(@RequestBody CampDto campDto) {
        if (campService.existsByTitle(campDto.getTitle())) {
            return ResponseEntity.badRequest().body("Error: camp title is already taken!");
        }
        if (!campService.isValidDate(campDto.getStartDate(),campDto.getEndDate())) {
            return ResponseEntity.badRequest().body("Error: start date must be before end date and both dates must be in the future");
        }
        String campid = campService.saveCamp(campDto);
        String message = String.format("Camp %s registered successfully!", campid);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCamp() {
        List<CampDto> camps = campService.findAllCamps();
        if (camps.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Camps found");
        } else {
            return ResponseEntity.ok().body(camps);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCampById(@PathVariable("id") @Valid String campId) {
        if (campId.isBlank()) {
            return ResponseEntity.badRequest().body("Camp Id cannot be null");
        }
        CampDto campDto = campService.findCampById(campId);
        if (campDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Users found");
        } else {
            return ResponseEntity.ok().body(campDto);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateCamp(@PathVariable("id") @Valid String campId, @Valid @RequestBody CampDto campDto) {
        if (campDto == null || campId.isBlank()) {
            return ResponseEntity.badRequest().body("Request cannot be empty ");
        }
        if (!campId.equals(campDto.getId())) {
            return ResponseEntity.badRequest().body("Invalid camp ID in the request");
        }
        if (!campService.isValidDate(campDto.getStartDate(),campDto.getEndDate())) {
            return ResponseEntity.badRequest().body("Error: start date must be before end date and both dates must be in the future");
        }
        CampDto camp = campService.updateCamp(campDto);
        if (camp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Camp found");
        } else {
            return ResponseEntity.ok().body(camp);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") @Valid String campId) {
        if (campId.isBlank()) {
            return ResponseEntity.badRequest().body("Camp ID cannot be empty");
        }
        CampDto camp = campService.deleteCampById(campId);
        if (camp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Camp not found with ID: " + campId);

        }
        return ResponseEntity.ok().body("Camp deleted successfully");
    }
    
}


