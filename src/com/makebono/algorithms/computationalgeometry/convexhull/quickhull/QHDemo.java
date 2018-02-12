package com.makebono.algorithms.computationalgeometry.convexhull.quickhull;

/** 
 * @ClassName: QHDemo 
 * @Description: QHDemo 
 * @author makebono
 * @date 2018年2月12日 上午11:53:15 
 *  
 */
public class QHDemo {
    public static void show() {
        final QuickHull<Integer> gs = new QuickHull<Integer>();
        gs.add(1, 1, 3, -3);
        gs.add(2, 3, 0.5, 0.5);
        gs.add(3, 3, 3, 0);
        gs.add(4, 3, 0, 3);
        gs.add(5, 3, -2, 1);
        gs.add(6, 3, -1.5, -2);
        System.out.println(gs);
    }
}
