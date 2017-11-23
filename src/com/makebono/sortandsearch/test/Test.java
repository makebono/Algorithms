package com.makebono.sortandsearch.test;

import com.makebono.sortandsearch.sort.MergeSort;
import com.makebono.sortandsearch.sort.sortinterface.Sorter;
import com.makebono.sortandsearch.tools.print.Print;

/** 
 * @ClassName: Test 
 * @Description: Test
 * @author makebono
 * @date 2017年11月15日 上午8:47:24 
 *  
 */
public class Test {
    public static void main(final String[] args) {
        final int[] i = { 0, 0, 0, 1, 2, 3, 1, 4, 12, 444, 1, 5, 1, 235, 26, 11, -9999, -1, -2 };

        final Sorter s = new MergeSort();

        Print.print(i);

        s.sort(i);

        Print.print(i);

    }
}
