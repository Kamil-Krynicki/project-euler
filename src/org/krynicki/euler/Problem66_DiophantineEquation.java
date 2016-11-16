package org.krynicki.euler;

import java.util.HashSet;
import java.util.Set;

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

        Set<Long> result = new HashSet<>();

        int maxD = -1;
        long maxDx = -1;

        for (int D = 1; D <= 1000; D++) {
            System.out.println("====================================");
            System.out.println("D=" + D);

            long a0 = (int) Math.sqrt(D);
            if (a0 * a0 == D) continue;

            long ai = a0;

            long d = 1;
            long m = 0;

            long hi = a0, ki = 1, hi1 = 1, ki1 = 0, hi2, ki2;

            do {
                m = d * ai - m;
                d = (D - m * m) / d;
                ai = (a0 + m) / d;

                hi2 = hi1;
                ki2 = ki1;

                hi1 = hi;
                ki1 = ki;

                hi = ai * hi1 + hi2;
                ki = ai * ki1 + ki2;
            } while (hi * hi - D * ki * ki != 1);

            if( maxDx < hi) {
                maxDx = hi;
                maxD = D;
            }


            System.out.println("y=" + ki);
            System.out.println("x=" + hi);

        }


        final long t2 = System.currentTimeMillis();

        System.out.println("max D: " + maxD);
        System.out.println("max D x: " + maxDx);

        System.out.println("time:");
        System.out.println(t2 - t1);
    }

}
