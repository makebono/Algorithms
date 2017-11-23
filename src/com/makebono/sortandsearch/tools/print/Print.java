package com.makebono.sortandsearch.tools.print;

import java.util.Arrays;

/** 
 * @ClassName: Print 
 * @Description: Print array 
 * @author makebono
 * @date 2017年11月15日 上午10:24:17 
 *  
 */
public class Print {
    public static void print(final int[] input) {
        System.out.print("Elements in this array:\n    {");
        for (final int i : Arrays.copyOfRange(input, 0, input.length - 1)) {
            System.out.print(i + ", ");
        }
        System.out.print(input[input.length - 1] + "}\n");
    }
}
