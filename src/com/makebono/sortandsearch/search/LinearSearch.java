package com.makebono.sortandsearch.search;

import com.makebono.sortandsearch.search.searchinterface.Searcher;

/** 
 * @ClassName: LinearSearch 
 * @Description: Plain, trivial linear search. Return the index of first element meet in the list(if multiple answers exist). 
 * Acts as helper to find max and min value/index in some sort classes.
 * @author makebono
 * @date 2017年11月15日 上午8:38:21 
 *  
 */
public class LinearSearch implements Searcher {
    @Override
    public int search(final int data, final int[] input) {
        int result = Integer.MAX_VALUE;
        boolean found = false;
        for (int i = 0; i < input.length; i++) {
            if (data == input[i]) {
                found = true;
                result = i;
            }
        }

        if (!found) {
            System.out.println("The element you are looking for is not in this array.");
            return result;
        }

        return result;
    }

    public static int min(final int begin, final int end, final int[] input) {
        int result = input[begin];
        for (int i = begin + 1; i < end; i++) {
            if (result > input[i]) {
                result = input[i];
            }
        }
        return result;
    }

    public static int max(final int begin, final int end, final int[] input) {
        int result = input[begin];
        for (int i = begin + 1; i < end; i++) {

            if (result < input[i]) {
                result = input[i];
            }
        }
        return result;
    }

    public static int minIndex(final int begin, final int end, final int[] input) {
        int result = input[begin];
        int index = 0;
        for (int i = begin + 1; i < end; i++) {
            if (result > input[i]) {
                result = input[i];
                index = i;
            }
        }
        return index;
    }

    public static int maxIndex(final int begin, final int end, final int[] input) {
        int result = input[begin];
        int index = 0;
        for (int i = begin + 1; i < end; i++) {

            if (result < input[i]) {
                result = input[i];
                index = i;
            }
        }
        return index;
    }
}
