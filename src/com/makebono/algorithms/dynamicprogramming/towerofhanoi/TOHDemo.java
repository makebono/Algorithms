package com.makebono.algorithms.dynamicprogramming.towerofhanoi;

import java.io.IOException;

/** 
 * @ClassName: TOHDemo 
 * @Description: Demo of Tower of Hanoi
 * @author makebono
 * @date 2018年1月16日 下午4:40:56 
 *  
 */
public class TOHDemo {
    public static void show() throws IOException {
        final TowerOfHanoi toh = new TowerOfHanoi(7);
        toh.game(1, 3);
    }
}
