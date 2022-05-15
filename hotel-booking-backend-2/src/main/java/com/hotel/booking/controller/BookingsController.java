package com.hotel.booking.controller;

import com.hotel.booking.model.*;
import com.hotel.booking.repository.BookingRepository;
import com.hotel.booking.repository.CustomerRepository;
import com.hotel.booking.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.commons.lang3.RandomStringUtils;
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
    public ResponseEntity<? extends Object> findBookingById(@PathVariable String id) {
        if(bookingRepository.findById(id).isPresent())
            return new ResponseEntity<Booking>(HttpStatus.OK);
        return new ResponseEntity<>("There is no booking by this ID. Please enter the correct ID.", HttpStatus.OK);
    }

    @PostMapping("/booking")
    public ResponseEntity saveBooking(@RequestBody BookingRequest bookingRequest) {
        long numberOfDays = ChronoUnit.DAYS.between(bookingRequest.checkInDate, bookingRequest.checkOutDate);
        if(numberOfDays > 7)
            return new ResponseEntity<>("The booking cannot exceed 7 days. Please modify your booking.", HttpStatus.OK);
        String bookingNumber = RandomStringUtils.randomAlphanumeric(10);
        saveBooking(bookingNumber, bookingRequest);
        return new ResponseEntity<>("Booking done! Booking ID: " + bookingNumber, HttpStatus.OK);
    }

    @PutMapping("/booking/{id}")
    public ResponseEntity updateBooking(@PathVariable String id, @RequestBody Booking booking) {
        if(!bookingRepository.findById(id).isPresent())
            return new ResponseEntity<>("There is no booking by this ID. Please enter the correct ID.", HttpStatus.OK);
        long numberOfDays = ChronoUnit.DAYS.between(booking.getCheckInDate(), booking.getCheckOutDate());
        if(numberOfDays > 7)
            return new ResponseEntity<>("The booking cannot exceed 7 days. Please modify your booking.", HttpStatus.OK);
        BookingRequest bookingRequest = new BookingRequest(booking.getHotelId(), booking.getCustomerEmail(), booking.getRoomType(),
                booking.getNumberOfRooms(), booking.getCheckInDate(), booking.getCheckOutDate(), booking.getAmenities());
        saveBooking(id, bookingRequest);
        return new ResponseEntity<>("Booking updated!", HttpStatus.OK);
    }

    private void saveBooking(String bookingNumber, BookingRequest bookingRequest){
        Room room = Room.valueOf(bookingRequest.roomType);
        double pricePerNight = room.pricePerNight;
        double priceIncrease = PricingService.getPriceIncrease(bookingRequest.checkInDate, bookingRequest.checkOutDate);
        pricePerNight = priceIncrease * pricePerNight;
        double totalPrice = 0;
        long numberOfDays = ChronoUnit.DAYS.between(bookingRequest.checkInDate, bookingRequest.checkOutDate);
        totalPrice = numberOfDays * pricePerNight * bookingRequest.numberOfRooms;
        // calculate discounted price by redeeming reward points
        //Customer customer = customerRepository.findById(bookingRequest.customerId).get();
        Customer customer = customerRepository.findByEmail(bookingRequest.customerEmail);
        int pointsRemaining = customer.getPointsRemaining();
        double discount = pointsRemaining * RewardPoints.discountPerPoint;
        double discountedPrice = totalPrice - discount;
        // add reward points for stay
        long pointsAwardedForStay = RewardPoints.pointsPerNight * numberOfDays;
        Booking booking = new Booking(bookingNumber, bookingRequest.hotelId, bookingRequest.customerEmail, LocalDate.now(), bookingRequest.roomType,
                bookingRequest.numberOfRooms, bookingRequest.checkInDate, bookingRequest.checkOutDate, totalPrice, discountedPrice, pointsRemaining, bookingRequest.amenities, (int) pointsAwardedForStay);
        bookingRepository.save(booking);
        customer.setPointsRemaining((int) pointsAwardedForStay + customer.getPointsRemaining());
        customerRepository.save(customer);
    }

    @DeleteMapping("/booking/{id}")
    public ResponseEntity cancelBooking(@PathVariable String id) {
        if(!bookingRepository.findById(id).isPresent())
            return new ResponseEntity<>("There is no booking by this ID. Please enter the correct ID.", HttpStatus.OK);
        Booking booking = bookingRepository.findById(id).get();
        //Customer customer = customerRepository.findById(booking.getCustomerEmail()).get();
        Customer customer = customerRepository.findByEmail(booking.getCustomerEmail());
        int pointsRemaining = customer.getPointsRemaining();
        bookingRepository.deleteById(id);
        customer.setPointsRemaining(pointsRemaining - booking.getPointsAwarded());
        customerRepository.save(customer);
        return new ResponseEntity<>("Booking cancelled!", HttpStatus.OK);
    }
}
