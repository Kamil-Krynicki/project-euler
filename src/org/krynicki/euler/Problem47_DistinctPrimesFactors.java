package org.krynicki.euler;

import java.util.TreeSet;

/**
 * Created by K on 2016-10-28.
 */
public class Problem47_DistinctPrimesFactors {
    static TreeSet<Integer> primes = new TreeSet<>();

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        int found = 0;

        int i = 2;
        int factorCount;
        while (found < 4) {
            factorCount = countFactors(i);

            if (factorCount == 4) {
                found++;
            } else {
                if(factorCount == 0) {
                    primes.add(i);
                }
                found = 0;
            }
            i++;
        }

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
        System.out.println(i-4);
    }

    static int countFactors(int value) {
        int count = 0;

        for(int potentialFactor:primes) {
            if(value%potentialFactor==0) {
                do {
                    value /= potentialFactor;
                } while (value % potentialFactor == 0);
                count++;
                if(value == 1) {
                    return count;
                }
            }
        }

        return count;
    }
}
