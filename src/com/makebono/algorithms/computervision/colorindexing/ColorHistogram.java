package com.makebono.algorithms.computervision.colorindexing;

import com.makebono.algorithms.tools.computervision.matrix.ImageMatrix;

/** 
 * @ClassName: ColorHistogram 
 * @Description: Generate color histogram, for performance, the size of histogram is 8x8x8 for RGB channels.
 * @author makebono
 * @date 2017年11月27日 下午3:14:26 
 *  
 */
public class ColorHistogram {
    public static int[][][] generate(final ImageMatrix input) {
        final int[][][] histogram = new int[8][8][8];

        for (int i = 0; i < input.matrix().length; i++) {
            for (int o = 0; o < input.matrix()[0].length; o++) {
                // Ignore a certain pixel with value(brightness) in all 3 channel less than 5. In practical case, there
                // would not likely to be "something black that black". If there is, consider it as noise.
                if (input.matrix()[i][o][0] >= 5 || input.matrix()[i][o][1] >= 5 || input.matrix()[i][o][2] >= 5) {
                    // According to Swain and Ballard's method, 8X8X8 channels are capable to guarantee accuracy enough
                    // high.
                    int r = (int) (input.matrix()[i][o][0] / 31.875) - 1;
                    int g = (int) (input.matrix()[i][o][1] / 31.875) - 1;
                    int b = (int) (input.matrix()[i][o][2] / 31.875) - 1;

                    if (r < 0) {
                        r = 0;
                    }

                    if (g < 0) {
                        g = 0;
                    }

                    if (b < 0) {
                        b = 0;
                    }

                    histogram[r][g][b] += 1;
                }
            }
        }

        return histogram;
    }

    // Try to change everything into 1d array.
    public static int[] generate1D(final ImageMatrix input) {
        final int[] histogram = new int[512];

        for (int i = 0; i < input.image().getHeight(); i++) {
            for (int o = 0; o < input.image().getWidth(); o++) {

                if (input.get1D(i, o, 0) >= 5 || input.get1D(i, o, 1) >= 5 || input.get1D(i, o, 2) >= 5) {
                    int r = (int) (input.get1D(i, o, 0) / 31.875) - 1;
                    int g = (int) (input.get1D(i, o, 1) / 31.875) - 1;
                    int b = (int) (input.get1D(i, o, 2) / 31.875) - 1;

                    if (r < 0) {
                        r = 0;
                    }

                    if (g < 0) {
                        g = 0;
                    }

                    if (b < 0) {
                        b = 0;
                    }

                    histogram[r * 8 * 8 + g * 8 + b] += 1;
                }
            }
        }

        return histogram;
    }
}
