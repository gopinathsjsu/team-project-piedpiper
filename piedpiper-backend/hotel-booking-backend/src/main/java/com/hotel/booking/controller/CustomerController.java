package com.hotel.booking.controller;

import com.hotel.booking.model.Customer;
import com.hotel.booking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer/{id}/points")
    public int getCustomerPoints(@PathVariable int id) {
        Customer customer = customerRepository.findById(id).get();
        return customer.getPointsRemaining();
    }

    @PostMapping("/customer")
    public void addCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
