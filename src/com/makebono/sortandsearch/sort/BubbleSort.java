package com.makebono.sortandsearch.sort;

import com.makebono.sortandsearch.sort.sortinterface.Sorter;
import com.makebono.sortandsearch.tools.swapper.Swapper;

/** 
 * @ClassName: BubbleSort 
 * @Description: Bubble sort that Bubblesort-ly sorts
 * @author makebono
 * @date 2017年11月15日 上午10:38:37 
 *  
 */
public class BubbleSort implements Sorter {
    @Override
    public void sort(final int[] input) {
        for (int i = input.length; i > 0; i--) {
            for (int n = 0; n < i - 1; n++) {
                if (input[n] > input[n + 1]) {
                    Swapper.swap(n, n + 1, input);
                }
            }
        }
    }
}
