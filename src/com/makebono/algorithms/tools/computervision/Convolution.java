package com.makebono.algorithms.tools.computervision;

/** 
 * @ClassName: Convolution 
 * @Description: 2d Convolution.
 * @author makebono
 * @date 2017年11月27日 下午2:29:49 
 *  
 */
public class Convolution {
    public static int[][] conv2(final int[][] origin, final int[][] kernel) {
        final int[][] result = new int[origin.length][origin[0].length];
        final int[][] flipped = new int[kernel.length][kernel[0].length];

        final int kernelY0 = kernel.length / 2;
        final int kernelX0 = kernel[0].length / 2;

        for (int i = 0; i < kernel.length; i++) {
            for (int o = 0; o < kernel[0].length; o++) {
                flipped[kernel.length - 1 - i][kernel[0].length - 1 - o] = kernel[i][o];
            }
        }

        for (int i = 0; i < origin.length; i++) {
            for (int o = 0; o < origin[0].length; o++) {
                for (int m = 0; m < flipped.length; m++) {
                    for (int n = 0; n < flipped[0].length; n++) {
                        final int iBoundary = i + m - kernelY0;
                        final int oBoundary = o + n - kernelX0;

                        if (iBoundary >= 0 && iBoundary < origin.length && oBoundary >= 0
                                && oBoundary < origin[0].length) {
                            result[i][o] += origin[iBoundary][oBoundary] * flipped[m][n];

                        }
                    }
                }
            }
        }

        return result;
    }
}
