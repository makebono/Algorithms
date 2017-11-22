package com.makebono.algorithms.dynamicprogramming.towerofhanoi;

import java.io.IOException;

/** 
 * @ClassName: Test 
 * @Description: Test class for Tower of Hanoi
 * @author makebono
 * @date 2017骞�11鏈�3鏃� 涓嬪崍1:45:46 
 *  
 */
public class Test {
    public static void main(final String[] args) throws IOException {
        // Input height of tower
        final TowerOfHanoi g1 = new TowerOfHanoi(7);
        // Use game(height, home rod, target rod) to print out result. A file will be generated at source repository.
        g1.game(7, 1, 3);
    }
}
