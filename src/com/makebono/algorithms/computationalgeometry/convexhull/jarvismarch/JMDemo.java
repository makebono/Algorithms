package com.makebono.algorithms.computationalgeometry.convexhull.jarvismarch;

/** 
 * @ClassName: JMDemo 
 * @Description: Demo for Jarvis March
 * @author makebono
 * @date 2018年1月16日 下午4:28:34 
 *  
 */
public class JMDemo {
    public static void show() {
        final JarvisMarch<Integer> jm = new JarvisMarch<Integer>();
        jm.add(1, 1, 3, -3);
        jm.add(2, 3, 0.5, 0.5);
        jm.add(3, 3, 3, 0);
        jm.add(4, 3, 0, 3);
        jm.add(5, 3, -2, 1);
        jm.add(6, 3, -1.5, -2);
        System.out.println(jm);
    }
}
