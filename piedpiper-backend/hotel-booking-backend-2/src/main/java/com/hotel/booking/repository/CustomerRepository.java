package com.hotel.booking.repository;

import com.hotel.booking.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Integer> {

    Customer findByEmail(String email);
}
