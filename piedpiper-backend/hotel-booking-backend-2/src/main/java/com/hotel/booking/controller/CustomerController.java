package com.hotel.booking.controller;

import com.hotel.booking.model.Customer;
import com.hotel.booking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer/{email}/points")
    public ResponseEntity getCustomerPoints(@PathVariable String email) {
        Customer customer = customerRepository.findByEmail(email);
        if(customer == null)
            return new ResponseEntity<>("There is no customer by this email.", HttpStatus.OK);
        return new ResponseEntity<>("Points remaining: " + customer.getPointsRemaining(), HttpStatus.OK);
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
