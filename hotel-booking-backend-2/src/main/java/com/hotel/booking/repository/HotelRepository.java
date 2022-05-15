package com.hotel.booking.repository;

import com.hotel.booking.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, Integer> {

    List<Hotel> findByCity(String city);
}
