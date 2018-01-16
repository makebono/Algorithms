package com.makebono.algorithms.pathfinder.astaralgorithm;

import java.io.FileNotFoundException;

/** 
 * @ClassName: ASPFDemo 
 * @Description: Demo of A* algorithm
 * @author makebono
 * @date 2018年1月16日 下午4:49:24 
 *  
 */
public class ASPFDemo {
    public static void show() throws FileNotFoundException {
        final AStarPathFinder ASPF = new AStarPathFinder("inputSet/astarinput.txt");
        ASPF.findPath(97, 791);
    }
}
