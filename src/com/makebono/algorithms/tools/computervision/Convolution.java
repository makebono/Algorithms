package com.makebono.algorithms.tools.computervision;

/** 
 * @ClassName: Convolution 
 * @Description: 2d Convolution. A trivial implementation of conv2t and a separable convolution of conv2f includes.
 * @author makebono
 * @date 2017年11月27日 下午2:29:49 
 *  
 */
public class Convolution {
    // 2D covolution trivial implementation. Add a normalKernel conditional flag, normal kernel means a m-by-n kernel
    // has m-by-n "1" as value. If this triggered, only summation will be done in the convolution, this will slightly
    // boosts the performance(in bottle2 with desk case, cost drop from ~1.7s to ~1.5s).
    public static int[][] conv2t(final int[][] origin, final int[][] kernel, final boolean normalKernel) {
        final int[][] result = new int[origin.length][origin[0].length];

        final int kernelY0 = kernel.length / 2;
        final int kernelX0 = kernel[0].length / 2;
        long count = 0;

        if (!normalKernel) {
            for (int i = 0; i < origin.length; i++) {
                for (int o = 0; o < origin[0].length; o++) {
                    for (int m = 0; m < kernel.length; m++) {
                        for (int n = 0; n < kernel[0].length; n++) {
                            final int iBoundary = i + m - kernelY0;
                            final int oBoundary = o + n - kernelX0;
                            count += 2;

                            if (iBoundary >= 0 && iBoundary < origin.length && oBoundary >= 0
                                    && oBoundary < origin[0].length) {
                                result[i][o] += origin[iBoundary][oBoundary]
                                        * kernel[kernel.length - m - 1][kernel[0].length - n - 1];
                                count++;
                            }
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < origin.length; i++) {
                for (int o = 0; o < origin[0].length; o++) {
                    for (int m = 0; m < kernel.length; m++) {
                        for (int n = 0; n < kernel[0].length; n++) {
                            final int iBoundary = i + m - kernelY0;
                            final int oBoundary = o + n - kernelX0;
                            count += 2;

                            if (iBoundary >= 0 && iBoundary < origin.length && oBoundary >= 0
                                    && oBoundary < origin[0].length) {
                                result[i][o] += origin[iBoundary][oBoundary];
                                count++;
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Trivial convolution computes " + count + " times.");
        return result;
    }

    // Fast convolution using separable kernel. Normal kernel conditional flag also added here.
    public static int[][] conv2f(final int[][] origin, final int[] v, final int[] h, final boolean normalKernel) {
        final int[][] result = new int[origin.length][origin[0].length];

        final int kernelY0 = v.length / 2;
        final int kernelX0 = h.length / 2;

        long count = 0;

        if (!normalKernel) {
            for (int i = 0; i < origin.length; i++) {
                for (int o = 0; o < origin[0].length; o++) {
                    for (int n = 0; n < h.length; n++) {
                        final int oBoundary = o + n - kernelX0;
                        count++;
                        if (oBoundary >= 0 && oBoundary < origin[0].length) {
                            result[i][o] += origin[i][oBoundary] * h[n];
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
                    for (int m = 0; m < v.length; m++) {
                        final int iBoundary = i + m - kernelY0;
                        count++;

                        // Don't add value of current pixel redundantly.
                        if (iBoundary >= 0 && iBoundary < origin.length && iBoundary != i) {
                            result[i][o] += temp[iBoundary][o] * v[m];
                            count++;
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < origin.length; i++) {
                for (int o = 0; o < origin[0].length; o++) {
                    for (int n = 0; n < h.length; n++) {
                        final int oBoundary = o + n - kernelX0;
                        count++;
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
                    for (int m = 0; m < v.length; m++) {
                        final int iBoundary = i + m - kernelY0;
                        count++;

                        // Don't add value of current pixel redundantly.
                        if (iBoundary >= 0 && iBoundary < origin.length && iBoundary != i) {
                            result[i][o] += temp[iBoundary][o];
                            count++;
                        }
                    }
                }
            }
        }

        System.out.println("Optimized convolution computes " + count + " times.");
        return result;
    }
}