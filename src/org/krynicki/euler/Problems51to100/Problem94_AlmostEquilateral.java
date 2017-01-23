package org.krynicki.euler.Problems51to100;

import java.util.Set;

/**
 * Created by kamil.krynicki on 19/01/2017.
 */
public class Problem94_AlmostEquilateral {
    private static Set<Long> squares;
    /*
    It is easily proved that no equilateral triangle exists with integral length sides and integral area. However,
    the almost equilateral triangle 5-5-6 has an area of 12 square units.

    We shall define an almost equilateral triangle to be a triangle for which two sides are equal and the third
    differs by no more than one unit.

    Find the sum of the perimeters of all almost equilateral triangles with integral side lengths and area and whose
    perimeters do not exceed one billion (1,000,000,000).
    */

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        long sum = 0;

        int max = (int) 1E9;

        long a = 6;
        do {
            if((a+1)%4==0 || (a-1)%4==0) {
                if(isSquare(area2(a, a + 1))) sum += 3 * a + 1;
                else if(isSquare(area2(a, a - 1))) sum += 3 * a - 1;
            }
            a++;
        } while ( a <= max/3);

        long t2 = System.currentTimeMillis();

        System.out.println(sum);
        System.out.println(t2 - t1);
    }

    private static long area2(long a, long c) {
        return (a-c/2)*(a+c/2);
    }

    static boolean isSquare(long l) {
        long l2=(long) Math.sqrt(l);
        return l == l2 * l2;
    }
}
