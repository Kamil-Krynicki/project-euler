package org.krynicki.euler.Problems51to100;

/**
 * Created by K on 2016-11-10.
 */
public class Problem63_PowerfulDigitCounts {
    /*
    The 5-digit number, 16807=75, is also a fifth power. Similarly, the 9-digit number, 134217728=89, is a ninth power.

    How many n-digit positive integers exist which are also an nth power?
    */


    public static void main(String[] args) {
        final long t1 = System.nanoTime();

        int sum = 0;
        int increment;
        int digitCount = 0;

        do {
            ++digitCount;
            increment = (int) Math.ceil(10 / Math.pow(10, (double) 1 / digitCount));
            sum -= increment;
        } while (increment < 10);

        sum += digitCount*10;

        System.out.println("Sum2 = " + sum);

        final long t2 = System.nanoTime();

        System.out.println(t2 - t1);
    }
}
