package com.makebono.algorithms.sortandsearch.sort;

import com.makebono.algorithms.sortandsearch.sort.sortinterface.Sorter;
import com.makebono.algorithms.tools.sortandsearch.tools.swapper.Swapper;

/** 
 * @ClassName: InsertionSort 
 * @Description: Insertion sort that does insertion sort
 * @author makebono
 * @date 2017年11月15日 上午9:12:14 
 *  
 */
public class InsertionSort implements Sorter {
    @Override
    public void sort(final int[] input) {
        for (int i = 1; i < input.length; i++) {
            for (int n = i; n > 0; n--) {
                if (input[n] < input[n - 1]) {
                    Swapper.swap(n, n - 1, input);
                }
            }
        }
    }
}
