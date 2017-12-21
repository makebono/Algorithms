package com.makebono.algorithms.test;

import java.io.IOException;

import com.makebono.algorithms.string.patternmatching.forcecomparison.ForceComparison;
import com.makebono.algorithms.string.patternmatching.knuth_morris_pratt.KMPMatching;

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

        final String[] input = { "西安办事处", "郑州办事处", "江苏办事处", "山东办事处", "云南办事处", "宁夏办事处", "广州办事处" };
        kmpm.init(input);
        final HashMap<String, String> result = kmpm.paragraph(input);
        
        for (final String key : result.keySet()) {
            System.out.println(key);
            System.out.println(result.get(key));
        }
        */
        final KMPMatching kmpm = new KMPMatching("inputSet/kmpHomeCourt.txt");
        final ForceComparison fc = new ForceComparison("inputSet/kmpHomeCourt.txt");
        final char[] input = "ababaca".toCharArray();
        kmpm.init("ababaca");
        fc.init("ababaca");
        final int[] pi = KMPMatching.computePrefix("ababaca");

        int result = 0;

        double t = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            result = kmpm.matchByBuildInMethod("ababaca");
        }
        t = System.currentTimeMillis() - t;
        System.out.println("Matching by build-in method takes: " + t / 1000 + "s");

        t = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            result = kmpm.match(input, pi);
        }
        t = System.currentTimeMillis() - t;
        System.out.println("Matching by optimized force matching takes: " + t / 1000 + "s");

        t = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            result = kmpm.match(input, pi);
        }
        t = System.currentTimeMillis() - t;
        System.out.println("Matching by KMP algorithm takes: " + t / 1000 + "s");
    }

}
