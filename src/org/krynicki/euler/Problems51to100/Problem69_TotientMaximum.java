package org.krynicki.euler.Problems51to100;

import org.krynicki.euler.util.PrimeGenerator;

/**
 * Created by K on 2016-11-16.
 */
public class Problem69_TotientMaximum {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        PrimeGenerator generator = new PrimeGenerator(5);

        int val = 1;
        int nextPrime = 1;

        do {
            nextPrime = generator.nextPrime(nextPrime);
            val *= nextPrime;
        } while (val <= 1E6);

        val/=nextPrime;

        long t2 = System.currentTimeMillis();

        System.out.println(val);
        System.out.println("================");

        System.out.println(t2 - t1);
    }


}
