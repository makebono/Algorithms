package com.makebono.algorithms.tools.dateenum;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/** 
 * @ClassName: WeekdaysEnum 
 * @Description: WeekdaysEnum
 * @author makebono
 * @date 2018年1月19日 上午11:11:54 
 *  
 */
public enum WeekdaysEnum {
    Monday(1), Tuesday(2), Wednesday(3), Thursday(4), Friday(5), Saturday(6), Sunday(0);

    private int day;
    private static Map<Integer, WeekdaysEnum> lookUpTable = new HashMap<Integer, WeekdaysEnum>();

    private WeekdaysEnum(final int day) {
        this.day = day;
    }

    static {
        for (final WeekdaysEnum dayByDay : EnumSet.allOf(WeekdaysEnum.class)) {
            lookUpTable.put(dayByDay.day, dayByDay);
        }
    }

    public static WeekdaysEnum getByInt(final int day) {
        return lookUpTable.get(day);
    }
}
