/**Note:
 * Easy and useful algorithm. Work with enumeration and global abstract static constant class to get the output more 
 * readable and comprehensible. Accepts input format yyyymmdd, for example 20180101.
 * Why does this algorithm have such freaking awesome name?
 */
package com.makebono.algorithms.astronomy.doomsdayrule;

import java.security.InvalidParameterException;

import com.makebono.algorithms.astronomy.doomsdayrule.doomsdays.Doomsdays;
import com.makebono.algorithms.astronomy.doomsdayrule.weekdayscalculatorinterface.DoomsdayCalculator;
import com.makebono.algorithms.tools.dateenum.MonthEnum;
import com.makebono.algorithms.tools.dateenum.WeekdaysEnum;

/** 
 * @ClassName: WeekDaysCalculatorImpl 
 * @Description: Decide an input date is what day in a week. Doomsday algorithm.
 * @author makebono
 * @date 2018年1月19日 上午11:05:48 
 *  
 */
public class WeekDaysCalculatorImpl implements DoomsdayCalculator {
    // Calculate which day in a week is the doomsday. Depends on the year input.
    private int calculateDoomsday(final String date) {
        final int year = Integer.valueOf(date.substring(0, 4));
        final int yearIn2Digits = Integer.valueOf(date.substring(2, 4));
        int anchor = 0;

        // Every 400 years is a cycle. If need to support input out of this range, simply modify here.
        if (year >= 1800 && year <= 1899) {
            anchor = Doomsdays.Anchor1;
        } else if (year >= 1900 && year <= 1999) {
            anchor = Doomsdays.Anchor2;
        } else if (year >= 2000 && year <= 2099) {
            anchor = Doomsdays.Anchor3;
        } else if (year >= 2100 && year <= 2199) {
            anchor = Doomsdays.Anchor4;
        }

        return (yearIn2Digits / 12 + yearIn2Digits % 12 + (yearIn2Digits % 12) / 4 + anchor) % 7;
    }

    private String calculateWeekdays(final String date) throws InvalidParameterException {
        if (date.length() != 8) {
            throw new InvalidParameterException("Invalid input. Please double check.");
        }
        final int doomsday = this.calculateDoomsday(date);

        final String year = date.substring(0, 4);
        final String month = date.substring(4, 6);
        String dayString = date.substring(6);
        final int day = Integer.valueOf(dayString);
        String monthlyDoomsday = "";
        int result;

        final char firstDigit = dayString.charAt(0);
        final char lastDigit = dayString.charAt(1);

        if (lastDigit == '1' && firstDigit != '1') {
            dayString += "st";
        } else if (lastDigit == '2' && firstDigit != '1') {
            dayString += "nd";
        } else if (lastDigit == '3' && firstDigit != '1') {
            dayString += "rd";
        } else {
            dayString += "th";
        }

        dayString = dayString.charAt(0) == '0' ? dayString.substring(1) : dayString;

        if (month.equals("01") || month.equals("02")) {
            final boolean isLeap = Integer.valueOf(year) % 4 == 0 ? true : false;

            if (isLeap) {
                monthlyDoomsday = month.equals("01") ? Doomsdays.Jan04 : Doomsdays.Feb29;
            } else {
                monthlyDoomsday = month.equals("01") ? Doomsdays.Jan03 : Doomsdays.Feb28;
            }

        } else {
            switch (month) {
            case "03":
                monthlyDoomsday = Doomsdays.Mar;
                break;
            case "04":
                monthlyDoomsday = Doomsdays.Apr;
                break;
            case "05":
                monthlyDoomsday = Doomsdays.May;
                break;
            case "06":
                monthlyDoomsday = Doomsdays.Jun;
                break;
            case "07":
                monthlyDoomsday = Doomsdays.Jul;
                break;
            case "08":
                monthlyDoomsday = Doomsdays.Aug;
                break;
            case "09":
                monthlyDoomsday = Doomsdays.Sep;
                break;
            case "10":
                monthlyDoomsday = Doomsdays.Oct;
                break;
            case "11":
                monthlyDoomsday = Doomsdays.Nov;
                break;
            case "12":
                monthlyDoomsday = Doomsdays.Dec;
                break;
            }
        }

        final int dayOfDoom = Integer.valueOf(monthlyDoomsday.substring(2));

        if (day > dayOfDoom) {
            result = (doomsday + (day - dayOfDoom)) % 7;
        } else {
            result = doomsday - (dayOfDoom - day) > 0 ? doomsday - (dayOfDoom - day)
                    : (doomsday - (dayOfDoom - day)) % 7 + 7;
        }

        return "Date input " + MonthEnum.getByInt(Integer.valueOf(month)) + " " + dayString + " " + year + " is "
                + WeekdaysEnum.getByInt(result);
    }

    @Override
    public String weekday(final String date) {
        return this.calculateWeekdays(date);
    }
}