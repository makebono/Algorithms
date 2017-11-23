package com.makebono.sortandsearch.sort;

import com.makebono.sortandsearch.search.LinearSearch;
import com.makebono.sortandsearch.sort.sortinterface.Sorter;
import com.makebono.sortandsearch.tools.swapper.Swapper;

/** 
 * @ClassName: SelectionSort 
 * @Description: Selection sort that does selection sort
 * @author makebono
 * @date 2017年11月15日 上午10:35:55 
 *  
 */
public class SelectionSort implements Sorter {
    @Override
    public void sort(final int[] input) {
        for (int i = input.length - 1; i >= 0; i--) {
            final int max = LinearSearch.maxIndex(0, i + 1, input);
            Swapper.swap(max, i, input);
        }
    }

}
