package com.jvdm.campaign.service.impl;

import com.jvdm.campaign.dto.BookingDto;
import com.jvdm.campaign.dto.DependentDTO;
import com.jvdm.campaign.entity.Booking;
import com.jvdm.campaign.entity.Event;
import com.jvdm.campaign.entity.Dependent;
import com.jvdm.campaign.repository.BookingRepository;
import com.jvdm.campaign.repository.EventRepository;
import com.jvdm.campaign.repository.DependentRepository;
import com.jvdm.campaign.repository.UserRepository;
import com.jvdm.campaign.service.BookingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DependentRepository dependentRepository;

    private Optional<Event> findEventById(String eventId) {
        return eventRepository.findById(eventId);
    }

    @Override
    public List<String> validate(BookingDto bookingDto) {
        List<String> errorMessages = new ArrayList<>();
        Optional<Event> event = findEventById(bookingDto.getEventId());
        if (event.isEmpty()) {
            errorMessages.add("Invalid Event Id");
        }
        if (!validEvent(event)) {
            errorMessages.add("you cannot book this event as event status is not created");
        }
        // if (bookingDto.getTotalAmount() == null || bookingDto.getSeatCount() <= 0
        // || bookingDto.getSeatCount() > event.get().getSeats()) {
        // errorMessages.add("Invalid total amount or number of seats booked.");
        // }
        int availbale_seats = event.get().getSeats() - event.get().getSeatsBooked();
        if (availbale_seats < event.get().getSeats()) {
            String message = "Total seats exceed the available seats. Available seats : " + availbale_seats;
            errorMessages.add(message);
        }
        // if (!validTotalAmount(event.get(), bookingDto)) {
        // errorMessages.add("total amount does not match the expected total amount");
        // }
        return errorMessages;
    }

    @Override
    @Transactional
    public BookingDto registerEvent(BookingDto bookingDto, String username) {
        Optional<Event> event = eventRepository.findById(bookingDto.getEventId());
        Booking booking = addBooking(bookingDto, username);
        List<Dependent> dependents = addDependents(bookingDto, booking);
        bookingRepository.save(booking);
        dependentRepository.saveAll(dependents);
        return bookingDto;
    }

    private List<Dependent> addDependents(BookingDto bookingDto, Booking booking) {
        List<Dependent> dependents = null;
        if (bookingDto.getDependents() != null) {
            dependents = new ArrayList<>();
            for (DependentDTO dependentDTO : bookingDto.getDependents()) {
                Dependent dependent = new Dependent();
                dependent.setDependentId(UUID.randomUUID().toString());
                dependent.setBooking(booking);
                dependent.setName(dependentDTO.getDependentName());
                dependent.setAge(dependentDTO.getAge());
                dependent.setPlace(dependentDTO.getPlace());
                dependent.setGender(dependentDTO.getGender());
                dependents.add(dependent);
            }
        }
        return dependents;
    }

    private Booking addBooking(BookingDto bookingDto, String username) {
        Booking booking = new Booking();
        // Assuming you generate ID for each booking
        booking.setBookingId(UUID.randomUUID().toString());
        // booking.setEvent(findEventById(bookingDto.getEventId()).get());
        booking.setPaid(Boolean.FALSE);
        booking.setTotalAmount(bookingDto.getTotalAmount());
        booking.setBookingDate(bookingDto.getBookingDate());
        // booking.setSeatCount(1 + bookingDto.getDependents().size());
        // booking.setPaidVia(bookingDto.getPaidVia());
        booking.setTotalAmount(bookingDto.getTotalAmount());
        booking.setAmountPaid(BigDecimal.ZERO);

        // booking.setLoggedInPrincipal(userRepository.findByUsername(username).get());

        booking.setBookingStatus(Booking.BookingStatus.PENDING);

        if (bookingDto.getDependents() != null) {
            for (DependentDTO dependentDTO : bookingDto.getDependents()) {
                Dependent dependent = new Dependent();
                dependent.setDependentId(UUID.randomUUID().toString());
                dependent.setBooking(booking);
                dependent.setName(dependentDTO.getDependentName());
                dependent.setAge(dependentDTO.getAge());
                dependent.setPlace(dependentDTO.getPlace());
                dependent.setGender(dependentDTO.getGender());
            }
        }
        return booking;
    }

    private boolean validEvent(Optional<Event> event) {
        return event
                .map(Event::getStatus)
                .map(status -> status == Event.EventStatus.CREATED)
                .orElse(false);
    }

    /*
     * private boolean validTotalAmount(Event event, BookingDto bookingDto) {
     * BigDecimal seatPrice = event.getAdultAmount();
     * BigDecimal childSeatPrice = event.getChildAmount() != null ?
     * event.getChildAmount() : seatPrice;
     * 
     * int adults = bookingDto.getAge() > 10 ? 1 : 0;
     * int children = 0;
     * if (bookingDto.getDependents() != null) {
     * for (DependentDTO dependent : bookingDto.getDependents()) {
     * if (dependent.getAge() > 10) {
     * adults++;
     * } else {
     * children++;
     * }
     * }
     * }
     * BigDecimal expectedTotalAmount =
     * seatPrice.multiply(BigDecimal.valueOf(adults))
     * .add(childSeatPrice.multiply(BigDecimal.valueOf(children)));
     * 
     * return expectedTotalAmount.equals(bookingDto.getTotalAmount());
     * 
     * }
     * 
     * }
     */
}
