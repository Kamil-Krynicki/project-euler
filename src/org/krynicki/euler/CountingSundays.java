package org.krynicki.euler;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;

import static java.time.Year.isLeap;

/**
 * Created by K on 2016-10-12.
 */
public class CountingSundays {
    /*
    You are given the following information, but you may prefer to do some research for yourself.

    1 Jan 1900 was a Monday.
    Thirty days has September,
    April, June and November.
    All the rest have thirty-one,
    Saving February alone,
    Which has twenty-eight, rain or shine.
    And on leap years, twenty-nine.

    A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
    How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
    */

    public static void main(String[] args) {
        CountingSundays l = new CountingSundays();

        long t1 = System.currentTimeMillis();
        System.out.println(l.countDaysRange(DayOfWeek.TUESDAY, (short)1901, (short)2000, (short)1, DayOfWeek.SUNDAY));
        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
    }

    short[] normalYear;
    short[] normalYearSum;
    short[] leapYear;
    short[] leapYearSum;

    public CountingSundays() {
        normalYear = new short[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        leapYear = new short[]{0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        normalYearSum = new short[13];
        leapYearSum = new short[13];
        for (short i = 1; i <= 12; i++) {
            normalYearSum[i] = (short) (normalYearSum[i - 1] + normalYear[i]);
            leapYearSum[i] = (short) (leapYearSum[i - 1] + leapYear[i]);
        }
    }

    int countDaysRange(DayOfWeek firstOfJan, short yearFrom, short yearTo, short dayOfMonth, DayOfWeek typeOfDayToCount) {
        short currentYear = yearFrom;
        DayOfWeek currentFirstOfJan = firstOfJan;

        int sum = 0;

        while(currentYear <= yearTo) {
            sum += countDays(currentFirstOfJan, currentYear, dayOfMonth, typeOfDayToCount);
            currentFirstOfJan = DayOfWeek.of((currentFirstOfJan.getValue()-1+(isLeap(currentYear)?366:365))%7+1);
            currentYear++;
        }

        return sum;
    }

    short countDays(DayOfWeek firstOfJan, short year, short dayOfMonth, DayOfWeek typeOfDayToCount) {
        short[] yearStructure = isLeap(year) ? leapYearSum : normalYearSum;

        short result = 0;

        System.out.print("YEAR:" +year);

        for (int i = 0; i < 12; i++) {
            if((yearStructure[i]+firstOfJan.getValue()+dayOfMonth-2)%7==typeOfDayToCount.getValue()-1) {
                result++;
            }
        }

        System.out.println(" (" +result+")");

        return result;
    }
}

