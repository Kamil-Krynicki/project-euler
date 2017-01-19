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

        int a = 2;
        int c = 3;

        double area1;
        double area2;
        int hp;
        do {
            if(c%4==0) {
                a = c - 1;
                area1 = c/4*Math.sqrt(4*a*a+c*c);

                hp = a+c/2;

                area2 = Math.sqrt(hp * (hp - a) * (hp - a) * (hp - c));

                a = c + 1;
            }
            c++;


            //c/4*sqrt(4*a*a+c*c)

            // c = a - 1, c = a + 1

           // System.out.println(Arrays.toString(sides));
            //if(p%2==0 && p%3!=0){
            //    hp = p / 2;
//
            //    //BigInteger kamil = BigInteger.valueOf(hp).multiply(BigInteger.valueOf((hp - a))).multiply(BigInteger.valueOf((hp - b))).multiply(BigInteger.valueOf((hp - c)));
//
            //    area1 = Math.sqrt(hp*(hp - sides[0])*(hp - sides[1])*(hp - sides[2]));
            //    //System.out.println("-----");
            //    //System.out.println(p);
//
            //    System.out.println(area1);
            //    if (isInt(area1)) {
            //        //System.out.println("Perimeter:" +p +" sides: "+sides[0]+", "+sides[1]+", "+sides[2]);
            //        //System.out.println(Math.sqrt(hp * (hp - sides[0]) * (hp - sides[1]) * (hp - sides[2])));
            //        sum += p;
            //    }
            //}
        } while ( a <= max +1);

        long t2 = System.currentTimeMillis();

        System.out.println(sum);
        System.out.println(t2 - t1);
    }

    static boolean isInt(double d) {
        double v = (int) d - d;
        return v < 0.001 && v > -0.001;
    }

}
