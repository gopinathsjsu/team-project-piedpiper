package com.piedpiper.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.piedpiper.model.Customer;


@Repository
public interface CustRepo extends MongoRepository<Customer, Integer> {

	Customer findByEmail(String email);
	
	Customer findByUsername(String username);
}

