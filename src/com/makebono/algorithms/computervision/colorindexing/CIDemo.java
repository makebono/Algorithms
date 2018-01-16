package com.makebono.algorithms.computervision.colorindexing;

import java.io.IOException;

import com.makebono.algorithms.tools.computervision.matrix.ImageMatrix;

/** 
 * @ClassName: CIDemo 
 * @Description: Demo of Swain&Ballard's color-indexing
 * @author tangxj
 * @date 2018年1月16日 下午4:35:57 
 *  
 */
public class CIDemo {
    public static void show() throws IOException {
        final ImageMatrix environment = new ImageMatrix("inputSet/desk2.png");
        final ImageMatrix target = new ImageMatrix("inputSet/charger.png");
        Tracking.backProjection(target, environment);
    }
}
