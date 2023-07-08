package com.eph.datetimecalculator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CalculatorLogic {

    public LocalDateTime buildLocalDateTime(int year,int month,int day,int hour,int minute){
        LocalDateTime localDateTime = LocalDateTime.of(year,month,day,hour,minute);
        return localDateTime;
    }

    public DateDto calculateDateDifference(LocalDateTime date1, LocalDateTime date2){
        LocalDateTime tempDateTime = LocalDateTime.from( date1 );

        long years = tempDateTime.until( date2, ChronoUnit.YEARS );
        tempDateTime = tempDateTime.plusYears( years );

        long months = tempDateTime.until( date2, ChronoUnit.MONTHS );
        tempDateTime = tempDateTime.plusMonths( months );

        long days = tempDateTime.until( date2, ChronoUnit.DAYS );
        tempDateTime = tempDateTime.plusDays( days );

        long hours = tempDateTime.until( date2, ChronoUnit.HOURS );
        tempDateTime = tempDateTime.plusHours( hours );

        long minutes = tempDateTime.until( date2, ChronoUnit.MINUTES );
        DateDto dateDto = new DateDto();
        dateDto.setYear(years);
        dateDto.setMonth(months);
        dateDto.setDay(days);
        dateDto.setHour(hours);
        dateDto.setMinute(minutes);
        return dateDto;
    }
}
