package com.hotel.booking.service;

import com.hotel.booking.model.Hotel;
import com.hotel.booking.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getHotelsInCity(String city){
        return hotelRepository.findByCity(city);
    }

}
