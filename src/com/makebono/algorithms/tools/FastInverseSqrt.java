/**Note:
 *    Say '1/sqrt(y)' is is a root of f(y) = (1/(y^2)) - x, f'(y) = -2/(y)^3
 *    y(n+1) = y(n) - (1/(y(n)^2) - x)/(-2/(y)^3)
 *           .
 *           .
 *           .
 *           .
 *           
 *           = y * (1.5 - (xhalf*y*y)
 *    So need to find a magic number y for this.
 *    May seems like wtf at first but it is a Newton's method.
 *    Thanks to the all mighty 0x5f3759df, we have this fast inverse square root algorithm.
 */
package com.makebono.algorithms.tools;

/** 
 * @ClassName: FastInverseSqrt 
 * @Description: Everyone loves id
 * @author  0x5f3759df
 * @date 2017年11月29日 上午11:56:37 
 *  
 */
public class FastInverseSqrt {
    public static float inverseSqrt(final float number) {
        int i;
        // x2 is actually xhalf.
        float x2, y;
        final float threehalfs = 1.5F;

        x2 = number * 0.5F;
        y = number;
        i = Float.floatToIntBits(y); // evil floating point bit level hacking
        i = 0x5f3759df - (i >> 1); // what the fuck?
        y = Float.intBitsToFloat(i);
        y = y * (threehalfs - (x2 * y * y)); // 1st iteration
        // y = y * (threehalfs - (x2 * y * y)); // 2nd iteration, this can be removed

        return y; // y = 1/sqrt(number)
        // return 1/y;
    }
}
