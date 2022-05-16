package com.hotel.booking.model;


public class Amenities {
    private boolean dailyContinentalBreakfast = false;
    private boolean fitnessRoomAccess = false;
    private boolean swimmingPoolAndJacuzziAccess = false;
    private boolean dailyParking = false;
    private boolean allMeals = false;

    public Amenities(boolean dailyContinentalBreakfast, boolean fitnessRoomAccess, boolean swimmingPoolAndJacuzziAccess, boolean dailyParking, boolean allMeals) {
        this.dailyContinentalBreakfast = dailyContinentalBreakfast;
        this.fitnessRoomAccess = fitnessRoomAccess;
        this.swimmingPoolAndJacuzziAccess = swimmingPoolAndJacuzziAccess;
        this.dailyParking = dailyParking;
        this.allMeals = allMeals;
    }

    public boolean isDailyContinentalBreakfast() {
        return dailyContinentalBreakfast;
    }

    public void setDailyContinentalBreakfast(boolean dailyContinentalBreakfast) {
        this.dailyContinentalBreakfast = dailyContinentalBreakfast;
    }

    public boolean isFitnessRoomAccess() {
        return fitnessRoomAccess;
    }

    public void setFitnessRoomAccess(boolean fitnessRoomAccess) {
        this.fitnessRoomAccess = fitnessRoomAccess;
    }

    public boolean isSwimmingPoolAndJacuzziAccess() {
        return swimmingPoolAndJacuzziAccess;
    }

    public void setSwimmingPoolAndJacuzziAccess(boolean swimmingPoolAndJacuzziAccess) {
        this.swimmingPoolAndJacuzziAccess = swimmingPoolAndJacuzziAccess;
    }

    public boolean isDailyParking() {
        return dailyParking;
    }

    public void setDailyParking(boolean dailyParking) {
        this.dailyParking = dailyParking;
    }

    public boolean isAllMeals() {
        return allMeals;
    }

    public void setAllMeals(boolean allMeals) {
        this.allMeals = allMeals;
    }
}
