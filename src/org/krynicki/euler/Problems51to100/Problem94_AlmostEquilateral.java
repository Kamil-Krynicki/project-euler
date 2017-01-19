package org.krynicki.euler.Problems51to100;

/**
 * Created by kamil.krynicki on 19/01/2017.
 */
public class Problem94_AlmostEquilateral {
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

        long c = (int) 3;
        long a = 3;

        double area1;
        double area2;
        int hp;
        do {
            if(true) {
                a = c - 1;
                area1 = ((double)c/4)*Math.sqrt(4*a*a-c*c);

                if(isInt(area1)) {
                    System.out.println(area1);
                    sum += 2*a+c;
                }

                a = c + 1;
                area1 = ((double)c/4)*Math.sqrt(4*a*a-c*c);

                if(isInt(area1)) {
                    System.out.println(area1);
                    sum += 2*a+c;
                }
            }
            c++;
        } while ( 2*a + c <= max);

        long t2 = System.currentTimeMillis();

        System.out.println(sum);
        System.out.println(t2 - t1);
    }

    static boolean isInt(double d) {
        double v = (int) d - d;
        return v < 0.000001 && v > -0.000001;
    }

}
