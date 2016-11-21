package org.krynicki.euler;

import org.krynicki.euler.util.ContinuedFraction;

import java.util.Arrays;

/**
 * Created by K on 2016-11-21.
 */
public class Problem73_CountingFractionsInRange {
    /*
    Consider the fraction, n/d, where n and d are positive integers. If n<d and HCF(n,d)=1, it is called a reduced proper fraction.

    If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size, we get:

    1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8

    It can be seen that there are 3 fractions between 1/3 and 1/2.

    How many fractions lie between 1/3 and 1/2 in the sorted set of reduced proper fractions for d ≤ 12,000?
    */

    static int maxDen = 12000;

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        System.out.println(countBetween(1, 3, 1, 2));

        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
    }

    static int countBetween(int num1, int den1, int num2, int den2) {
        if (den1 + den2 > maxDen) {
            return 0;
        }

        int newDen = den1 + den2;
        int newNum = num1 + num2;

        int result = 1;

        result += countBetween(num1, den1, newNum, newDen);
        result += countBetween(newNum, newDen, num2, den2);

        return result;
    }
}
