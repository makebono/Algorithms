package com.makebono.algorithms.tools.dateenum;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/** 
 * @ClassName: MonthEnum 
 * @Description: MonthEnum
 * @author makebono
 * @date 2018年1月19日 上午11:11:54 
 *  
 */
public enum MonthEnum {
    January(1), February(2), March(3), April(4), May(5), June(6), July(7), August(8), September(9), October(
            10), November(11), December(12);

    private int month;
    private static Map<Integer, MonthEnum> lookUpTable = new HashMap<Integer, MonthEnum>();

    private MonthEnum(final int month) {
        this.month = month;
    }

    static {
        for (final MonthEnum monthByMonth : EnumSet.allOf(MonthEnum.class)) {
            lookUpTable.put(monthByMonth.month, monthByMonth);
        }
    }

    public static MonthEnum getByInt(final int month) {
        return lookUpTable.get(month);
    }
}
