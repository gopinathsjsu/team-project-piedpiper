package com.piedpiper.api;

public class Amenities {

	private boolean continentalBreakfast = false;
	private boolean fitnessRoom = false;
	private boolean swimmingPool = false;
	private boolean parking = false;
	private boolean allMeals = false;

	public boolean isDaily_continental_breakfast() {
		return continentalBreakfast;
	}

	public void setDaily_continental_breakfast(boolean continentalBreakfast) {
		this.continentalBreakfast = continentalBreakfast;
	}

	public boolean isAccess_to_fitness_room() {
		return fitnessRoom;
	}

	public void setAccess_to_fitness_room(boolean fitnessRoom) {
		this.fitnessRoom = fitnessRoom;
	}

	public boolean isAccess_to_swimming_Pool_Jacuzzi() {
		return swimmingPool;
	}

	public void setAccess_to_swimming_Pool_Jacuzzi(boolean swimmingPool) {
		this.swimmingPool = swimmingPool;
	}

	public boolean isDaily_parking() {
		return parking;
	}

	public void setDaily_parking(boolean daily_parking) {
		this.parking = daily_parking;
	}

	public boolean isAll_meals_included() {
		return allMeals;
	}

	public void setAll_meals_included(boolean allMeals) {
		this.allMeals = allMeals;
	}

}
