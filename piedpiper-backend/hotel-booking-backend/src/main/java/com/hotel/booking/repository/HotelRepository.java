package com.hotel.booking.repository;

import com.hotel.booking.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HotelRepository extends MongoRepository<Hotel, Integer> {

    List<Hotel> findByCity(String city);
}
