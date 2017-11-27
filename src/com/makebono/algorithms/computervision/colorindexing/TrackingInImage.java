package com.makebono.algorithms.computervision.colorindexing;

import java.io.IOException;

import com.makebono.algorithms.tools.computervision.Convolution;
import com.makebono.algorithms.tools.computervision.Display;
import com.makebono.algorithms.tools.computervision.matrix.ImageMatrix;
import com.makebono.algorithms.tools.computervision.matrix.Maximum;

/** 
 * @ClassName: TrackingInImage
 * @Description: Target tracking in static image. Using Swain&Ballard's color indexing.  
 * @author makebono
 * @date 2017年11月27日 上午10:37:02 
 *  
 */
public class TrackingInImage {
    public static void backProjection(final ImageMatrix target, final ImageMatrix environment) throws IOException {
        final int[][][] targetMatrix = target.matrix();
        final int[][][] environmentMatrix = environment.matrix();

        final int[][][] targetHistogram = ColorHistogram.generate(targetMatrix);
        final int[][][] environmentHistogram = ColorHistogram.generate(environmentMatrix);

        final int[][] rationalHistogram = RationalHistogram.generate(targetHistogram, environmentHistogram,
                environmentMatrix.length, environmentMatrix[0].length, environmentMatrix);

        final int[][] kernel = Kernel.generate(target);

        final int[][] histogramConvolution = Convolution.conv2(rationalHistogram, kernel);

        final int[] peak = Maximum.find(histogramConvolution);
        final int maxM = peak[0];
        final int maxN = peak[1];

        for (int i = maxM - 10; i < maxM + 10; i++) {
            for (int o = maxN - 10; o < maxN + 10; o++) {
                environment.set(i, o, 0, 255);
                environment.set(i, o, 1, 0);
                environment.set(i, o, 2, 0);
            }
        }

        Display.imshow(environment.rebuildImage(), "bjj");
    }
}
