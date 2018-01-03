package com.makebono.algorithms.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import com.makebono.algorithms.string.patternmatching.knuth_morris_pratt.KMPMatching;

/** 
 * @ClassName: TestField 
 * @Description: Test class
 * @author makebono
 * @date 2017年11月16日 上午9:50:03 
 *  
 */
@SuppressWarnings("unchecked")
public class TestField {
    public static void main(final String[] args) throws IOException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        /*
        // Maximum network flow
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
        
        // Convolution
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
        
        // Color-Indexing
        final ImageMatrix environment = new ImageMatrix("inputSet/desk2.png");
        final ImageMatrix target = new ImageMatrix("inputSet/charger.png");
        Tracking.backProjection(target, environment);        
        
        // Huffman coding
        final HuffmanCoding hc = new HuffmanCoding("inputSet/huffmaninput.txt");
        hc.printText();
        hc.printCodeBook();
        hc.printDecodeBook();
        hc.printEncodedText();
        hc.printDecodedText();
        
        // A* algorithm
        final AStarPathFinder ASPF = new AStarPathFinder("inputSet/astarinput.txt");
        final ArrayList<AStarNode> list = ASPF.findPath(97, 791);        
        
        // Hungarian algorithm
        final HungarianWorkDistributor hwd = new HungarianWorkDistributor(4);
        hwd.solve();
        
        // Tower of Hanoi
        final TowerOfHanoi toh = new TowerOfHanoi(7);
        toh.game(7, 1, 3);
        
        // Line intersection
        final Line l1 = new Line(-3, 1, 1, 5);
        final Line l2 = new Line(-1, 5, 4, 2);
        System.out.println(LineIntersection.detect(l2, l1));
        BigDecimal[] xy = new BigDecimal[2];
        xy = LineIntersection.intersectAt(l1, l2);
        System.out.println(xy[0]);
        System.out.println(xy[1]);
         */
        /*             
        // KMPMatching paragraphing
        final KMPMatching kmpm = new KMPMatching("inputSet/output.txt");
        final String[] input = { "西安办事处", "郑州办事处", "江苏办事处", "山东办事处", "云南办事处", "宁夏办事处", "广州办事处" };
        kmpm.init(input);
        final HashMap<String, String> result = kmpm.paragraph(input);
        
        for (final Entry<String, String> cursor : result.entrySet()) {
            System.out.println(cursor.getKey());
            System.out.println(cursor.getValue());
        }
        */

        final KMPMatching kmpm = new KMPMatching("inputSet/output.txt");
        final String[] input = { "西安办事处", "郑州办事处", "江苏办事处", "山东办事处", "云南办事处", "宁夏办事处", "广州办事处" };
        kmpm.init(input);
        final HashMap<String, String> result = kmpm.paragraph(input);

        for (final Entry<String, String> cursor : result.entrySet()) {
            System.out.println(cursor.getKey());
            System.out.println(cursor.getValue());
        }
    }

}
