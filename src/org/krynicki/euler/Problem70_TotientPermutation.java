package org.krynicki.euler;

import org.hibernate.mapping.Array;

import java.util.Arrays;
import java.util.Iterator;
import java.util.SortedSet;

/**
 * Created by K on 2016-11-17.
 */
public class Problem70_TotientPermutation {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        QuadraticPrimes.PrimeGenerator generator = QuadraticPrimes.PrimeGenerator.getPrimesBelow((int) 5E6);
        SortedSet<Integer> primesSet = generator.getPrimes();
        Iterator<Integer> iterator = primesSet.iterator();

        int[] primes = new int[primesSet.size()];

        int i = 0;
        while(iterator.hasNext()) {
            primes[i++] = iterator.next();
        }
        int counter = 0;
        int maxPrime = primes.length - 1;

        for(int j=0;j<maxPrime;j++) {
            int k=j;
            while(primes[k]*primes[j]<1E6) {
                k++;
                counter++;

                System.out.println(counter+" : " + primes[k] +"x"+primes[j]);
            }
            maxPrime = k;
        }

        long t2 = System.currentTimeMillis();

        System.out.println("================");
        //System.out.println(Arrays.toString(primes));

        System.out.println(t2 - t1);
    }
}
