package com.makebono.algorithms.graphalgorithms.kcoloring;

/** 
 * @ClassName: BackTrackingKColoring 
 * @Description: K-Coloring problem by back-tracking
 * @author makebono
 * @date 2018年1月17日 上午8:57:39 
 *  
 */
public class BackTrackingKColoring extends AbstractKColoringPortal {

    @Override
    public void dealWithIt() {
        final int size = this.getGraph().getVertices().size();
        // Record the parents of each vertex.
        final int[][] parentTable = new int[size][size];

    }
}
