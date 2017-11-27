package com.makebono.algorithms.tools.gcd;

/** 
 * @ClassName: GreatestCommonDivisor 
 * @Description: Greatest common divisor
 * @author makebono
 * @date 2017年11月27日 上午10:01:15 
 *  
 */
public class GreatestCommonDivisor {
    public static long gcdIterative(final long a, final long b) {
        long n1;
        long n2;

        if (a >= b) {
            n1 = a;
            n2 = b;
        } else {
            n1 = b;
            n2 = a;
        }

        long divisor = n1 % n2;

        while (divisor != 0) {
            n1 = n2;
            n2 = divisor;
            divisor = n1 % n2;
        }

        return n2;
    }

    public static long gcdRecursive(final long a, final long b) {
        long n1;
        long n2;

        if (a >= b) {
            n1 = a;
            n2 = b;
        } else {
            n1 = b;
            n2 = a;
        }

        if (n2 == 0) {
            return n1;
        }

        return gcdRecursive(n2, n1 % n2);
    }
}
