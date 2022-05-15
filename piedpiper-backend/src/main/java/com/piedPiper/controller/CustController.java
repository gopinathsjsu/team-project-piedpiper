package com.piedpiper.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piedpiper.api.BookingResponse;
import com.piedpiper.exception.GenericException;
import com.piedpiper.model.Reservation;
import com.piedpiper.model.Customer;
import com.piedpiper.repository.BookingRepository;
import com.piedpiper.repository.CustRepo;
import com.piedpiper.repository.HotelRepository;
import com.piedpiper.service.CustServiceImpl;
import com.piedpiper.service.HotelServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/hotel-booking")
public class CustController {

	@Autowired
	public HotelRepository hotelrepository;

	@Autowired
	public BookingRepository bookingRepository;

	@Autowired
	public CustRepo custRepo;

	@Autowired
	public HotelServiceImpl hotelservice;

	@Autowired
	private CustServiceImpl customerService;

	@Autowired
	public CustController(HotelServiceImpl hotelservice) {
		this.hotelservice = hotelservice;
	}

	@PostMapping("/registerCustomer")
	public String registerCustomer(@RequestBody Customer customer) {

		return "registered";
	}

	@PostMapping("/createBooking")
	public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody Reservation customer) {

		BookingResponse bookingResponseDetails = hotelservice.createBooking(customer);

		return new ResponseEntity<BookingResponse>(bookingResponseDetails, HttpStatus.CREATED);
	}

	@PutMapping("/updateBooking/{reservationID}")
	public ResponseEntity<Reservation> updateBooking(@Valid @PathVariable("reservationID") String reservationID,
			@Valid @RequestBody Reservation reservation) {

		ResponseEntity<Reservation> updatedDetails = hotelservice.updateBookingDetails(reservationID,
				reservation);

		return new ResponseEntity<Reservation>(updatedDetails.getBody(), HttpStatus.OK);

	}

	@GetMapping("/getBooking/{emailID}")
	public List<Reservation> getBookingOfCustomer(@Valid @PathVariable("emailID") String emailID) {

		List<Reservation> reservation = bookingRepository.findByEmail(emailID);

		return reservation;
	}

	@GetMapping("/getRewardPoints/{emailID}")
	public String getRewardPoints(@Valid @PathVariable("emailID") String emailID) {

		Customer customerexists = customerService.findUserByEmail(emailID);
		double sum = 0;

		if (null != customerexists) {

			List<Reservation> reservation = bookingRepository.findByEmail(emailID);

			if (!reservation.isEmpty() && reservation != null) {

				sum = reservation.get(reservation.size() - 1).getRewardpoints();
				// bookingDetails.stream().mapToDouble(de -> de.getRewardpoints()).sum();

				return " your reward points are " + sum;

			} else {

				throw new GenericException("No customer with " + emailID + " exists");
			}

		} else {

			throw new GenericException("No customer with " + emailID + " exists");
		}

	}

	@DeleteMapping("/cancel/{reservationID}")
	public String cancelBooking(@PathVariable("reservationID") String reservationID) {

		hotelservice.deleteBookingDetails(reservationID);
		return "booking cancelled";
	}

}
