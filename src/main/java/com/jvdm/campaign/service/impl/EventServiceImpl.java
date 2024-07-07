package com.jvdm.campaign.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jvdm.campaign.dto.EventDto;
import com.jvdm.campaign.entity.Event;
import com.jvdm.campaign.repository.EventRepository;
import com.jvdm.campaign.service.EventService;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public String saveEvent(EventDto eventDto) {

        Event event = new Event();
        event.setEventId(UUID.randomUUID().toString());
        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        event.setStartDate(eventDto.getStartDate());
        event.setEndDate(eventDto.getEndDate());
        event.setPlace(eventDto.getPlace());
        event.setAdultAmount(eventDto.getAdultAmount());
        event.setChildAmount(eventDto.getChildAmount());
        event.setSeats(eventDto.getSeats());
        event.setSeatsBooked(0);
        event.setStatus(Event.EventStatus.CREATED);

        eventRepository.save(event);
        return event.getEventId();
    }

    @Override
    public EventDto findEventById(String eventId) {
        return eventRepository.findById(eventId).map(this::mapToEventDto).orElse(null);
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map((event) -> mapToEventDto(event))
                .collect(Collectors.toList());

    }

    @Override
    public EventDto updateEvent(EventDto eventDto) {
        try {
            Optional<Event> optionalEvent = eventRepository.findById(eventDto.getId());
            if (optionalEvent.isPresent()) {
                Event event = optionalEvent.get();
                event.setTitle(eventDto.getTitle());
                event.setDescription(eventDto.getDescription());
                event.setPlace(eventDto.getPlace());
                event.setAdultAmount(eventDto.getAdultAmount());
                event.setChildAmount(eventDto.getChildAmount());
                event.setSeats(eventDto.getSeats());
                event.setStatus(eventDto.getStatus());

                eventRepository.save(event);
                return eventDto;
            }
        } catch (Exception e) {
            return null;
        }
        return null;

    }

    @Override
    public EventDto deleteEventById(String eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            eventRepository.delete(event);
            return mapToEventDto(event);
        } else {
            return null;
        }

    }

    private EventDto mapToEventDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setEventId(event.getEventId());
        eventDto.setTitle(event.getTitle());
        eventDto.setDescription(event.getDescription());
        eventDto.setPlace(event.getPlace());
        eventDto.setAdultAmount(event.getAdultAmount());
        eventDto.setStartDate(event.getStartDate());
        eventDto.setEndDate(event.getEndDate());
        eventDto.setSeats(event.getSeats());
        eventDto.setStatus(event.getStatus());

        return eventDto;

    }

    @Override
    public boolean existsByTitle(String title) {
        return eventRepository.existsByTitle(title);
    }

    public boolean isValidDate(Date startDate, Date endDate) {
        return startDate != null && endDate != null && startDate.before(endDate) && startDate.after(new Date());
    }
}