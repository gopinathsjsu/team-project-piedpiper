package com.piedpiper.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.piedpiper.api.Amenities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Document(collection = "reservations")
public class Reservation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String reservationID;
	private String customerName;
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String emailID;
	@NotBlank(message = "roomType is mandatory")
	private String roomType;
	private Integer adults;
	private Integer children;
	private LocalDate fromDate;
	private LocalDate toDate;
	private double rewardpoints;
	@NotNull(message = "Number of Rooms is mandatory")
	private int numberOfRooms;
	private int price;
	private Amenities amenities;
	public Reservation() {
		// TODO Auto-generated constructor stub
	}
	

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
	public Integer getNumber_of_children() {
		return children;
	}

	/**
	 * @param numbder_of_children the children to set
	 */
	public void setNumber_of_children(Integer number_of_children) {
		this.children = number_of_children;
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
	 * @return the adults
	 */
	public Integer getNumber_of_adults() {
		return adults;
	}

	/**
	 * @param adults the adults to set
	 */
	public void setNumber_of_adults(Integer number_of_adults) {
		this.adults = number_of_adults;
	}

	/**
	 * @return the rewardpoints
	 */
	public double getRewardpoints() {
		return rewardpoints;
	}

	/**
	 * @param rewardpoints the rewardpoints to set
	 */
	public void setRewardpoints(double rewardpoints) {
		this.rewardpoints = rewardpoints;
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

}
