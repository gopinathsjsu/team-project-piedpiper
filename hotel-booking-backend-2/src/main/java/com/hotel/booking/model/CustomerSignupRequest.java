package com.hotel.booking.model;

public class CustomerSignupRequest {
    public String firstName;
    public String lastName;
    public String password;
    public String phoneNumber;
    public String email;

    public CustomerSignupRequest(){

    }

    public CustomerSignupRequest(String firstName, String lastName, String password, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
