package com.hotel.booking.repository;

import com.hotel.booking.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, String> {

    Booking findByBookingNumber(String bookingNumber);
}
