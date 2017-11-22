package com.makebono.algorithms.greedyalgorithm.greedycoinchangeproblem;

import java.math.BigDecimal;

import com.makebono.algorithms.tools.coins.RealLifeCoin;

/** 
 * @ClassName: RealLifeCoinChange 
 * @Description: Coin change problem deploying greedy approaches.
 * This uses coin values in real life, a simple greedy approach guarantees to be optimized.
 * @author makebono
 * @date 2017年11月16日 上午9:31:51 
 *  
 */
public class RealLifeCoinChange {
    private final BigDecimal total;

    private RealLifeCoinChange(final double total) {
        final String input = Double.toString(total);
        this.total = new BigDecimal(input);
    }

    public static void change(final double total) {
        final RealLifeCoinChange temp = new RealLifeCoinChange(total);

        BigDecimal amount = temp.total;
        final int[] change = new int[6];

        while (amount.compareTo(new BigDecimal("0")) > 0) {
            if (amount.compareTo(RealLifeCoin.Toonie.getValue()) >= 0) {
                amount = amount.subtract(RealLifeCoin.Toonie.getValue());
                change[5]++;

            } else if (amount.compareTo(RealLifeCoin.Loonie.getValue()) >= 0) {
                amount = amount.subtract(RealLifeCoin.Loonie.getValue());
                change[4]++;

            } else if (amount.compareTo(RealLifeCoin.Quarter.getValue()) >= 0) {
                amount = amount.subtract(RealLifeCoin.Quarter.getValue());
                change[3]++;

            } else if (amount.compareTo(RealLifeCoin.Dime.getValue()) >= 0) {
                amount = amount.subtract(RealLifeCoin.Dime.getValue());
                change[2]++;

            } else if (amount.compareTo(RealLifeCoin.Nickel.getValue()) >= 0) {
                amount = amount.subtract(RealLifeCoin.Nickel.getValue());
                change[1]++;

            } else if (amount.compareTo(RealLifeCoin.Penny.getValue()) >= 0) {
                amount = amount.subtract(RealLifeCoin.Penny.getValue());
                change[0]++;

            }
        }
        System.out.printf("Your total change is: %f\nGreedy way:\n    Your change would be:\n        ", temp.total);
        int coins = 0;
        for (int i = 5; i >= 0; i--) {
            if (change[i] != 0) {
                System.out.printf(RealLifeCoin.values()[i].name() + ": %d\n        ", change[i]);
                coins += change[i];
            }
        }
        System.out.printf("\nTotal number of coins is: %d\n\n", coins);
    }
}
