package com.piedpiper.api;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class BookingResponse {

	
	private String reservationID;
	private String customerName;
	private String emailID;
	private String phoneNumber;
	private String roomType;
	private int number_of_adults;
	private int number_of_children;
	private LocalDate fromDate;
	private LocalDate toDate;
	private Amenities amenities;
	private int price;
	private double rewardPoints;
	private int numberOfRooms;

	public String getReservationID() {
		return reservationID;
	}

	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the numbder_of_children
	 */
	public int getNumber_of_children() {
		return number_of_children;
	}

	/**
	 * @param numbder_of_children the number_of_children to set
	 */
	public void setNumber_of_children(int number_of_children) {
		this.number_of_children = number_of_children;
	}

	/**
	 * @return the amenities
	 */
	public Amenities getAmenities() {
		return amenities;
	}

	/**
	 * @param amenities the amenities to set
	 */
	public void setAmenities(Amenities amenities) {
		this.amenities = amenities;
	}

	/**
	 * @return the number_of_adults
	 */
	public int getNumber_of_adults() {
		return number_of_adults;
	}

	/**
	 * @param number_of_adults the number_of_adults to set
	 */
	public void setNumber_of_adults(int number_of_adults) {
		this.number_of_adults = number_of_adults;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the rewardPoints
	 */
	public double getRewardPoints() {
		return rewardPoints;
	}

	/**
	 * @param rewardPoints the rewardPoints to set
	 */
	public void setRewardPoints(double rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	/**
	 * @return the numberOfRooms
	 */
	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	/**
	 * @param numberOfRooms the numberOfRooms to set
	 */
	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	
}
