package org.krynicki.euler.Problems1to50;

/**
 * Created by kamil.krynicki on 19/10/2016.
 */
public class SpiralNumbers {
    /*
    Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:

    21 22 23 24 25
    20  7  8  9 10
    19  6  1  2 11
    18  5  4  3 12
    17 16 15 14 13

    It can be verified that the sum of the numbers on the diagonals is 101.

    What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?

    // observation 1: nth "ring" contributes 4*(4*n^2-7*n+4) to the total sum
    // observation 2: sum for i = 1 to M of the equation above is 4*M*((M+1)*(4/3*M-17/6)+4)-3!!
    */

    public static void main(String[] args) {
        int size = 501;
        long t1 = System.currentTimeMillis();

        System.out.println(superFast(size));
        System.out.println(fast(size));

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    public static long superFast(int size) {
        return (long) (4* size *((size +1)*((double)(4* size - 8.5))/3+4)) - 3;
    }

    public static long fast(int size) {
        long sum = 1;
        for(int i=2;i<size+1;i++) {
            sum += 4*(4*i*i-7*i+4);
        }
        return sum;
    }
}
