package com.hotel.booking.model;

public enum Room {
    SINGLE(100),
    DOUBLE(200),
    SUITE(350);

    public final int pricePerNight;

    private Room(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
}
