package com.makebono.algorithms.tools.computervision.matrix;

import java.math.BigDecimal;

/** 
 * @ClassName: Maximum 
 * @Description: Maximum value in a matrix
 * @author makebono
 * @date 2017年11月27日 下午4:07:52 
 *  
 */
public class Maximum {
    public static int[] find(final int[][] matrix) {
        int maximum = matrix[0][0];
        final int[] mn = new int[2];

        for (int i = 0; i < matrix.length; i++) {
            for (int o = 0; o < matrix[0].length; o++) {
                if (maximum <= matrix[i][o]) {
                    maximum = matrix[i][o];
                }
            }
        }

        int count = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int o = 0; o < matrix[0].length; o++) {
                if (maximum == matrix[i][o]) {
                    count++;
                    mn[0] += i;
                    mn[1] += o;
                }
            }
        }

        mn[0] /= count;
        mn[1] /= count;

        System.out.println("number of peeaks: " + count);

        return mn;
    }

    public static int[] find(final BigDecimal[][] matrix) {
        BigDecimal maximum = matrix[0][0];
        final int[] mn = new int[2];

        for (int i = 0; i < matrix.length; i++) {
            for (int o = 0; o < matrix[0].length; o++) {
                if (maximum.compareTo(matrix[i][o]) <= 0) {
                    maximum = matrix[i][o];

                }
            }
        }

        int count = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int o = 0; o < matrix[0].length; o++) {
                if (maximum.equals(matrix[i][o])) {
                    count++;
                    mn[0] += i;
                    mn[1] += o;
                }
            }
        }

        mn[0] /= count;
        mn[1] /= count;

        System.out.println("number of peeaks: " + count);

        return mn;
    }
}
