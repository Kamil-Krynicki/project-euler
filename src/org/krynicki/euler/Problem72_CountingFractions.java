package org.krynicki.euler;

/**
 * Created by kamil.krynicki on 18/11/2016.
 */
public class Problem72_CountingFractions {
    /*
    Consider the fraction, n/d, where n and d are positive integers. If n<d and HCF(n,d)=1, it is called a reduced proper fraction.

    If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size, we get:

    1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8

    It can be seen that there are 21 elements in this set.

    How many elements would be contained in the set of reduced proper fractions for d ≤ 1,000,000?
    */

    static int[] memo;

    public static void main(String[] args) {
        int max = 1000000;
        memo = new int[max +1];

        long t1 = System.currentTimeMillis();

        long phiSum = 0;
        for (int i = 2; i <= max; i++) {
            phiSum += phi(i);
        }

        long t2 = System.currentTimeMillis();

        System.out.println("PhiSum " + phiSum);
        System.out.println(t2 - t1);
    }

    private static int phi(int num) {
        int tmp;
        for (int div = 2, tDiv; div*div <= num; div++) {
            if (num % div != 0) {
                continue;
            }

            tmp = num;
            tDiv = div;

            do {
                tDiv *= div;
                tmp /= div;
            } while (tmp % div == 0);

            tDiv /= div;

            if (tmp != 1) {
                memo[num] = memo[tDiv] * memo[tmp];
            } else {
                memo[num] = tDiv / div * memo[div];
            }

            return memo[num];
        }
        return memo[num] = num - 1;
    }

}
