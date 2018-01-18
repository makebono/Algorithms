package com.makebono.algorithms.graphalgorithms.kcoloring;

import com.makebono.datastructures.graph.Vertex;

/** 
 * @ClassName: KCDemo 
 * @Description: Demo for Welsh Powell's k-coloring
 * @author makebono
 * @date 2018年1月16日 下午4:14:17 
 *  
 */
public class KCDemo {
    public static void showWPKC() {
        final WelshPowellKColoringSolver kcs = new WelshPowellKColoringSolver();

        final Vertex<Character> v1 = new Vertex<Character>(1, ' ');
        final Vertex<Character> v2 = new Vertex<Character>(2, ' ');
        final Vertex<Character> v3 = new Vertex<Character>(3, ' ');
        final Vertex<Character> v4 = new Vertex<Character>(4, ' ');
        final Vertex<Character> v5 = new Vertex<Character>(5, ' ');
        final Vertex<Character> v6 = new Vertex<Character>(6, ' ');
        final Vertex<Character> v7 = new Vertex<Character>(7, ' ');
        final Vertex<Character> v8 = new Vertex<Character>(8, ' ');
        final Vertex<Character> v9 = new Vertex<Character>(9, ' ');
        final Vertex<Character> v10 = new Vertex<Character>(10, ' ');
        final Vertex<Character> v11 = new Vertex<Character>(11, ' ');
        final Vertex<Character> v12 = new Vertex<Character>(12, ' ');

        kcs.add(v1, v2);
        kcs.add(v1, v5);
        kcs.add(v1, v6);
        kcs.add(v2, v3);
        kcs.add(v2, v5);
        kcs.add(v5, v4);
        kcs.add(v1, v11);
        kcs.add(v2, v12);
        kcs.add(v7, v6);
        kcs.add(v7, v8);
        kcs.add(v9, v6);
        kcs.add(v5, v10);
        kcs.dealWithIt();
        System.out.println(kcs);
    }

    public static void showBTKC() {
        final BackTrackingKColoringSolver kcs = new BackTrackingKColoringSolver();

        final Vertex<Character> v1 = new Vertex<Character>(1, ' ');
        final Vertex<Character> v2 = new Vertex<Character>(2, ' ');
        final Vertex<Character> v3 = new Vertex<Character>(3, ' ');
        final Vertex<Character> v4 = new Vertex<Character>(4, ' ');
        final Vertex<Character> v5 = new Vertex<Character>(5, ' ');
        final Vertex<Character> v6 = new Vertex<Character>(6, ' ');
        final Vertex<Character> v7 = new Vertex<Character>(7, ' ');
        final Vertex<Character> v8 = new Vertex<Character>(8, ' ');
        final Vertex<Character> v9 = new Vertex<Character>(9, ' ');
        final Vertex<Character> v10 = new Vertex<Character>(10, ' ');
        final Vertex<Character> v11 = new Vertex<Character>(11, ' ');
        final Vertex<Character> v12 = new Vertex<Character>(12, ' ');

        kcs.add(v1, v2);
        kcs.add(v1, v5);
        kcs.add(v1, v6);
        kcs.add(v2, v3);
        kcs.add(v2, v5);
        kcs.add(v5, v4);
        kcs.add(v1, v11);
        kcs.add(v2, v12);
        kcs.add(v7, v6);
        kcs.add(v7, v8);
        kcs.add(v9, v6);
        kcs.add(v5, v10);
        kcs.dealWithIt();
        System.out.println(kcs);
    }
}
