package com.piedpiper.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.piedpiper.model.Reservation;

@Repository
public interface BookingRepository extends MongoRepository<Reservation, String> {

	@Query(value = "{reservationID: ?0}", delete = true)
	Reservation deleteByReservationID(String reservationID);

	@Query("{reservationID :?0}") // SQL Equivalent : SELECT * FROM Hotel WHERE reservationID=?
	Reservation getDetailsByReservationID(String reservationID);

	Reservation save(Optional<Reservation> details);

	@Query("{emailID :?0}")
	List<Reservation> findByEmail(String emailID);


}
