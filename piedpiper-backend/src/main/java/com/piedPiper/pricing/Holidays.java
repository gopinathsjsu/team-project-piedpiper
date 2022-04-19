package com.piedpiper.pricing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Holidays {

	//Mon, Jan 17, 2022
	private LocalDate Martin_Luther_King_day = LocalDate.of(2022, 1, 17);
	//Mon, May 30, 2022
	private LocalDate Memorial_day = LocalDate.of(2022, 5, 30);
	//Mon, Jul 4, 2022
	private LocalDate Independence_day = LocalDate.of(2022, 7, 04);
	// Mon, Sep 5, 2022
	private LocalDate Labor_day = LocalDate.of(2022, 9, 05);
	//Fri, Nov 11, 2022
	private LocalDate Veterans_day = LocalDate.of(2022, 11, 11);
	//Thu, Nov 24, 2022
	private LocalDate ThanksGiving_day = LocalDate.of(2022, 11, 24);
	//Mon, Dec 26, 2022
	private LocalDate Christmas_day = LocalDate.of(2022, 12, 26);
	
	public List<LocalDate> list_holidays() {
		
		List<LocalDate> dates = new ArrayList<LocalDate>();

		dates.add(Independence_day);
		dates.add(Martin_Luther_King_day);
		dates.add(Memorial_day);
		dates.add(Veterans_day);
		dates.add(Christmas_day);
		dates.add(Labor_day);
		dates.add(ThanksGiving_day);
		
		return dates;
		
	}


	

	
}
