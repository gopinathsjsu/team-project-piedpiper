package com.piedpiper.service;

import java.util.List;

import com.piedpiper.api.BookingResponse;
import com.piedpiper.model.Reservation;
import com.piedpiper.model.Hotel;


//Service Pattern for Reservation
public interface HotelService {
	
	public BookingResponse createBooking(Reservation customer);
	
	public List<Reservation> getAllBookingDetails();

	public Reservation deleteBookingDetails(String id);
	
	public List<Hotel> searchHotelByLocation(String location);


}
