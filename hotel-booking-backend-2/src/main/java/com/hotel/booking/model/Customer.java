package com.hotel.booking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class Customer {
    //@Id
    //private int _id;
    private String firstName;
    private String lastName;
    //private String username;
    private String password;
    private String phoneNumber;
    @Id
    private String email;
    private int pointsRemaining;

    public Customer(String firstName, String lastName, String password, String phoneNumber,
                    String email, int pointsRemaining) {
        //this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        //this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.pointsRemaining = pointsRemaining;
    }

    public void setPointsRemaining(int pointsRemaining) {
        this.pointsRemaining = pointsRemaining;
    }

    public int getPointsRemaining() {
        return pointsRemaining;
    }
/*
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }*/

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
