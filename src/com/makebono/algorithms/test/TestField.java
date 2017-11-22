package com.makebono.algorithms.test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.makebono.algorithms.pathfinder.astaralgorithm.AStarNode;
import com.makebono.algorithms.pathfinder.astaralgorithm.AStarPathFinder;

/** 
 * @ClassName: TestField 
 * @Description: Test class
 * @author makebono
 * @date 2017年11月16日 上午9:50:03 
 *  
 */
public class TestField {
    public static void main(final String[] args) throws FileNotFoundException {
        final AStarPathFinder ASPF = new AStarPathFinder("input.txt");
        final ArrayList<AStarNode> list = ASPF.findPath(97, 791);
        /*
        final int[][] w = new int[4][4];
        final int[] w1 = { 0, 3, 0, 5 };
        final int[] w2 = { 7, 1, 0, 6 };
        final int[] w3 = { 3, 2, 1, 0 };
        final int[] w4 = { 1, 1, 0, 1 };
        
        w[0] = w1;
        w[1] = w2;
        w[2] = w3;
        w[3] = w4;
        
        for (int i = 0; i < w.length; i++) {
            for (int o = 0; o < w[0].length; o++) {
                System.out.print(w[i][o] + " ");
            }
            System.out.println();
        }
        System.out.println();
        final int[][] draw = HungarianWorkDistributor.draw(w);
        for (int i = 0; i < draw.length; i++) {
            for (int o = 0; o < draw[0].length; o++) {
                System.out.print(draw[i][o] + " ");
            }
            System.out.println();
        }
        System.out.println();
        HungarianWorkDistributor.updateTempTable(w, draw);
        
        for (int i = 0; i < w.length; i++) {
            for (int o = 0; o < w[0].length; o++) {
                System.out.print(w[i][o] + " ");
            }
            System.out.println();
        }
        */

    }
}
