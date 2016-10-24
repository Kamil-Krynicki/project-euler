package org.krynicki.euler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by kamil.krynicki on 24/10/2016.
 */
public class Problem37_TruncablePrimes {
    /*
    The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right, and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379, 37, and 3.

    Find the sum of the only eleven primes that are both truncatable from left to right and right to left.

    NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
     */

    // observation 1 - it's just like the rotate one
    // observation 2 - the only place the digit 2 can appear is the first position, as it is the only prime even number, but that would be a hyperoptimization.

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        CircularPrimes p = new CircularPrimes();
        Set<Integer> result = p.getTruncablePrimes(1000000);

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
        System.out.println(result.stream().sorted().distinct().collect(Collectors.toList()));
        System.out.println(result.stream().reduce(Integer::sum));
        System.out.println(result.size());
    }

    public static class CircularPrimes {
        private Map<Integer, Boolean> isPrime = new HashMap<>();
        private int[] pows = {1, 10, 100, 1000, 10000, 100000, 1000000};

        public CircularPrimes() {
            isPrime.put(1, false);
        }

        public Set<Integer> getTruncablePrimes(int limit) {
            Problem35_CircularPrimes.CombinationGenerator c = new Problem35_CircularPrimes.CombinationGenerator(new int[]{1, 2, 3, 5, 7, 9});

            Set<Integer> result = new HashSet<>();
            int next;

            while ((next = c.next()) < limit)
                if (isPrimeAllTruncations(next))
                    result.add(next);

            result.remove(2);
            result.remove(3);
            result.remove(5);
            result.remove(7);

            return result;
        }

        public boolean isPrimeAllTruncations(int next) {
            int current;

            current = next;

            do {
                if (!isPrime(current)) return false;
            } while ((current = trunkRight(current)) > 0);

            current = next;

            do {
                if (!isPrime(current)) return false;
            } while ((current = trunkLeft(current)) > 0);

            return true;
        }

        public boolean isPrime(int number) {
            if (!isPrime.containsKey(number)) {

                for (int i = 2; i <= Math.sqrt(number); i++)
                    if (number % i == 0) {
                        isPrime.put(number, false);
                        return false;
                    }

                isPrime.put(number, true);
                return true;
            }
            return isPrime.get(number);
        }

        private int trunkLeft(int number) {
            return number % pows[(int) Math.log10(number)];
        }

        private int trunkRight(int number) {
            return number / 10;
        }
    }
}
