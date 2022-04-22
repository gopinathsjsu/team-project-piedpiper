package com.piedpiper.model;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customers")
public class Customer {

	@Id
	private String _id;

	@NotBlank(message = "username is mandatory")
	private String username;

	@NotBlank(message = "password is mandatory")
	private String password;
	
	@NotBlank(message = "phoneNumber is mandatory")
	@Pattern(regexp="(^$|[0-9]{10})", message = "should be min of 10 digits")
	private String phoneNumber;


	/**
	 * @return the _id
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * @param _id the _id to set
	 */
	public void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
	private String email;
	
	@DBRef
	private Set<Role> roles;

	public Customer(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	// User getters and setters fields

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		this._id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
