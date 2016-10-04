package org.krynicki.euler;

import java.util.Arrays;

/**
 * Created by kamil.krynicki on 04/10/2016.
 */
public class SumOfPrimes {
    /*
    * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
    * Find the sum of all the primes below two million.
    */
    private boolean[] primes;
    private long result;

    public SumOfPrimes(int n) {
        primes = new boolean[n];
        Arrays.fill(primes, true);

        result = 0;

        primes[0] = false;
        primes[1] = false;

        int sieve = 2;

        while (sieve <= n/2) {
            while (!primes[++sieve]) {}

            for (int i = 2*sieve; i < n; i += sieve) {
                primes[i] = false;
            }
        }

        for(int i=3;i<n;i+=2) {
            if(primes[i]) result += i;
        }
    }

    long get() {
        return result;
    }

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        SumOfPrimes t = new SumOfPrimes((int) 2E6);
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
        System.out.println(t.get());
    }
}
