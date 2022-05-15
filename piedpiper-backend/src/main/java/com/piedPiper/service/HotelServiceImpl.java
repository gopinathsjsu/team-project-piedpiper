package com.piedpiper.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.piedpiper.api.Amenities;
import com.piedpiper.api.BookingResponse;
import com.piedpiper.exception.GenericException;
import com.piedpiper.model.Reservation;
import com.piedpiper.model.Hotel;
import com.piedpiper.pricing.ChristmasPricing;
import com.piedpiper.pricing.PublicHolidayPricing;
import com.piedpiper.pricing.SummerPricing;
import com.piedpiper.pricing.WeekDayPricing;
import com.piedpiper.pricing.WeekendPricing;
import com.piedpiper.repository.BookingRepository;
import com.piedpiper.repository.HotelRepository;
import com.piedpiper.response.BookingResponseDetailsBuilder;
import com.piedpiper.validation.DateRangeValidator;

@Service
public class HotelServiceImpl implements HotelService {

	public ChristmasPricing christmasPricing;
	public PublicHolidayPricing publicHolidayPricing;
	public WeekendPricing weekendPricing;
	public WeekDayPricing weekDayPricing;
	public SummerPricing summerPricing;

	@Autowired
	public BookingRepository bookingRepository;

	private LocalDate Martin_Luther_King_day = LocalDate.of(2022, 1, 17);
	private LocalDate Memorial_day = LocalDate.of(2022, 5, 30);
	private LocalDate Independence_day = LocalDate.of(2022, 7, 04);
	private LocalDate Labor_day = LocalDate.of(2022, 9, 05);
	private LocalDate Veterans_day = LocalDate.of(2022, 11, 11);
	private LocalDate ThanksGiving_day = LocalDate.of(2022, 11, 24);
	private LocalDate Christmas_day = LocalDate.of(2022, 12, 26);

	@Autowired
	public HotelRepository hotelrepository;

	public DateRangeValidator dateRangeValidator;

	public BookingResponseDetailsBuilder bookingResponseDetailsbuilder;

	public HotelServiceImpl(HotelRepository hotelrepository, DateRangeValidator dateRangeValidator,
			BookingResponseDetailsBuilder bookingResponseDetailsbuilder, ChristmasPricing christmasPricing,
			PublicHolidayPricing publicHolidayPricing, WeekendPricing weekendPricing, WeekDayPricing weekDayPricing,
			SummerPricing summerPricing) {
		this.hotelrepository = hotelrepository;
		this.dateRangeValidator = dateRangeValidator;
		this.bookingResponseDetailsbuilder = bookingResponseDetailsbuilder;
		this.christmasPricing = christmasPricing;
		this.publicHolidayPricing = publicHolidayPricing;
		this.weekDayPricing = weekDayPricing;
		this.weekendPricing = weekendPricing;
		this.summerPricing = summerPricing;
	}

	public List<Hotel> searchHotelByLocation(String location) {

		List<Hotel> hotelList = hotelrepository.getHotelByLocation(location);

		if (hotelList.isEmpty() || hotelList == null) {
			throw new GenericException("No hotels in " + location + "." + "Please try another location nearby");
		}
		return hotelList;
	}

	public BookingResponse createBooking(Reservation reservation) {

		dateRangeValidator.validateDateRange(reservation);

		List<Reservation> bookingDetailList = new ArrayList<Reservation>();

		bookingDetailList = bookingRepository.findAll();

		if (!bookingDetailList.isEmpty() && null != bookingDetailList) {
			for (Reservation bookingDetails2 : bookingDetailList) {
				if (reservation.getEmailID().equalsIgnoreCase(bookingDetails2.getEmailID())) {
					reservation.setRewardpoints(bookingDetails2.getRewardpoints() + 100);
				}
			}
		}

		return bookingResponseDetailsbuilder.buildResponse(reservation);

	}

	public List<Reservation> getAllBookingDetails() {
		return (List<Reservation>) bookingRepository.findAll();
	}

	public Reservation deleteBookingDetails(String reservationID) {
		return bookingRepository.deleteByReservationID(reservationID);
	}

