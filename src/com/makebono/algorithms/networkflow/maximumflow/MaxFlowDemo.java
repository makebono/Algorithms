package com.makebono.algorithms.networkflow.maximumflow;

import com.makebono.datastructures.graph.Vertex;

/** 
 * @ClassName: MaxFlowDemo 
 * @Description: Demo of maximum flow
 * @author makebono
 * @date 2018年1月16日 下午4:47:48 
 *  
 */
public class MaxFlowDemo {
    public static void show() {
        final Vertex<String> s = new Vertex<String>(0, "Vancouver");
        final Vertex<String> v1 = new Vertex<String>(1, "Edmonton");
        final Vertex<String> v2 = new Vertex<String>(2, "Calgary");
        final Vertex<String> v3 = new Vertex<String>(3, "Saskatoon");
        final Vertex<String> v4 = new Vertex<String>(4, "Regina");
        final Vertex<String> t = new Vertex<String>(5, "Winnipeg");

        final FordFulkersonMethod ffm = new FordFulkersonMethod();
        ffm.add(s, v1, 16);
        ffm.add(s, v2, 13);
        ffm.add(v1, v3, 12);
        ffm.add(v2, v1, 4);
        ffm.add(v3, v2, 9);
        ffm.add(v2, v4, 14);
        ffm.add(v4, v3, 7);
        ffm.add(v3, t, 20);
        ffm.add(v4, t, 4);

        ffm.setSource(s);
        ffm.setSink(t);
        ffm.maximumFlow();
    }
}
