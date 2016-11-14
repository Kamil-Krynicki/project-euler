package org.krynicki.euler;

/**
 * Created by K on 2016-11-14.
 */
public class Problem64_OddSquareRoots {
    /*
    The first ten continued fraction representations of (irrational) square roots are:

    √2=[1;(2)], period=1
    √3=[1;(1,2)], period=2
    √5=[2;(4)], period=1
    √6=[2;(2,4)], period=2
    √7=[2;(1,1,1,4)], period=4
    √8=[2;(1,4)], period=2
    √10=[3;(6)], period=1
    √11=[3;(3,6)], period=2
    √12= [3;(2,6)], period=2
    √13=[3;(1,1,1,1,6)], period=5

    Exactly four continued fractions, for N ≤ 13, have an odd period.

    How many continued fractions for N ≤ 10000 have an odd period?
    */

    static public void main(String... args) {
        final long t1 = System.currentTimeMillis();
        int len, count = 0;
        int a0;
        int ai;

        int d;
        int m;
        for (int i = 1; i <= 10000; i++) {
            a0 = (int) Math.sqrt(i);

            if (a0 * a0 == i) continue;

            len = 0;

            ai = a0;
            d = 1;
            m = 0;

            do {
                m = d * ai - m;
                d = (i - m * m) / d;
                ai = (a0 + m) / d;
                len++;
            } while (ai != 2 * a0);
            count += len%2;
        }
        final long t2 = System.currentTimeMillis();

        System.out.println(count);
        System.out.println(t2 - t1);

    }
}
