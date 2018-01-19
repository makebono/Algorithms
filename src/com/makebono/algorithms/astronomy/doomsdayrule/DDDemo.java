package com.makebono.algorithms.astronomy.doomsdayrule;

import com.makebono.algorithms.astronomy.doomsdayrule.weekdayscalculatorinterface.DoomsdayCalculator;

/** 
 * @ClassName: DDDemo 
 * @Description: Doomsday algorithm demo class.
 * @author makebono
 * @date 2018年1月19日 下午3:38:18 
 *  
 */
public class DDDemo {
    final private static DoomsdayCalculator dc = new WeekDaysCalculatorImpl();

    public static void show(final String date) {
        System.out.println(dc.weekday(date));
    }
}
