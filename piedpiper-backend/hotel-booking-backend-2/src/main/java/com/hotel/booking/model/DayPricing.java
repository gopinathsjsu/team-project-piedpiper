package com.hotel.booking.model;

public enum DayPricing {
    WEEKEND(1.5),
    HOLIDAY(2);

    public final double priceIncrease;

    private DayPricing(double priceIncrease) {
        this.priceIncrease = priceIncrease;
    }
}
