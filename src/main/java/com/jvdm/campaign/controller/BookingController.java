package com.jvdm.campaign.controller;

import com.jvdm.campaign.dto.BookingDto;
import com.jvdm.campaign.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/register")
    public ResponseEntity<?> registerForEvent(@Valid @RequestBody BookingDto bookingDto) {
        var errorMessages = bookingService.validate(bookingDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        var user = authentication.getPrincipal();
        if (errorMessages.isEmpty()) {
            BookingDto bookingDto1 = bookingService.registerEvent(bookingDto, currentUsername);
        } else {
            return ResponseEntity.badRequest().body(errorMessages);
        }
        return ResponseEntity.ok("Booking Successful");
    }

    // registerUSer
    // registerBulkUser
    // EditRegistration
    // DeleteRegistration
    // ListRegisteredUserBasedOnCampid Admin
    // ListRegisteredCampPerUserID(phoneNO)
}
