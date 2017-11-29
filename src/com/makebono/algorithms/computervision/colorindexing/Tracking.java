/**Note: 
 * Swain and Ballard's color indexing. Generate color histogram for both target and environment image, and use back projection to compute
 * the probabilities of presense of target at each pixel in the environment. Use a convolutional mask with size of target to sum the 
 * probabilities up in a region. Mark the coordinate of highest region sum as the position of target. Some specific notes in each of the 
 * classes: RatioHistogram, Convolution, Kernel, ColorHistogram.
 *  
 * 
 * Performance issue and solution:
 *     Most time consuming part is convolution. A trivial 2-D convolution is unacceptably slow for even a small size image(for example 
 *     the image I used for testing is 960X720, it takes ~70 secs for convolution). So a optimization is mandantory. 
 *     The optimized convolution method is separable convolution. Which is frankly to convolve horizontally and vertically, separately.
 *     This needs a separable kernel. Which is all "1"s in both vertical and horizontal in this tracking case.
 *     After optimization, the time takes for the convolution dropped from 70secs to 1sec. Boo! 
 *     
 * Instruction:
 *     final ImageMatrix desk = new ImageMatrix("desk.jpg");
 *     final ImageMatrix bottle = new ImageMatrix("bottle.png");
 *     Tracking.backProjection(bottle, desk);
 */
package com.makebono.algorithms.computervision.colorindexing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
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
public class Tracking {
    public static void backProjection(final ImageMatrix target, final ImageMatrix environment) throws IOException {

        double t = System.currentTimeMillis();
        // final int[][][] targetHistogram = ColorHistogram.generate(target);
        // final int[][][] environmentHistogram = ColorHistogram.generate(environment);

        final int[] tHistogram1D = ColorHistogram.generate1D(target);
        final int[] eHistogram1D = ColorHistogram.generate1D(environment);

        t = System.currentTimeMillis() - t;
        System.out.println("Times cost for generating color histogram is: " + t / 1000);

        t = System.currentTimeMillis();
        // final int[][] rationalHistogram = RatioHistogram.backProject(targetHistogram, environmentHistogram,
        // environment);

        final int[] rHistogram1D = RatioHistogram.backProject1D(tHistogram1D, eHistogram1D, environment);

        t = System.currentTimeMillis() - t;
        System.out.println("Times cost for generating rational histogram is: " + t / 1000);

        // final int[][] kernel = Kernel.generate(target);
        final int[] kernelH = Kernel.separateH(target);
        final int[] kernelV = Kernel.separateV(target);

        t = System.currentTimeMillis();
        // final int[][] histogramConvolution = Convolution.conv2trivial(rationalHistogram, kernel, true);
        // final int[][] histogramConvolution = Convolution.conv2f(rationalHistogram, kernelV, kernelH, true);
        final int[] histogram1DConvolution = Convolution.conv1f(rHistogram1D, kernelV, kernelH,
                environment.image().getHeight(), environment.image().getWidth());

        t = System.currentTimeMillis() - t;
        System.out.println("Times cost for convolution calculation is: " + t / 1000);

        t = System.currentTimeMillis();
        // final int[] peak = Maximum.find(histogramConvolution);
        final int[] peak = Maximum.find1D(histogram1DConvolution, environment.image().getHeight(),
                environment.image().getWidth());
        final int maxM = peak[0];
        final int maxN = peak[1];
        t = System.currentTimeMillis() - t;
        System.out.println("Times cost for finding maximum is: " + t / 1000);

        t = System.currentTimeMillis();
        final Graphics2D g2d = Display.imshow(environment.image(), "Result").createGraphics();

        // Draw a circle at maxM,maxN
        g2d.setColor(Color.RED);
        final RoundRectangle2D r2d = new RoundRectangle2D.Double(maxN, maxM, 20, 20, 20, 20);
        g2d.setStroke(new BasicStroke(5f));
        g2d.draw(r2d);
        t = System.currentTimeMillis() - t;
        System.out.println("Times cost for drawing on image is: " + t / 1000);

        Display.imshow(target.image(), "Target for track");
    }
}
