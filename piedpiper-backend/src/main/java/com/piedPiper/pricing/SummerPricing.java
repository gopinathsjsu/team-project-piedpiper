package com.piedpiper.pricing;

import static com.piedpiper.pricing.RoomRates.Family_Lounge_Rate;
import static com.piedpiper.pricing.RoomRates.Single_room_Rate;
import static com.piedpiper.pricing.RoomRates.Suite_Rate;
import static com.piedpiper.pricing.RoomRates.double_room_Rate;

import org.springframework.stereotype.Component;

@Component
public class SummerPricing implements Pricing{
	

	int price = 0;

	@Override
	public int getPricing(String roomType) {

		if (roomType.equalsIgnoreCase("Family_lounge")) {

			price = Family_Lounge_Rate + 140;
		}
		if (roomType.equalsIgnoreCase("single_room")) {

			price = Single_room_Rate + 40;
		}
		if (roomType.equalsIgnoreCase("double_room")) {

			price = double_room_Rate + 70;
		}
		if (roomType.equalsIgnoreCase("suite")) {

			price = Suite_Rate + 90;
		}

		return price;

	}
}

