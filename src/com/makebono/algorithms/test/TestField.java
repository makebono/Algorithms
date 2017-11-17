package com.makebono.algorithms.test;

import com.makebono.algorithms.grahamscan.GrahamScan;
import com.makebono.algorithms.grahamscan.HullContainer;

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

        final HullContainer<Integer> hull = gs.convexHull();
        gs.convexHull();
        gs.convexHull();
        gs.convexHull();
        gs.convexHull();
        for (int i = 0; i < hull.getVertices().size(); i++) {
            System.out.println(hull.getVertices().get(i));
        }

        for (int i = 0; i < hull.getEdges().size(); i++) {
            System.out.println(hull.getEdges().get(i));
        }

        System.out.println(gs);
    }
}
