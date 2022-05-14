package com.piedpiper.response;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.piedpiper.api.Amenities;
import com.piedpiper.api.BookingResponse;
import com.piedpiper.model.Reservation;
import com.piedpiper.pricing.ChristmasPricing;
import com.piedpiper.pricing.PublicHolidayPricing;
import com.piedpiper.pricing.SummerPricing;
import com.piedpiper.pricing.WeekDayPricing;
import com.piedpiper.pricing.WeekendPricing;
import com.piedpiper.repository.BookingRepository;

@Component
public class BookingResponseDetailsBuilder {

	public ChristmasPricing christmasPricing;
	public PublicHolidayPricing publicHolidayPricing;
	public WeekendPricing weekendPricing;
	public WeekDayPricing weekDayPricing;
	public SummerPricing summerPricing;

	@Autowired
	public BookingRepository bookingRepository;

	@Autowired
	public BookingResponseDetailsBuilder(ChristmasPricing christmasPricing, PublicHolidayPricing publicHolidayPricing,
			WeekendPricing weekendPricing, WeekDayPricing weekDayPricing,SummerPricing summerPricing) {
		super();
		this.christmasPricing = christmasPricing;
		this.publicHolidayPricing = publicHolidayPricing;
		this.weekendPricing = weekendPricing;
		this.weekDayPricing = weekDayPricing;
		this.summerPricing=summerPricing;
	}

	/* saving all the public holidays */
	// Mon, Jan 17, 2022
	private LocalDate Martin_Luther_King_day = LocalDate.of(2022, 1, 17);
	// Mon, May 30, 2022
	private LocalDate Memorial_day = LocalDate.of(2022, 5, 30);
	// Mon, Jul 4, 2022
	private LocalDate Independence_day = LocalDate.of(2022, 7, 04);
	// Mon, Sep 5, 2022
	private LocalDate Labor_day = LocalDate.of(2022, 9, 05);
	// Fri, Nov 11, 2022
	private LocalDate Veterans_day = LocalDate.of(2022, 11, 11);
	// Thu, Nov 24, 2022
	private LocalDate ThanksGiving_day = LocalDate.of(2022, 11, 24);
	// Mon, Dec 26, 2022
	private LocalDate Christmas_day = LocalDate.of(2022, 12, 26);

