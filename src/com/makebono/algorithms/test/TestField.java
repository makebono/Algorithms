package com.makebono.algorithms.test;

import java.io.FileNotFoundException;

import com.makebono.algorithms.computationalgeometry.lineintersectiondetection.tools.Line;

/** 
 * @ClassName: TestField 
 * @Description: Test class
 * @author makebono
 * @date 2017年11月16日 上午9:50:03 
 *  
 */
public class TestField {
    public static void main(final String[] args) throws FileNotFoundException {
        final Line l1 = new Line(-3, 1, 1, 5);
        final Line l2 = new Line(1, 5, 4, 2);
        System.out.println(l1.length());

    }
}
