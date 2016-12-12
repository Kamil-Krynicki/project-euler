package org.krynicki.euler.Problems51to100;

import java.math.BigInteger;

/**
 * Created by kamil.krynicki on 15/11/2016.
 */
public class Problem66_DiophantineEquation {
    /*
    Consider quadratic Diophantine equations of the form:

    x2 – Dy2 = 1

    For example, when D=13, the minimal solution in x is 6492 – 13×1802 = 1.

    It can be assumed that there are no solutions in positive integers when D is square.

    By finding minimal solutions in x for D = {2, 3, 5, 6, 7}, we obtain the following:

    32 – 2×22 = 1
    22 – 3×12 = 1
    92 – 5×42 = 1
    52 – 6×22 = 1
    82 – 7×32 = 1

    Hence, by considering minimal solutions in x for D ≤ 7, the largest x is obtained when D=5.

    Find the value of D ≤ 1000 in minimal solutions of x for which the largest value of x is obtained.
    */

    static public void main(String... args) {
        final long t1 = System.currentTimeMillis();

        int maxD = -1;
        BigInteger maxDx = BigInteger.ZERO;

        for (int D = 1; D <= 1000; D++) {
            int a0 = (int) Math.sqrt(D);
            if (a0 * a0 == D) continue;

            long ai = a0;

            long d = 1;
            long m = 0;

            BigInteger hi = BigInteger.valueOf(a0);
            BigInteger ki = BigInteger.valueOf(1);
            BigInteger hi1 = BigInteger.valueOf(1);
            BigInteger ki1 = BigInteger.valueOf(0);
            BigInteger hi2;
            BigInteger ki2;

            do {
                m = d * ai - m;
                d = (D - m * m) / d;
                ai = (a0 + m) / d;

                hi2 = hi1;
                ki2 = ki1;

                hi1 = hi;
                ki1 = ki;

                hi = hi1.multiply(BigInteger.valueOf(ai)).add(hi2);
                ki = ki1.multiply(BigInteger.valueOf(ai)).add(ki2);
            } while (!hi.pow(2).subtract(ki.pow(2).multiply(BigInteger.valueOf(D))).equals(BigInteger.ONE));

            if (maxDx.compareTo(hi) < 0) {
                maxDx = hi;
                maxD = D;
            }
        }

        final long t2 = System.currentTimeMillis();

        System.out.println("max D: " + maxD);
        System.out.println("max D x: " + maxDx);

        System.out.println("time:");
        System.out.println(t2 - t1);
    }

}
