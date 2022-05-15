package com.hotel.booking.service;

import java.util.*;

import com.hotel.booking.model.Customer;
import com.hotel.booking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email);
        if(customer != null) {
            return new User(customer.getEmail(), customer.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("There is no customer by this email.");
        }
    }
}
