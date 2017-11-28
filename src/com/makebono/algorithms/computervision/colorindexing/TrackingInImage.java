/**Note:
 * 
 * 
 * Performance analyze:
 *     Most time consuming part is convolution. A trivial 2-D convolution is unacceptably slow for even a small size image(for example 
 *     the image I used for testing is 960X720, it takes ~70 secs for convolution). So a optimization is mandantory. 
 *     The optimized convolution method is to convolve horizontally and vertically, separately.
 *     After optimization, the time takes for the convolution dropped from 70secs to 1sec. Boo! 
 */
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

        double t = System.currentTimeMillis();
        final int[][][] targetHistogram = ColorHistogram.generate(targetMatrix);
        final int[][][] environmentHistogram = ColorHistogram.generate(environmentMatrix);
        t = System.currentTimeMillis() - t;
        System.out.println("Times cost for generating color histogram is: " + t / 1000);

        t = System.currentTimeMillis();
        final int[][] rationalHistogram = RationalHistogram.generate(targetHistogram, environmentHistogram,
                environmentMatrix.length, environmentMatrix[0].length, environmentMatrix);
        t = System.currentTimeMillis() - t;
        System.out.println("Times cost for generating rational histogram is: " + t / 1000);

        final int[][] kernel = Kernel.generate(target);

        t = System.currentTimeMillis();
        // final int[][] histogramConvolution = Convolution.conv2trivial(rationalHistogram, kernel);
        final int[][] histogramConvolution = Convolution.conv2f(rationalHistogram, kernel);
        t = System.currentTimeMillis() - t;
        System.out.println("Times cost for convolution calculation is: " + t / 1000);

        t = System.currentTimeMillis();
        final int[] peak = Maximum.find(histogramConvolution);
        final int maxM = peak[0];
        final int maxN = peak[1];
        t = System.currentTimeMillis() - t;
        System.out.println("Times cost for finding maximum is: " + t / 1000);

        t = System.currentTimeMillis();
        for (int i = maxM - 10; i < maxM + 10; i++) {
            for (int o = maxN - 10; o < maxN + 10; o++) {
                environment.set(i, o, 0, 255);
                environment.set(i, o, 1, 0);
                environment.set(i, o, 2, 0);
            }
        }

        t = System.currentTimeMillis() - t;
        System.out.println("Times cost for rewriting matrix data is: " + t / 1000);

        t = System.currentTimeMillis();
        Display.imshow(environment.rebuildImage(), "bjj");
        t = System.currentTimeMillis() - t;
        System.out.println("Times cost for rebuilding image is: " + t / 1000);
    }
}
