package com.makebono.algorithms.dynamicprogramming.coinchangeproblem.coins;

/** 
 * @ClassName: Coins 
 * @Description: Enum of coins, not optimized.
 * @author makebono
 * @date 2017年11月16日 上午9:34:48 
 *  
 */
public enum NonOptimizedCoin {

    // Virtual values of coins. In such case, using greedy approach won't guarantee to be optimal any more.
    // Only integer here for simplicity.
    Loonie(1), Ten(10), Fifteen(15);

    private int value;

    private NonOptimizedCoin(final int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(final int value) {
        this.value = value;
    }

}
