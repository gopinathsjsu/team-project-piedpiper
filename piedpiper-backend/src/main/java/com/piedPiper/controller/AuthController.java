package com.piedpiper.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piedpiper.api.CustomerAuthenticationRequest;
import com.piedpiper.exception.GenericException;
import com.piedpiper.model.Customer;
import com.piedpiper.repository.CustRepo;
import com.piedpiper.security.JwtTokenGenerator;
import com.piedpiper.service.CustServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 40000)
@RequestMapping("/api/auth")
@SuppressWarnings("rawtypes")
public class AuthController {

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JwtTokenGenerator tokenGenerator;

	@Autowired
	CustRepo custRepo;

	@Autowired
	private CustServiceImpl custService;

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/signin")
	public ResponseEntity signin(@Valid @RequestBody CustomerAuthenticationRequest data) {
		try {
			String username = data.getEmail();
			authManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			String token = tokenGenerator.createToken(username,
					this.custRepo.findByEmail(username).getRoles());
			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid email/password ");
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/signup")
	public ResponseEntity signup(@Valid @RequestBody Customer customer) {
		Customer customerexists = custService.findUserByEmail(customer.getEmail());
		if (customerexists != null) {
			throw new GenericException("Customer with email: " + customer.getEmail() + " already exists. Please Login");
		}
		custService.saveCustomer(customer);
		Map<Object, Object> model = new HashMap<>();
		model.put("message", "User signup successfull");
		return ok(model);
	}
	
}
