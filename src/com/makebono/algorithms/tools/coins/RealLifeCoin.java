package com.makebono.algorithms.tools.coins;

import java.math.BigDecimal;

/** 
 * @ClassName: Coins 
 * @Description: Enum of coins, coin value in pracical life will guarantee a simple greedy approach to be optimal.
 * @author makebono
 * @date 2017年11月16日 上午9:34:48 
 *  
 */
public enum RealLifeCoin {

    // This is deadly easy but... If not using BigDecimal, double or float will lose acuracy and cause errors. What I
    // learnt from these few lines is, anything not integer, use BigDecimal. And decimal parameter parse in constructor
    // must be strings, if you create a BigDecimal by double, it will lose accuracy instantly. For example new
    // BigDecimal(0.5) will mess things up and new BigDecimal("0.5") will work.
    Penny(new BigDecimal("0.01")), Nickel(new BigDecimal("0.05")), Dime(new BigDecimal("0.1")), Quarter(
            new BigDecimal("0.25")), Loonie(new BigDecimal("1")), Toonie(new BigDecimal("2"));

    private BigDecimal value;

    private RealLifeCoin(final BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public void setValue(final BigDecimal value) {
        this.value = value;
    }

}
