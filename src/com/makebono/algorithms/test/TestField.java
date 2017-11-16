package com.makebono.algorithms.test;

import com.makebono.algorithms.coinchangeproblem.DynamicCoinChange;
import com.makebono.algorithms.coinchangeproblem.GreedyCoinChange;
import com.makebono.algorithms.coinchangeproblem.RealLifeCoinChange;

/** 
 * @ClassName: TestField 
 * @Description: Test class
 * @author makebono
 * @date 2017年11月16日 上午9:50:03 
 *  
 */
public class TestField {
    public static void main(final String[] args) {
        DynamicCoinChange.change(35);
        GreedyCoinChange.change(35);
        RealLifeCoinChange.change(35);
    }
}
