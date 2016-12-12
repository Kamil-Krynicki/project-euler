package org.krynicki.euler.Problems1to50;

import java.math.BigInteger;

/**
 * Created by K on 2016-10-17.
 */
public class Fibonacci1000Digit {

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        FibonacciFinder f = new FibonacciFinder();

        int i = 3;

        while(f.next().toString().length() < 1000) {
            i++;
        }

        System.out.println(i);

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    static class FibonacciFinder {
        BigInteger prvFibA = BigInteger.ONE;
        BigInteger prvFibB = BigInteger.ONE;

        BigInteger next() {
            BigInteger next = prvFibA.add(prvFibB);
            prvFibA = prvFibB;
            prvFibB = next;
            return next;
        }

        BigInteger current() {
            return prvFibB;
        }
    }
}
