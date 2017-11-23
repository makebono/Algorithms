package com.makebono.sortandsearch.tools.swapper;

/** 
 * @ClassName: Swapper 
 * @Description: Swapper class help swap elements in array
 * @author makebono
 * @date 2017年11月15日 上午9:31:32 
 *  
 */
public class Swapper {
    public static void swap(final int index1, final int index2, final int[] input) {
        final int temp = input[index1];
        input[index1] = input[index2];
        input[index2] = temp;
    }
}
