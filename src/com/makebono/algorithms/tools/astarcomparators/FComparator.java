package com.makebono.algorithms.tools.astarcomparators;

import java.util.Comparator;

import com.makebono.algorithms.pathfinder.astaralgorithm.AStarNode;

/** 
 * @ClassName: FComparator 
 * @Description: Comparator that compares, required by priority queue, helps sorting the queue by increasing order on f score.
 * @author makebono
 * @date 2017年11月1日 下午5:45:21 
 *  
 */
public class FComparator implements Comparator<AStarNode> {

    @Override
    public int compare(final AStarNode n1, final AStarNode n2) {
        if (n1.getFScore() > n2.getFScore()) {
            return 1;
        }
        if (n1.getFScore() < n2.getFScore()) {
            return -1;
        }
        return 0;
    }
}
