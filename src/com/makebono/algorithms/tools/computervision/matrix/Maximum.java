package com.makebono.algorithms.tools.computervision.matrix;

/** 
 * @ClassName: Maximum 
 * @Description: Maximum value in a matrix, call it peak. After convolution of rational histogram, (a) peak(s) is acutually
 * the position shows high(est) potential to have the target. And sometimes several peaks will be found, and they are actually
 * all on the target, in such situation, just average coordinates of peaks. Or it could also be simply done by return the 
 * first/last peak found. 
 * @author makebono
 * @date 2017年11月27日 下午4:07:52 
 *  
 */
public class Maximum {
    public static int[] find(final int[][] matrix) {
        int maximum = matrix[0][0];
        final int[] mn = new int[2];

        // Find a maximum value first
        for (int i = 0; i < matrix.length; i++) {
            for (int o = 0; o < matrix[0].length; o++) {
                if (maximum <= matrix[i][o]) {
                    maximum = matrix[i][o];
                }
            }
        }

        int count = 0;

        // As consideration of errors. I also count probability (maximum plus/minus 3) as maximum. So sometimes there
        // would be multiple maximum, simply average their coordinates for better accuracy.
        for (int i = 0; i < matrix.length; i++) {
            for (int o = 0; o < matrix[0].length; o++) {
                if (maximum - matrix[i][o] <= 3) {
                    count++;
                    mn[0] += i;
                    mn[1] += o;
                }
            }
        }

        // mn[0] = m, mn[1] = n
        mn[0] /= count;
        mn[1] /= count;

        System.out.println("Number of peak(s) found: " + count);

        return mn;
    }

    public static int[] find1D(final int[] matrix, final int m, final int n) {
        int maximum = matrix[0];
        final int[] mn = new int[2];

        // Find a maximum value first
        for (int i = 0; i < m; i++) {
            for (int o = 0; o < n; o++) {
                if (maximum <= matrix[i * n + o]) {
                    maximum = matrix[i * n + o];
                }
            }
        }

        int count = 0;

        // As consideration of errors. I also count probability (maximum plus/minus 3) as maximum. So sometimes there
        // would be multiple maximum, simply average their coordinates for better accuracy.
        for (int i = 0; i < m; i++) {
            for (int o = 0; o < n; o++) {
                if (maximum - matrix[i * n + o] <= 3) {
                    count++;
                    mn[0] += i;
                    mn[1] += o;
                }
            }
        }

        // mn[0] = m, mn[1] = n
        mn[0] /= count;
        mn[1] /= count;

        System.out.println("Number of peak(s) found: " + count);

        return mn;

    }
}
