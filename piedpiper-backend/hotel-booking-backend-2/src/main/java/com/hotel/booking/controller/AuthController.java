package com.hotel.booking.controller;

import com.hotel.booking.model.Customer;
import com.hotel.booking.model.CustomerAuthRequest;
import com.hotel.booking.model.CustomerSignupRequest;
import com.hotel.booking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/customer/login")
    public void customerLogin(@RequestBody CustomerAuthRequest request) {
    }

    @PostMapping("/customer/signup")
    public ResponseEntity customerSignup(@RequestBody CustomerSignupRequest customerRequest) {
        Customer oldCustomer = customerRepository.findByEmail(customerRequest.email);
        if (oldCustomer != null) {
            return new ResponseEntity<>("Signup failed: customer with this email ID already exists.", HttpStatus.OK);
        }
        String encodedPassword = bCryptPasswordEncoder.encode(customerRequest.password);
        Customer customer = new Customer(customerRequest.firstName, customerRequest.lastName, encodedPassword,
                customerRequest.phoneNumber, customerRequest.email, 0);
        customerRepository.save(customer);
        return new ResponseEntity<>("Customer created!", HttpStatus.OK);
    }
}
