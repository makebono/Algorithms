package com.makebono.algorithms.hungarianalgorithm;

/** 
 * @ClassName: HWDDemo 
 * @Description: Demo of Hungarian algorithm work distributor
 * @author makebono
 * @date 2018年1月16日 下午4:44:24 
 *  
 */
public class HWDDemo {
    public static void show(final int candidates) {
        final HungarianWorkDistributor hwd = new HungarianWorkDistributor(candidates);
        hwd.solve();
    }
}
