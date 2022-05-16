package com.hotel.booking.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Holidays {
    public static List<LocalDate> getHolidays(){
        LocalDate ThanksGivingDay = LocalDate.of(2022, 11, 24);
        LocalDate IndependenceDay = LocalDate.of(2022, 7, 04);
        LocalDate MartinLutherKingDay = LocalDate.of(2022, 1, 17);
        LocalDate LaborDay = LocalDate.of(2022, 9, 05);

        List<LocalDate> holidaysList = new ArrayList<>();
        holidaysList.add(ThanksGivingDay);
        holidaysList.add(IndependenceDay);
        holidaysList.add(MartinLutherKingDay);
        holidaysList.add(LaborDay);
        return holidaysList;
    }
}
