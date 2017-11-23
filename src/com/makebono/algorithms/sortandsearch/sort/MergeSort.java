package com.makebono.algorithms.sortandsearch.sort;

import com.makebono.algorithms.sortandsearch.sort.sortinterface.Sorter;

/** 
 * @ClassName: MergeSort 
 * @Description: Merge sort does merge sort
 * @author makebono
 * @date 2017年11月15日 上午11:13:59 
 *  
 */
public class MergeSort implements Sorter {
    @Override
    public void sort(final int[] input) {
        this.merge(0, input.length, input);
    }

    // Divide array input into pieces, then recurssively sort the pieces from bottom to top.
    private void merge(final int begin, final int end, final int[] input) {
        if (begin < end - 1) {
            final int mid = (end + begin) / 2;
            merge(begin, mid, input);
            merge(mid, end, input);
            mergeSort(begin, end, input);
        }
    }

    private void mergeSort(final int begin, final int end, final int[] input) {
        int i1 = begin;
        int i2 = (end + begin) / 2;
        final int mid = i2;
        final int[] temp = new int[end - begin];
        int index = 0;

        // Stop until the 2 sub-arrays all added into the temp array. And after this manipulation, this temp array is
        // sorted.
        while (i1 < mid || i2 < end) {
            if (i1 >= mid) {
                temp[index++] = input[i2++];
            } else if (i2 >= end) {
                temp[index++] = input[i1++];
            } else {
                if (input[i1] <= input[i2]) {
                    temp[index++] = input[i1++];
                } else {
                    temp[index++] = input[i2++];
                }
            }
        }

        // Update the original array by the sorted temporary array.
        index = 0;
        for (int i = begin; i < end; i++) {
            input[i] = temp[index++];
        }
    }
}
