package com.makebono.algorithms.pathfinder.astaralgorithm.tools;

import java.util.Comparator;

import com.makebono.algorithms.pathfinder.astaralgorithm.AStarNode;

/** 
 * @ClassName: GComparator 
 * @Description: Comparator that compares, required by priority queue, helps sorting the queue by increasing order on g score.
 * @author makebono
 * @date 2017年11月1日 下午5:45:21 
 *  
 */
public class GComparator implements Comparator<AStarNode> {

    public int compare(final AStarNode n1, final AStarNode n2) {
        if (n1.getGScore() > n2.getGScore()) {
            return 1;
        }
        if (n1.getGScore() < n2.getGScore()) {
            return -1;
        }
        return 0;
    }
}
