package org.krynicki.euler.util;

import org.krynicki.euler.QuadraticPrimes;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by K on 2016-11-17.
 */
public class PrimeGenerator {
    private TreeSet<Integer> primes;

    public SortedSet<Integer> getPrimes() {
        return primes;
    }

    public static PrimeGenerator getPrimesBelow(int to) {
        PrimeGenerator result = new PrimeGenerator();

        result.primes.add(2);
        int i = 3;
        while (i < to) {
            if (result.checkPrime(i)) {
                result.primes.add(i);
            }
            i += 2;
        }

        return result;
    }

    public PrimeGenerator() {
        primes = new TreeSet<>();

    }

    public PrimeGenerator(int size) {
        primes = new TreeSet<>();
        primes.add(2);
        primes.add(3);
        int i = 5;
        while (primes.size() < size) {
            if (checkPrime(i)) {
                primes.add(i);
            }
            i += 2;
        }
    }

    public int nextPrime(int number) {
        expandBuffer(number);
        try {
            return primes.higher(number);

        } catch (NullPointerException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public boolean isPrime(int number) {
        expandBuffer(number);
        return primes.contains(number);
    }

    public void expandBuffer(int number) {
        int i = primes.last();
        while (number >= primes.last()) {
            if (checkPrime(i)) {
                primes.add(i);
            }
            i += 2;
        }
    }

    private boolean checkPrime(int i) {
        for (int prime : primes.headSet(primes.higher((int) (Math.sqrt(i))))) {
            if (i % prime == 0) {
                return false;
            }
        }
        return true;
    }
}
