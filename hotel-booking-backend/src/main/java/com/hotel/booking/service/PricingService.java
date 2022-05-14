package com.hotel.booking.service;

import com.hotel.booking.model.DayPricing;
import com.hotel.booking.model.Holidays;
import com.hotel.booking.model.SeasonalPricing;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.util.List;

@Service
public class PricingService {

    public static boolean isWeekend(LocalDate date){
        DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        switch (day) {
            case SATURDAY:
            case SUNDAY:
                return true;
            default:
                return false;
        }
    }

    public static boolean isHoliday(LocalDate date){
        List<LocalDate> holidays = Holidays.getHolidays();
        for(LocalDate holiday: holidays){
            if(holiday.isEqual(date))
                return true;
        }
        return false;
    }

    public static boolean isSummer(LocalDate date){
        if(date.getMonthValue() == 6 || date.getMonthValue() == 7 || date.getMonthValue() == 8){
            return true;
        }
        return false;
    }

    public static boolean isChristmas(LocalDate date){
        if(date.getMonthValue() == 12 && date.getDayOfMonth() > 23 && date.getDayOfMonth() <=31){
            return true;
        }
        return false;
    }

    public static double getPriceIncrease(LocalDate checkInDate, LocalDate checkOutDate){
        if(PricingService.isSummer(checkInDate) || PricingService.isSummer(checkOutDate)){
             return SeasonalPricing.SUMMER.priceIncrease;
        }
        else if(PricingService.isChristmas(checkInDate) || PricingService.isChristmas(checkOutDate)){
            return SeasonalPricing.CHRISTMAS.priceIncrease;
        }
        else if(PricingService.isHoliday(checkInDate) || PricingService.isHoliday(checkOutDate)){
            return DayPricing.HOLIDAY.priceIncrease;
        }
        else if(PricingService.isWeekend(checkInDate) || PricingService.isWeekend(checkOutDate)){
            return DayPricing.WEEKEND.priceIncrease;
        }
        return 1;
    }
}
