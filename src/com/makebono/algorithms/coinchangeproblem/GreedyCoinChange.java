package com.makebono.algorithms.coinchangeproblem;

import com.makebono.algorithms.coinchangeproblem.coins.NonOptimizedCoin;

/** 
 * @ClassName: GreedyCoinChange 
 * @Description: Greedy approach in virtual cases, does not guarantee optimization. 
 * Same as practical case except value of coin is not practical. 
 * @author makebono
 * @date 2017年11月16日 上午11:40:46 
 *  
 */
public class GreedyCoinChange {
    private final int total;

    private GreedyCoinChange(final int total) {
        this.total = total;
    }

    public static void change(final int total) {
        final GreedyCoinChange temp = new GreedyCoinChange(total);

        int amount = temp.total;
        final int[] change = new int[3];

        while (amount > 0) {
            if (amount >= NonOptimizedCoin.Fifteen.getValue()) {
                amount -= NonOptimizedCoin.Fifteen.getValue();
                change[2]++;

            } else if (amount >= NonOptimizedCoin.Ten.getValue()) {
                amount -= NonOptimizedCoin.Ten.getValue();
                change[1]++;

            } else if (amount >= NonOptimizedCoin.Loonie.getValue()) {
                amount -= NonOptimizedCoin.Loonie.getValue();
                change[0]++;

            }
        }
        System.out.printf("Your total change is: %d\nGreedy way:\n    Your change would be:\n        ", temp.total);
        int coins = 0;
        for (int i = 2; i >= 0; i--) {
            if (change[i] != 0) {
                System.out.printf(NonOptimizedCoin.values()[i].name() + ": %d\n        ", change[i]);
                coins += change[i];
            }
        }
        System.out.printf("\nTotal number of coins is: %d\n\n", coins);
    }
}
