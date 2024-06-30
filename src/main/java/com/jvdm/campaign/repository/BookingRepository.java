package com.jvdm.campaign.repository;

import com.jvdm.campaign.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,String> {
}
