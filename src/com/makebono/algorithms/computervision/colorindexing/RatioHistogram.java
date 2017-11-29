package com.makebono.algorithms.computervision.colorindexing;

import com.makebono.algorithms.tools.computervision.matrix.ImageMatrix;

/** 
 * @ClassName: RatioHistogram 
 * @Description: Generate rational histogram by comparing pixels of target and environment. This is histogram of ratio 
 * on each pixel, representing the probability of target's presence at that pixel. This method called Back-Projection. 
 * According to Swain and Ballard, the formula of ratio histogram:
 * 
 *                                     Ri = min(Mi/Ii,1)
 * Ri is the probability ratio at pixel i, Mi and Ii are values of color histogram of target(Model) and Image(Environment).
 * Take minimum value between it and 1 is because probability cannot be more than 1. Because I don't want to mess with
 * double around its accuracy or BigDecimal around its methods, I use ratio*100 as for here. So it is: 
 *                                     Ri = min(Mi*100/Ii,100) 
 * in this implementation.
 * @author makebono
 * @date 2017年11月27日 下午3:42:02 
 *  
 */
public class RatioHistogram {
    public static int[][] backProject(final int[][][] targetHistogram, final int[][][] environmentHistogram,
            final ImageMatrix environment) {
        final int m = environment.image().getHeight();
        final int n = environment.image().getWidth();
        final int[][][] environmentMatrix = environment.matrix();

        final int[][] rationalHistogram = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int o = 0; o < n; o++) {
                int r = (int) (environmentMatrix[i][o][0] / 31.875) - 1;
                int g = (int) (environmentMatrix[i][o][1] / 31.875) - 1;
                int b = (int) (environmentMatrix[i][o][2] / 31.875) - 1;

                if (r < 0) {
                    r = 0;
                }

                if (g < 0) {
                    g = 0;
                }

                if (b < 0) {
                    b = 0;
                }

                final int numberOfEntriesTarget = targetHistogram[r][g][b];
                final int numberOfEntriesEnvironment = environmentHistogram[r][g][b];

                if (numberOfEntriesTarget >= 5 || numberOfEntriesEnvironment >= 5) {
                    final int newEntry = (numberOfEntriesTarget * 100) / (numberOfEntriesEnvironment);
                    if (newEntry >= 100) {
                        rationalHistogram[i][o] = 100;
                    } else {
                        rationalHistogram[i][o] = newEntry;
                    }
                }
            }
        }

        return rationalHistogram;
    }

    public static int[] backProject1D(final int[] targetHistogram, final int[] environmentHistogram,
            final ImageMatrix environment) {
        final int m = environment.image().getHeight();
        final int n = environment.image().getWidth();

        final int[] rationalHistogram = new int[m * n];

        for (int i = 0; i < m; i++) {
            for (int o = 0; o < n; o++) {
                int r = (int) (environment.get1D(i, o, 0) / 31.875) - 1;
                int g = (int) (environment.get1D(i, o, 1) / 31.875) - 1;
                int b = (int) (environment.get1D(i, o, 2) / 31.875) - 1;

                if (r < 0) {
                    r = 0;
                }

                if (g < 0) {
                    g = 0;
                }

                if (b < 0) {
                    b = 0;
                }

                final int numberOfEntriesTarget = targetHistogram[r * 8 * 8 + g * 8 + b];
                final int numberOfEntriesEnvironment = environmentHistogram[r * 8 * 8 + g * 8 + b];

                if (numberOfEntriesTarget >= 5 || numberOfEntriesEnvironment >= 5) {
                    final int newEntry = (numberOfEntriesTarget * 100) / (numberOfEntriesEnvironment);
                    if (newEntry >= 100) {
                        rationalHistogram[o + i * n] = 100;
                    } else {
                        rationalHistogram[o + i * n] = newEntry;
                    }
                }
            }
        }

        return rationalHistogram;
    }
}
