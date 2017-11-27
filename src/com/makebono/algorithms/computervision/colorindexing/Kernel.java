package com.makebono.algorithms.computervision.colorindexing;

import java.util.Arrays;

import com.makebono.algorithms.tools.computervision.matrix.ImageMatrix;

/** 
 * @ClassName: Kernel 
 * @Description: A simple kernel with all 1 assigned.
 * @author makebono
 * @date 2017年11月27日 下午4:01:06 
 *  
 */
public class Kernel {
    public static int[][] generate(final ImageMatrix target) {
        final int m = target.image().getHeight();
        final int n = target.image().getWidth();
        final int[][] kernel = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(kernel[i], 1);
        }

        return kernel;
    }
}
