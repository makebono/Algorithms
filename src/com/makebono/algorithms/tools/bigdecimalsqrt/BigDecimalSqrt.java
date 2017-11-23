/**Newton's method.
 * sqrt(a) is a root of f(x) = x^2 - a = 0. Notice f(x)' is 2x.
 * Set x0 anything positive you like, then we have:
 * 
 *        xn = xn-1 + f(x-1)/f(x-1)' = xn-1 + ((xn-1)^2 - a)/2xn-1 = (xn-1 + a/xn-1)/2 
 * 
 * Iterate this to get xn as sqrt(a).
 */
package com.makebono.algorithms.tools.bigdecimalsqrt;

import java.math.BigDecimal;

/** 
 * @ClassName: BigDecimalSqrt 
 * @Description: Square root for BigDecimal, using Newton's method.
 * @author makebono
 * @date 2017年11月23日 下午12:01:18 
 *  
 */
public class BigDecimalSqrt {
    public static BigDecimal sqrt(final BigDecimal input, final int precision) {
        BigDecimal x = BigDecimal.valueOf(206666766);
        final BigDecimal two = BigDecimal.valueOf(2);

        for (int i = 0; i < 100; i++) {
            x = (x.add((input.divide(x, precision, BigDecimal.ROUND_HALF_UP)))).divide(two, precision,
                    BigDecimal.ROUND_HALF_UP);
        }

        return x;
    }

}