	public ResponseEntity<Reservation> updateBookingDetails(String reservationID, Reservation reservation) {

		dateRangeValidator.validateDateRange(reservation);

		Reservation updatedetails = bookingRepository.getDetailsByReservationID(reservationID);

		if (updatedetails != null) {

			updateRequestBuilder(updatedetails, reservation);

			return new ResponseEntity<Reservation>(bookingRepository.save(updatedetails), HttpStatus.OK);

		} else {
			return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * method to build update the booking details
	 * 
	 * @param updateddetails
	 * @param reservation
	 */
	private void updateRequestBuilder(Reservation updateddetails, Reservation reservation) {

		/* saving on which day the start date and end date is falling in a week */
		DayOfWeek startDate = DayOfWeek.of(reservation.getFromDate().get(ChronoField.DAY_OF_WEEK));
		DayOfWeek endDate = DayOfWeek.of(reservation.getToDate().get(ChronoField.DAY_OF_WEEK));

		int oldPrice = updateddetails.getPrice();

		System.out.println(oldPrice + " OLDPRICE");
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
		LocalDate SummerEndDate = LocalDate.of(2023, 07, 30);

		if (dates.contains(reservation.getFromDate()) || dates.contains(reservation.getToDate())) {

			updateddetails.setPrice(reservation.getNumberOfRooms()
					* (publicHolidayPricing.getPricing(reservation.getRoomType())));
			System.out.println("print public holiday");
			// System.out.println(bookingResponse.getPrice());
		} else if ((reservation.getFromDate().isAfter(ChristmasStartDate))
				&& (reservation.getToDate().isBefore(ChristmasEndDate))) {

			System.out.println("christmas");
			updateddetails.setPrice(
					reservation.getNumberOfRooms() * (christmasPricing.getPricing(reservation.getRoomType())));
			System.out.println(updateddetails.getPrice() + "printing price afterchekcing again ");

		} else if (startDate == DayOfWeek.SATURDAY || startDate == DayOfWeek.SUNDAY || endDate == DayOfWeek.SATURDAY
				|| endDate == DayOfWeek.SUNDAY) {
			System.out.println("weekend");
			updateddetails.setPrice(
					reservation.getNumberOfRooms() * (weekendPricing.getPricing(reservation.getRoomType())));

		} else if ((reservation.getFromDate().isAfter(SummerStartDate))
				&& (reservation.getToDate().isBefore(SummerEndDate))) {

			System.out.println("summer");
			updateddetails.setPrice(
					reservation.getNumberOfRooms() * (summerPricing.getPricing(reservation.getRoomType())));
		} else {
			System.out.println("weekday");
			updateddetails.setPrice(
					reservation.getNumberOfRooms() * (weekDayPricing.getPricing(reservation.getRoomType())));
		}

		/*
		 * if reward points are less than 300 then customer gets 5% discount, if reward
		 * points are less than 700 n more than 300 then customer gets 10% discount, if
		 * reward points are more than 700 then customer gets 15% discount, if per one
		 * booking
		 */
		// int finalprice=0;
		if (updateddetails.getRewardpoints() <= 300 && updateddetails.getRewardpoints() >= 0) {

			int discountPrice = updateddetails.getPrice() * 5 / 100;
			System.out.println(discountPrice + "less than 300");
			int finalprice = updateddetails.getPrice() - discountPrice;
			updateddetails.setPrice(finalprice - oldPrice);

		} else if (updateddetails.getRewardpoints() <= 700 && updateddetails.getRewardpoints() >= 300) {
			int discountPrice = updateddetails.getPrice() * 10 / 100;
			System.out.println(discountPrice + "\"bw 300 and 700\"");
			int finalprice = updateddetails.getPrice() - discountPrice;

			updateddetails.setPrice(finalprice - oldPrice);

		} else if (updateddetails.getRewardpoints() >= 700) {
			int discountPrice = updateddetails.getPrice() * 15 / 100;
			System.out.println(discountPrice + " discount bookingDetails.getRewardpoints() > 700 ");
			int finalprice = updateddetails.getPrice() - discountPrice;
			System.out.println(finalprice + "finalprice");

			updateddetails.setPrice(finalprice - oldPrice);
			System.out.println(updateddetails.getPrice() + "  printing final price");
		}

		// updateddetails.setPrice(finalprice - oldPrice);
		if (reservation.getRoomType() != null) {
			updateddetails.setRoomType(reservation.getRoomType());
		}

		if (reservation.getFromDate() != null) {
			updateddetails.setFromDate(reservation.getFromDate());
		}
		if (reservation.getToDate() != null) {
			updateddetails.setToDate(reservation.getToDate());
		}
		if (reservation.getNumber_of_adults() != null) {
			updateddetails.setNumber_of_adults(reservation.getNumber_of_adults());
		}
		if (reservation.getNumber_of_children() != null) {
			updateddetails.setNumber_of_children(reservation.getNumber_of_children());
		}
		if (reservation.getAmenities() != null) {
			Amenities amenities = new Amenities();

			if (reservation.getAmenities().isAccess_to_fitness_room()) {
				amenities.setAccess_to_fitness_room(reservation.getAmenities().isAccess_to_fitness_room());
			} else {
				amenities.setAccess_to_fitness_room(reservation.getAmenities().isAccess_to_fitness_room());
			}
			if (reservation.getAmenities().isAccess_to_swimming_Pool_Jacuzzi()) {
				amenities.setAccess_to_swimming_Pool_Jacuzzi(
						reservation.getAmenities().isAccess_to_swimming_Pool_Jacuzzi());
			} else {
				amenities.setAccess_to_swimming_Pool_Jacuzzi(
						reservation.getAmenities().isAccess_to_swimming_Pool_Jacuzzi());
			}
			if (reservation.getAmenities().isAll_meals_included()) {
				amenities.setAll_meals_included(reservation.getAmenities().isAll_meals_included());
			} else {
				amenities.setAll_meals_included(reservation.getAmenities().isAll_meals_included());
			}
			if (reservation.getAmenities().isDaily_continental_breakfast()) {
				amenities.setDaily_continental_breakfast(reservation.getAmenities().isDaily_continental_breakfast());
			} else {
				amenities.setDaily_continental_breakfast(reservation.getAmenities().isDaily_continental_breakfast());
			}
			if (reservation.getAmenities().isDaily_parking()) {
				amenities.setDaily_parking(reservation.getAmenities().isDaily_parking());
			} else {
				amenities.setDaily_parking(reservation.getAmenities().isDaily_parking());
			}
			updateddetails.setAmenities(amenities);
		}

	}

}
