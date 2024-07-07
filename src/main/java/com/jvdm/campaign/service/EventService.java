package com.jvdm.campaign.service;

import java.util.Date;
import java.util.List;

import com.jvdm.campaign.dto.EventDto;

public interface EventService {

    String saveEvent(EventDto eventDto);

    EventDto findEventById(String eventId);

    List<EventDto> findAllEvents();

    EventDto updateEvent(EventDto eventDto);

    EventDto deleteEventById(String eventId);

    public boolean existsByTitle(String title);

    boolean isValidDate(Date startDate, Date endDate);
}
