package com.hotel.booking.model;

import java.time.LocalDate;

public class BookingRequest {
    public String bookingNumber;
    public int hotelId;
    public int customerId;
    public String roomType;
    public int numberOfRooms;
    public LocalDate checkInDate;
    public LocalDate checkOutDate;
    public Amenities amenities;
}
