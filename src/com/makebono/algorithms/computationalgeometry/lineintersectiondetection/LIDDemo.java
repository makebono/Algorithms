package com.makebono.algorithms.computationalgeometry.lineintersectiondetection;

import com.makebono.algorithms.computationalgeometry.lineintersectiondetection.tools.Line;

/** 
 * @ClassName: LIDDemo 
 * @Description: Demo of line intersection detection
 * @author makebono
 * @date 2018年1月16日 下午4:34:27 
 *  
 */
public class LIDDemo {
    public static void show() {
        final Line l1 = new Line(-3, 1, 1, 5);
        final Line l2 = new Line(-1, 5, 4, 2);
        System.out.println(LineIntersection.detect(l2, l1));
    }
}
