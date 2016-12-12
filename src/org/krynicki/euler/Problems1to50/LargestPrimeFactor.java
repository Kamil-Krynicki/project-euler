package org.krynicki.euler.Problems1to50;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kamil.krynicki on 29/09/2016.
 */
public class LargestPrimeFactor {
    /**
     * The prime factors of 13195 are 5, 7, 13 and 29.
     * What is the largest prime factor of the number 600851475143 ?
     */
    Set<Integer> factorsOf(long input) {
        Set<Integer> factors = new HashSet<>();

        long val = input;
        int factor;

        while (val != 1) {
            factor = 2;
            while (val % factor != 0) {
                factor++;
            }
            factors.add(factor);
            val /= factor;
        }

        return factors;
    }


    static public void main(String[] args) {
        LargestPrimeFactor t = new LargestPrimeFactor();
        System.out.println(Collections.max(t.factorsOf(600851475143l)));
    }
}
