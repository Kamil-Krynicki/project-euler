package org.krynicki.euler;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by kamil.krynicki on 03/11/2016.
 */
public class Problem51_PrimeDigitReplacements {
    /*
    By replacing the 1st digit of the 2-digit number *3, it turns out that six of the nine
    possible values: 13, 23, 43, 53, 73, and 83, are all prime.

    By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number is the
    first example having seven primes among the ten generated numbers, yielding the
    family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993.
    Consequently 56003, being the first member of this family, is the smallest prime with this property.

    Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the
    same digit, is part of an eight prime value family.
    */

    static Map<String, Integer> patternCounts = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Problem51_PrimeDigitReplacements k = new Problem51_PrimeDigitReplacements();
        long t1 = System.currentTimeMillis();

        QuadraticPrimes.PrimeGenerator gen = QuadraticPrimes.PrimeGenerator.getPrimesBelow(1000000);

        int i = 0;
        do {
            i = gen.nextPrime(i);
        } while (!addPatterns(k.patterns(i)));

        long t2 = System.currentTimeMillis();


        //patternCounts.entrySet().stream().filter(new Predicate<Map.Entry<String, Integer>>() {
        //    @Override
        //    public boolean test(Map.Entry<String, Integer> stringListEntry) {
        //        return stringListEntry.getValue()>7;
        //    }
        //}).forEach(System.out::println);

        System.out.println(t2 - t1);
    }

    private static boolean addPatterns(Set<String> patterns) {
        for (String pattern : patterns) {
            Integer count = patternCounts.get(pattern);

            if (count == null) {
                count = 0;
            }

            count++;

            if (count == 8) {
                System.out.println("Found " + pattern);
                return true;
            }

            patternCounts.put(pattern, count);
        }
        return false;
    }

    Set<String> patterns(int val) {
        char[] num = String.valueOf(val).toCharArray();

        Set<String> result = new HashSet<>();

        for (char c:num) {
            patternsOfDigit(num, c, 0, result);
        }

        return result;
    }

    private void patternsOfDigit(char[] x, char c, int i, Set<String> result) {
        if (i == x.length) {
            result.add(String.valueOf(x));
        } else {
            patternsOfDigit(x, c, i + 1, result);
            if (x[i] == c) {
                char prv = x[i];
                x[i] = 'x';
                patternsOfDigit(x, c, i + 1, result);
                x[i] = prv;
            }
        }
    }
}
