package org.krynicki.euler;

import java.util.Arrays;

/**
 * Created by K on 2016-10-24.
 */
public class Problem39_IntegerRightTriangles {
    /*
    If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120.

    {20,48,52}, {24,45,51}, {30,40,50}

    For which value of p ≤ 1000, is the number of solutions maximised?
    */

    // observation - this problem reduces to generation of pythagorean triples

    public static void main(String[] args) {
        int[] sums = new int[1001];
        int p = 1000;

        long t1 = System.currentTimeMillis();

        for (int a = 1; a < p / (2+Math.sqrt(2)); a++) {
            for (int b = 1; a + b < p; b++) {
                double c = Math.sqrt(a * a + b * b);

                double perimeter = a + b + c;
                if (perimeter > p) {
                    a++;
                    b = 1;
                } else {
                    if (Math.abs(Math.round(c) - c) < 0.00001) {
                        sums[(int) Math.round(perimeter)]++;
                    }
                }

            }
        }

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

        System.out.println(Arrays.toString(sums));

        int maxIndex = -1;
        int max = -1;

        for (int i = 0; i < 1001; i++) {
            if (sums[i] > max) {
                max = sums[i];
                maxIndex = i;
            }
        }

        System.out.println("Max value " + max + " at " + maxIndex);


    }

}
