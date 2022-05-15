package com.piedpiper.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piedpiper.api.EmployeeAuthenticationRequest;
import com.piedpiper.exception.GenericException;
import com.piedpiper.model.Reservation;
import com.piedpiper.model.Employee;
import com.piedpiper.model.Hotel;
import com.piedpiper.repository.EmployeeRepository;
import com.piedpiper.repository.HotelRepository;
import com.piedpiper.service.HotelServiceImpl;

@RestController
@CrossOrigin(origins = "*", maxAge = 40000)
@RequestMapping("/api")
public class HotelController {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	public HotelRepository hotelrepository;

	@Autowired
	public EmployeeRepository employeeRepository;

	public HotelServiceImpl hotelservice;

	@Autowired
	public HotelController(HotelServiceImpl hotelservice) {
		this.hotelservice = hotelservice;
	}

	@GetMapping("/searchHotel/{location}")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Hotel> searchHotel(@PathVariable String location) {

		return hotelservice.searchHotelByLocation(location);

	}

	@GetMapping("/getBooking")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Reservation> getBooking() {

		return hotelservice.getAllBookingDetails();
	}

	@PostMapping("/savehotel")
	@CrossOrigin(origins = "http://localhost:3000")
	public String saveHotel(@Valid @RequestBody Hotel hotel) {
		hotelrepository.save(hotel);

		return "added Hotel";

	}

	@GetMapping("/hotel/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public Optional<Hotel> getHotel(@PathVariable int id) {
		return hotelrepository.findById(id);

	}

	@PostMapping("/employee/register")
	public String registerEmployee(@Valid @RequestBody Employee employee) {

		Employee employeeExists = employeeRepository.findUserByEmail(employee.getEmail());
		if (employeeExists != null) {
			throw new GenericException("Employee with email: " + employee.getEmail() + " already exists. Please Login");
		} else {
			employeeRepository.save(employee);

			return "employee registered successfully";
		}
	}

	@PostMapping("/employee/login")
	public String employeeLogin(@Valid @RequestBody EmployeeAuthenticationRequest employee) {

		Employee employeeExists = employeeRepository.findUserByEmail(employee.getEmail());
		if (employeeExists != null) {
			return "signin succesfull";
		} else {
			throw new GenericException(
					"Employee with email: " + employee.getEmail() + " doesn't exists. Please SignUp");
		}

	}

}
