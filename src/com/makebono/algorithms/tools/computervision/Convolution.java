package com.makebono.algorithms.tools.computervision;

/** 
 * @ClassName: Convolution 
 * @Description: 2d Convolution. For lazy implementation to help color indexing problem, 
 * it only considers kernal with all 1s as input now. Will soon rewrite it so it would fit generic cases. 
 * @author makebono
 * @date 2017年11月27日 下午2:29:49 
 *  
 */
public class Convolution {
    public static int[][] conv2trivial(final int[][] origin, final int[][] kernel) {
        final int[][] result = new int[origin.length][origin[0].length];

        final int kernelY0 = kernel.length / 2;
        final int kernelX0 = kernel[0].length / 2;
        int count = 0;

        for (int i = 0; i < origin.length; i++) {
            for (int o = 0; o < origin[0].length; o++) {
                for (int m = 0; m < kernel.length; m++) {
                    for (int n = 0; n < kernel[0].length; n++) {
                        final int iBoundary = i + m - kernelY0;
                        final int oBoundary = o + n - kernelX0;

                        if (iBoundary >= 0 && iBoundary < origin.length && oBoundary >= 0
                                && oBoundary < origin[0].length) {
                            result[i][o] += origin[iBoundary][oBoundary];
                            count++;
                        }
                    }
                }
            }
        }
        System.out.println("Trivial convolution computes " + count + " times.");
        return result;
    }

    public static int[][] conv2f(final int[][] origin, final int[][] kernel) {
        final int[][] result = new int[origin.length][origin[0].length];

        final int kernelY0 = kernel.length / 2;
        final int kernelX0 = kernel[0].length / 2;

        int count = 0;

        for (int i = 0; i < origin.length; i++) {
            for (int o = 0; o < origin[0].length; o++) {
                for (int n = 0; n < kernel[0].length; n++) {
                    final int oBoundary = o + n - kernelX0;
                    if (oBoundary >= 0 && oBoundary < origin[0].length) {
                        result[i][o] += origin[i][oBoundary];
                        count++;
                    }
                }
            }
        }

        final int[][] temp = new int[origin.length][origin[0].length];

        for (int i = 0; i < origin.length; i++) {
            for (int o = 0; o < origin[0].length; o++) {
                temp[i][o] = result[i][o];
                count++;
            }
        }

        for (int o = 0; o < origin[0].length; o++) {
            for (int i = 0; i < origin.length; i++) {
                for (int m = 0; m < kernel.length; m++) {
                    final int iBoundary = i + m - kernelY0;
                    if (iBoundary >= 0 && iBoundary < origin.length && iBoundary != i) {
                        result[i][o] += temp[iBoundary][o];
                        count++;
                    }
                }
            }
        }
        System.out.println("Optimized convolution computes " + count + " times.");
        return result;
    }
}
