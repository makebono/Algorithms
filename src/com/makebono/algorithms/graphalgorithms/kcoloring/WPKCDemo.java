package com.makebono.algorithms.graphalgorithms.kcoloring;

import com.makebono.datastructures.graph.Vertex;

/** 
 * @ClassName: WPKCDemo 
 * @Description: Demo for Welsh Powell's k-coloring
 * @author makebono
 * @date 2018年1月16日 下午4:14:17 
 *  
 */
public class WPKCDemo {
    public static void show() {
        final WelshPowellKColoringSolver kcs = new WelshPowellKColoringSolver();

        final Vertex<Character> v1 = new Vertex<Character>(1, ' ');
        final Vertex<Character> v2 = new Vertex<Character>(2, ' ');
        final Vertex<Character> v3 = new Vertex<Character>(3, ' ');
        final Vertex<Character> v4 = new Vertex<Character>(4, ' ');
        final Vertex<Character> v5 = new Vertex<Character>(5, ' ');
        final Vertex<Character> v6 = new Vertex<Character>(6, ' ');

        kcs.add(v1, v2);
        kcs.add(v1, v5);
        kcs.add(v1, v6);
        kcs.add(v2, v3);
        kcs.add(v2, v5);
        kcs.add(v5, v4);
        kcs.dealWithIt();
        System.out.println(kcs);
    }
}
