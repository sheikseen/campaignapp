package com.jvdm.campaign.service;

import com.jvdm.campaign.dto.BookingDto;

import java.util.List;

public interface BookingService {

    List<String> validate(BookingDto bookingDto);

    BookingDto registerEvent(BookingDto bookingDto, String username);
}
