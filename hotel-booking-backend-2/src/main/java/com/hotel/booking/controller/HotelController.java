package com.hotel.booking.controller;

import com.hotel.booking.model.Hotel;
import com.hotel.booking.repository.HotelRepository;
import com.hotel.booking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {

    @Autowired
    private HotelService hotelservice;

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/hotel/{city}")
    public List<Hotel> searchByCity(@PathVariable String city) {
        return hotelservice.getHotelsInCity(city);
    }

    @GetMapping("/hotel")
    public List<Hotel> listHotels() {
        return hotelRepository.findAll();
    }

    @PostMapping("/hotel")
    public String saveHotel(@RequestBody Hotel hotel) {
        hotelRepository.save(hotel);
        return "Hotel added!";
    }
}
