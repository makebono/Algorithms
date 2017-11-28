package com.makebono.algorithms.test;

import java.io.IOException;

import com.makebono.algorithms.computervision.colorindexing.Tracking;
import com.makebono.algorithms.tools.computervision.matrix.ImageMatrix;

/** 
 * @ClassName: TestField 
 * @Description: Test class
 * @author makebono
 * @date 2017年11月16日 上午9:50:03 
 *  
 */
public class TestField {
    public static void main(final String[] args) throws IOException {
        /*
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
        
        final int[][] data = new int[3][3];
        final int[] d1 = { 1, 2, 3 };
        final int[] d2 = { 4, 5, 6 };
        final int[] d3 = { 7, 8, 9 };
        data[0] = d1;
        data[1] = d2;
        data[2] = d3;
        
        final int[][] mask = new int[3][3];
        final int[] m1 = { 1, 1, 1 };
        final int[] m2 = { 1, 1, 1 };
        final int[] m3 = { 1, 1, 1 };
        mask[0] = m1;
        mask[1] = m2;
        mask[2] = m3;
        
        final int[][] result = Convolution.conv2trivial(data, mask);
        
        for (int i = 0; i < 3; i++) {
            for (int o = 0; o < 3; o++) {
                System.out.print(result[i][o] + " ");
            }
            System.out.println();
        }
        */
        final ImageMatrix desk = new ImageMatrix("desk.jpg");
        final ImageMatrix bottle = new ImageMatrix("bottle.png");
        Tracking.backProjection(bottle, desk);

    }
}
