package org.krynicki.euler;

import java.util.Arrays;

/**
 * Created by K on 2016-11-22.
 */
public class Problem75_SingularIntegerRightTriangles {
    /*
    It turns out that 12 cm is the smallest length of wire that can be bent to form an integer sided right angle triangle in exactly one way, but there are many more examples.

    12 cm: (3,4,5)
    24 cm: (6,8,10)
    30 cm: (5,12,13)
    36 cm: (9,12,15)
    40 cm: (8,15,17)
    48 cm: (12,16,20)

    In contrast, some lengths of wire, like 20 cm, cannot be bent to form an integer sided right angle triangle, and other lengths allow more than one solution to be found; for example, using 120 cm it is possible to form exactly three different integer sided right angle triangles.

    120 cm: (30,40,50), (20,48,52), (24,45,51)

    Given that L is the length of the wire, for how many values of L ≤ 1,500,000 can exactly one integer sided right angle triangle be formed?
    */

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        long sum = 0;
        int L = (int) 1500000;

        int[] decompositions = new int[L + 1];


        for (int n = 1; n < Math.sqrt(L); n++)
            for (int m = n + 1; m < Math.sqrt(L); m++) {
                if (gdc(m, n) > 1) continue;
                if (m % 2 == 1 && n % 2 == 1) continue;
                int a = m * m - n * n;
                int b = 2 * m * n;
                int c = m * m + n * n;
                int tmpL = 0;
                while ((tmpL += (a + b + c)) <= L) {
                    decompositions[tmpL]++;
                }
            }

        long t2 = System.currentTimeMillis();

        for (int i : decompositions) {
            if (i == 1) {
                sum++;
            }
        }

        //System.out.println(Arrays.toString(decompositions));
        System.out.println(sum);
        System.out.println(t2 - t1);
    }

    private static int gdc(int a, int b) {
        if (b == 0) return a;
        return gdc(b, a % b);
    }

}
