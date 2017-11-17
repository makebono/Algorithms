package com.makebono.algorithms.test;

import com.makebono.algorithms.computationalgeometry.convexhull.grahamscan.GrahamScan;
import com.makebono.algorithms.computationalgeometry.convexhull.jarvismarch.JarvisMarch;

/** 
 * @ClassName: TestField 
 * @Description: Test class
 * @author makebono
 * @date 2017年11月16日 上午9:50:03 
 *  
 */
public class TestField {
    public static void main(final String[] args) {
        final GrahamScan<Integer> gs = new GrahamScan<Integer>();

        gs.add(1, 1, 1, -1);
        gs.add(2, 3, 0.5, -0.5);
        gs.add(3, 3, 2, 0);
        gs.add(4, 3, 1, 2);
        gs.add(5, 3, 1, 1);
        gs.add(6, 3, 0, 3);
        gs.add(7, 3, -1, 1);
        gs.add(8, 3, -2, 0);

        System.out.println(gs);

        final JarvisMarch<Integer> jm = new JarvisMarch<Integer>();
        jm.add(1, 1, 1, -1);
        jm.add(2, 3, 0.5, -0.5);
        jm.add(3, 3, 2, 0);
        jm.add(4, 3, 1, 2);
        jm.add(5, 3, 1, 1);
        jm.add(6, 3, 0, 3);
        jm.add(7, 3, -1, 1);
        jm.add(8, 3, -2, 0);

        System.out.println(jm);
    }
}
