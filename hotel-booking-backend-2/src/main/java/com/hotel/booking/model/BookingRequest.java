package com.hotel.booking.model;

import java.time.LocalDate;

public class BookingRequest {
    public int hotelId;
    public String customerEmail;
    public String roomType;
    public int numberOfRooms;
    public LocalDate checkInDate;
    public LocalDate checkOutDate;
    public Amenities amenities;

    public BookingRequest(){

    }

    public BookingRequest(int hotelId, String customerEmail, String roomType, int numberOfRooms, LocalDate checkInDate, LocalDate checkOutDate, Amenities amenities) {
        this.hotelId = hotelId;
        this.customerEmail = customerEmail;
        this.roomType = roomType;
        this.numberOfRooms = numberOfRooms;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.amenities = amenities;
    }
}
