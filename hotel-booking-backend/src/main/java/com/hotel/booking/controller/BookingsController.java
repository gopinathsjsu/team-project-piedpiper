package com.hotel.booking.controller;


import com.hotel.booking.model.*;
import com.hotel.booking.repository.BookingRepository;
import com.hotel.booking.repository.CustomerRepository;
import com.hotel.booking.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
public class BookingsController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/bookings")
    public List<Booking> listBookings() {
        return bookingRepository.findAll();
    }

    @GetMapping("/bookings/{id}")
    public List<Booking> findBookingById(@PathVariable String id) {
        return bookingRepository.findByBookingNumber(id);
    }

    @PostMapping("/booking")
    public void saveBooking(@RequestBody BookingRequest bookingRequest) {
        Room room = Room.valueOf(bookingRequest.roomType);
        double pricePerNight = room.pricePerNight;
        double priceIncrease = PricingService.getPriceIncrease(bookingRequest.checkInDate, bookingRequest.checkOutDate);
        pricePerNight = priceIncrease * pricePerNight;
        long numberOfDays = ChronoUnit.DAYS.between(bookingRequest.checkInDate, bookingRequest.checkOutDate);
        double totalPrice = 0;
        totalPrice = numberOfDays * pricePerNight * bookingRequest.numberOfRooms;
        // calculate discounted price by redeeming reward points
        Customer customer = customerRepository.findById(bookingRequest.customerId).get();
        int pointsRemaining = customer.getPointsRemaining();
        double discount = pointsRemaining * RewardPoints.discountPerPoint;
        double discountedPrice = totalPrice - discount;
        Booking booking = new Booking(bookingRequest.bookingNumber, bookingRequest.hotelId, bookingRequest.customerId, LocalDate.now(), bookingRequest.roomType,
                bookingRequest.numberOfRooms, bookingRequest.checkInDate, bookingRequest.checkOutDate, totalPrice, discountedPrice, pointsRemaining, bookingRequest.amenities);
        bookingRepository.save(booking);
        // add reward points for stay
        long pointsAwardedForStay = RewardPoints.pointsPerNight * numberOfDays;
        customer.setPointsRemaining((int) pointsAwardedForStay);
        customerRepository.save(customer);
    }

    @PutMapping("/bookings/{id}")
    public void updateBooking(@RequestBody Booking booking) {
        // TODO
    }

    @DeleteMapping("/bookings/{id}")
    public void cancelBooking(@RequestBody Booking booking) {
        // TODO
    }
}
