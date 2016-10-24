package org.krynicki.euler;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by K on 2016-10-18.
 */
public class QuadraticPrimes {
    /*
    Euler discovered the remarkable quadratic formula:
    n2+n+41n2+n+41
    It turns out that the formula will produce 40 primes for the consecutive integer values 0≤n≤390≤n≤39.
    However, when n=40,402+40+41=40(40+1)+41n=40,402+40+41=40(40+1)+41 is divisible by 41, and
    certainly when n=41,412+41+41n=41,412+41+41 is clearly divisible by 41.

    The incredible formula n2−79n+1601n2−79n+1601 was discovered, which produces 80 primes for the
    consecutive values 0≤n≤790≤n≤79. The product of the coefficients, −79 and 1601, is −126479.

    Considering quadratics of the form:

    n2+an+bn2+an+b, where |a|<1000|a|<1000 and |b|≤1000|b|≤1000

    where |n||n| is the modulus/absolute value of nn
    e.g. |11|=11|11|=11 and |−4|=4|−4|=4
    Find the product of the coefficients, aa and bb, for the quadratic expression that produces
    the maximum number of primes for consecutive values of nn, starting with n=0n=0.
    */

    // observation 1 : b has to be a prime, otherwise for n=0, the result isn't prime (order of magnitude less checks)
    // observation 2 : a is in (-b, 1000), otherwise for n=1 the result is negative (x0.5 checks)
    // observation 3 : a must be odd, otherwise for n=1 the result is even (x0.5 checks)
    private final PrimeGenerator f;
    private final int max;

    public QuadraticPrimes(int max) {
        this.max = max;
        this.f = new PrimeGenerator(max);
    }

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        QuadraticPrimes q = new QuadraticPrimes(1000);
        q.printLongestPrimeSequence();
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    void printLongestPrimeSequence() {
        int curLen;
        int bestLen = -1, bestA = 0, bestB = 0;

        for (int b = 2; b < max; b = f.nextPrime(b)) {
            for (int a = -b; a < max; a+=2) {
                curLen = primeSequenceLen(a, b);
                if (curLen > bestLen) {
                    bestLen = curLen;
                    bestA = a;
                    bestB = b;
                }
            }
        }

        System.out.println("max: " + bestLen);
        System.out.println("a: " + bestA);
        System.out.println("b: " + bestB);
    }

    private int primeSequenceLen(int a, int b) {
        int n = 0;
        while (f.isPrime((n + a) * n + b)) {
            n++;
        }
        return n;
    }

    static class PrimeGenerator {
        private SortedSet<Integer> primes;
        private int maxPrime;

        public PrimeGenerator(int size) {
            primes = new TreeSet<>();
            primes.add(2);
            int i = 3;
            while (primes.size() < size) {
                if (checkPrime(i)) {
                    primes.add(i);
                }
                i += 2;
            }
            maxPrime = primes.last();
        }

        public int nextPrime(int number) {
            expandBuffer(number);
            return primes.tailSet(number + 1).first();
        }

        public boolean isPrime(int number) {
            expandBuffer(number);
            return primes.contains(number);
        }

        private void expandBuffer(int number) {
            if (number > maxPrime) {
                for (int i = maxPrime; i < number; i += 2) {
                    if (checkPrime(i)) {
                        primes.add(i);
                    }
                }
                maxPrime = primes.last();
            }
        }

        private boolean checkPrime(int i) {
            for (int prime : primes) {
                if (i % prime == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}