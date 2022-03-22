package com.piedpiper.validation;

import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import com.piedpiper.exception.GenericException;
import com.piedpiper.model.Reservation;

@Component
public class DateRangeValidator {

	public void validateDateRange(Reservation reservation) {
		
		long noOfDaysDifference = ChronoUnit.DAYS.between(reservation.getFromDate(), reservation.getToDate());

		if (noOfDaysDifference < 0) {
			throw new GenericException("Start Date should not be greater than End Date");

		}
		if (noOfDaysDifference > 7) {
			throw new GenericException("Cannot book longer than 7 days");
		}

	}

}
