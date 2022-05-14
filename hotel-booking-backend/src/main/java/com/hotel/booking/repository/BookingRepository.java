package com.hotel.booking.repository;

import com.hotel.booking.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {

    List<Booking> findByBookingNumber(String bookingNumber);
}
