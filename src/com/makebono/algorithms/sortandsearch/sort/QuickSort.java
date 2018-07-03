package com.makebono.algorithms.sortandsearch.sort;

import com.makebono.algorithms.sortandsearch.sort.sortinterface.Sorter;
import com.makebono.algorithms.tools.sortandsearch.tools.swapper.Swapper;

/** 
 * @ClassName: QuickSort 
 * @Description: Quick sort that does quick sort
 * @author makebono
 * @date 2017年11月15日 上午10:37:43 
 *  
 */
public class QuickSort implements Sorter {
    @Override
    public void sort(final int[] input) {
        this.quickSort(0, input.length, input);
    }

    private void quickSort(final int begin, final int end, final int[] input) {
        if (begin < end) {
            final int pivot = partition(begin, end, input);
            this.quickSort(begin, pivot, input);
            this.quickSort(pivot + 1, end, input);
        }
    }

    private int partition(final int begin, final int end, final int[] input) {
        int pivot = begin;

            // Swap once a time, that's why using continue.
        for (int n = begin; n < end; n++) {
        	if ((input[n] < input[pivot] && pivot < n) || (input[n] > input[pivot] && pivot > n)) {
        		Swapper.swap(n, pivot, input);
        		pivot = n;
        		continue;
        	}
        }

        return pivot;
    }
}
