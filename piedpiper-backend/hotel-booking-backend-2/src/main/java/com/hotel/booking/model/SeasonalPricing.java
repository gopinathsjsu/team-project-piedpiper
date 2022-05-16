package com.hotel.booking.model;

public enum SeasonalPricing {
    SUMMER(2.5),
    CHRISTMAS(2.5);

    public final double priceIncrease;

    private SeasonalPricing(double priceIncrease) {
        this.priceIncrease = priceIncrease;
    }
}
