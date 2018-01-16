package com.makebono.algorithms.computationalgeometry.closestpointpair;

import java.util.Random;

import com.makebono.datastructures.graph.Vertex;

/** 
 * @ClassName: CPPDemo 
 * @Description: Demo of Closest points pair
 * @author makebono
 * @date 2018年1月16日 下午4:23:48 
 *  
 */
public class CPPDemo {
    public static void show() {
        final ClosestPair<Integer> cp = new ClosestPair<Integer>();
        final Random rnd = new Random();
        // Time cost increase dramatically as i's upper bound goes up.
        for (int i = 0; i < 2000; i++) {
            cp.add(i + 1, 0, rnd.nextDouble() * 10, rnd.nextDouble() * 10);
        }
        cp.init();
        final Vertex<Integer>[] result = cp.bruteForcePickClosestPair();
        System.out.println(result[0]);
        System.out.println(result[1]);
        final Vertex<Integer>[] result2 = cp.closestPair();
        System.out.println(result2[0]);
        System.out.println(result2[1]);
    }
}