	/**
	 * method to build the booking details response
	 * 
	 * @param reservation
	 */
	public BookingResponse buildResponse(Reservation reservation) {

		BookingResponse bookingResponse = new BookingResponse();

		/* saving on which day the start date and end date is falling in a week */
		DayOfWeek startDate = DayOfWeek.of(reservation.getFromDate().get(ChronoField.DAY_OF_WEEK));
		DayOfWeek endDate = DayOfWeek.of(reservation.getToDate().get(ChronoField.DAY_OF_WEEK));

		List<LocalDate> dates = new ArrayList<LocalDate>();

		dates.add(Independence_day);
		dates.add(Martin_Luther_King_day);
		dates.add(Memorial_day);
		dates.add(Veterans_day);
		dates.add(Christmas_day);
		dates.add(Labor_day);
		dates.add(ThanksGiving_day);

		// System.out.println("public holidays" + dates.toString());

		LocalDate ChristmasStartDate = LocalDate.of(2022, 12, 24);
		LocalDate ChristmasEndDate = LocalDate.of(2023, 1, 02);

		LocalDate SummerStartDate = LocalDate.of(2022, 06, 01);
		LocalDate SummerEndDate = LocalDate.of(2022, 07, 30);

		bookingResponse.setCustomerName(reservation.getCustomerName());
		bookingResponse.setEmailID(reservation.getEmailID());
		bookingResponse.setFromDate(reservation.getFromDate());
		bookingResponse.setToDate(reservation.getToDate());
		bookingResponse.setNumber_of_adults(reservation.getNumber_of_adults());
		bookingResponse.setNumber_of_children(reservation.getNumber_of_children());
		// bookingResponse.setPhoneNumber(bookingDetails.getPhoneNumber());
		bookingResponse.setNumberOfRooms(reservation.getNumberOfRooms());
		bookingResponse.setRewardPoints(reservation.getRewardpoints());

		if (dates.contains(reservation.getFromDate()) || dates.contains(reservation.getToDate())) {

			bookingResponse.setPrice(reservation.getNumberOfRooms()
					* (publicHolidayPricing.getPricing(reservation.getRoomType())));
			System.out.println("print public holiday");
			System.out.println(bookingResponse.getPrice());
		} else if ((reservation.getFromDate().isAfter(ChristmasStartDate))
				&& (reservation.getToDate().isBefore(ChristmasEndDate))) {

			System.out.println("christmas");
			bookingResponse.setPrice(
					reservation.getNumberOfRooms() * (christmasPricing.getPricing(reservation.getRoomType())));
		} else if (startDate == DayOfWeek.SATURDAY || startDate == DayOfWeek.SUNDAY || endDate == DayOfWeek.SATURDAY
				|| endDate == DayOfWeek.SUNDAY) {
			 System.out.println("weekend");
			bookingResponse.setPrice(
					reservation.getNumberOfRooms() * (weekendPricing.getPricing(reservation.getRoomType())));

		} else if ((reservation.getFromDate().isAfter(SummerStartDate))
				&& (reservation.getToDate().isBefore(SummerEndDate))) {

			System.out.println("summer");
			bookingResponse.setPrice(
					reservation.getNumberOfRooms() * (summerPricing.getPricing(reservation.getRoomType())));
		} else {
			 System.out.println("weekday");
			bookingResponse.setPrice(
					reservation.getNumberOfRooms() * (weekDayPricing.getPricing(reservation.getRoomType())));
		}

		/*
		 * if reward points are less than 300 then customer gets 5% discount, if reward
		 * points are less than 700 n more than 300 then customer gets 10% discount, if
		 * reward points are more than 700 then customer gets 15% discount, if per one
		 * booking
		 */
		if (reservation.getRewardpoints() <= 300 && reservation.getRewardpoints() > 0) {

			int discountPrice = bookingResponse.getPrice() * 5 / 100;
			System.out.println(discountPrice + "less than 300");
			bookingResponse.setPrice((bookingResponse.getPrice()) - discountPrice);

		} else if (reservation.getRewardpoints() <= 700 && reservation.getRewardpoints() >= 300) {
			int discountPrice = bookingResponse.getPrice() * 10 / 100;
			System.out.println(discountPrice+ "\"bw 300 and 700\"");
			bookingResponse.setPrice((bookingResponse.getPrice()) - discountPrice);
		} else if (reservation.getRewardpoints() >= 700 ) {
			int discountPrice = bookingResponse.getPrice() * 15 / 100;
			System.out.println(discountPrice+ "bookingDetails.getRewardpoints() > 700 ");
			bookingResponse.setPrice(( bookingResponse.getPrice()) - discountPrice);
		}
		bookingResponse.setRoomType(reservation.getRoomType());
		

		Amenities amenities = new Amenities();

		if (null != reservation.getAmenities()) {
			amenities.setAccess_to_fitness_room(reservation.getAmenities().isAccess_to_fitness_room());
			amenities.setAccess_to_swimming_Pool_Jacuzzi(reservation.getAmenities().isAccess_to_fitness_room());
			amenities.setAll_meals_included(reservation.getAmenities().isAll_meals_included());
			amenities.setDaily_continental_breakfast(reservation.getAmenities().isDaily_continental_breakfast());
			amenities.setDaily_parking(reservation.getAmenities().isDaily_parking());

		}
		bookingResponse.setAmenities(amenities);
		
		reservation.setPrice(bookingResponse.getPrice());
		bookingRepository.save(reservation);
		bookingResponse.setReservationID(reservation.getReservationID());
		
		return bookingResponse;

	}

}
