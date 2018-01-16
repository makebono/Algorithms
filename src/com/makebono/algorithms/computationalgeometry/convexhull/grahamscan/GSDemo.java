package com.makebono.algorithms.computationalgeometry.convexhull.grahamscan;

/** 
 * @ClassName: GSDemo 
 * @Description: Demo of Graham scan.
 * @author makebono
 * @date 2018年1月16日 下午4:32:23 
 *  
 */
public class GSDemo {
    public static void show() {
        final GrahamScan<Integer> gs = new GrahamScan<Integer>();
        gs.add(1, 1, 3, -3);
        gs.add(2, 3, 0.5, 0.5);
        gs.add(3, 3, 3, 0);
        gs.add(4, 3, 0, 3);
        gs.add(5, 3, -2, 1);
        gs.add(6, 3, -1.5, -2);
        System.out.println(gs);
    }
}
