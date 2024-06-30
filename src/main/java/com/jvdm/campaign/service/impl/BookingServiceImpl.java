package com.jvdm.campaign.service.impl;

import com.jvdm.campaign.dto.BookingDto;
import com.jvdm.campaign.dto.DependentDTO;
import com.jvdm.campaign.entity.Booking;
import com.jvdm.campaign.entity.Camp;
import com.jvdm.campaign.entity.Dependent;
import com.jvdm.campaign.repository.BookingRepository;
import com.jvdm.campaign.repository.CampRepository;
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
    CampRepository campRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DependentRepository dependentRepository;

    private Optional<Camp> findCampById(String CampId) {
        return campRepository.findById(CampId);
    }
    @Override
    public List<String> validate(BookingDto bookingDto) {
        List<String> errorMessages = new ArrayList<>();
        Optional<Camp> camp = findCampById(bookingDto.getCampId());
        if (camp.isEmpty()) {
          errorMessages.add("Invalid Camp Id");
        }
        if (!validCamp(camp)) {
            errorMessages.add("you cannot book this camp as camp status is not created");
        }
        if (bookingDto.getTotalAmount() == null || bookingDto.getSeatCount() <= 0
                || bookingDto.getSeatCount() > camp.get().getSeats()) {
            errorMessages.add("Invalid total amount or number of seats booked.");
        }
        int availbale_seats = camp.get().getSeats() - camp.get().getSeatsBooked();
        if (availbale_seats < camp.get().getSeats()){
            String message = "Total seats exceed the available seats. Available seats : " + availbale_seats;
            errorMessages.add(message);
        }
        if (!validTotalAmount(camp.get(), bookingDto)) {
            errorMessages.add("total amount does not match the expected total amount");
        }
        return errorMessages;
    }

    @Override
    @Transactional
    public BookingDto registerCamp(BookingDto bookingDto, String username) {
        Optional<Camp> camp = campRepository.findById(bookingDto.getCampId());
        Booking booking = addBooking(bookingDto, username);
        List<Dependent> dependents = addDependents(bookingDto,booking);
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
                dependent.setPhoneNo(dependentDTO.getPhoneNo());
                dependents.add(dependent);
            }
        }
        return dependents;
    }

    private Booking addBooking(BookingDto bookingDto, String username) {
        Booking booking = new Booking();
        // Assuming you generate ID for each booking
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setCamp(findCampById(bookingDto.getCampId()).get());
        booking.setPaid(Boolean.FALSE);
        booking.setTotalAmount(bookingDto.getTotalAmount());
        booking.setBookingDate(bookingDto.getBookingDate());
        booking.setSeatCount(1 + bookingDto.getDependents().size());
        //booking.setPaidVia(bookingDto.getPaidVia());
        booking.setTotalAmount(bookingDto.getTotalAmount());
        booking.setAmountPaid(BigDecimal.ZERO);
        booking.setBookerName(bookingDto.getBookerName());
        booking.setBookerPlace(bookingDto.getBookerPlace());
        booking.setBookerAge(bookingDto.getBookerAge());
        booking.setLoggedInPrincipal(userRepository.findByUsername(username).get());
        booking.setBookerPhone(bookingDto.getBookerPhone());
        booking.setBookingStatus(Booking.BookingStatus.PENDING);

        if (bookingDto.getDependents() != null) {
            for (DependentDTO dependentDTO : bookingDto.getDependents()) {
                Dependent dependent = new Dependent();
                dependent.setDependentId(UUID.randomUUID().toString());
                dependent.setBooking(booking);
                dependent.setName(dependentDTO.getDependentName());
                dependent.setAge(dependentDTO.getAge());
                dependent.setPhoneNo(dependentDTO.getPhoneNo());
            }
        }
        return booking;
    }

    private boolean validCamp(Optional<Camp> camp) {
        return camp
                .map(Camp::getStatus)
                .map(status -> status == Camp.CampStatus.CREATED)
                .orElse(false);
    }

    private boolean validTotalAmount(Camp camp, BookingDto bookingDto) {
        BigDecimal seatPrice = camp.getAmount();
        BigDecimal childSeatPrice = camp.getChildSeatAmount() != null ? camp.getChildSeatAmount() : seatPrice;

        int adults = bookingDto.getBookerAge() > 10 ? 1 : 0;
        int children = 0;
        if (bookingDto.getDependents() != null) {
            for (DependentDTO dependent : bookingDto.getDependents()) {
                if (dependent.getAge() > 10) {
                    adults++;
                } else {
                    children++;
                }
            }
        }
        BigDecimal expectedTotalAmount = seatPrice.multiply(BigDecimal.valueOf(adults))
                .add(childSeatPrice.multiply(BigDecimal.valueOf(children)));

        return expectedTotalAmount.equals(bookingDto.getTotalAmount());

    }

}
