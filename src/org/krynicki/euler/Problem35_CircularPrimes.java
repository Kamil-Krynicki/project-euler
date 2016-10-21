package org.krynicki.euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by kamil.krynicki on 21/10/2016.
 */
public class Problem35_CircularPrimes {
    /*
    The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.

    There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.

    How many circular primes are there below one million?
    */

    // observation - the digits of the number must not be even
    // observation - EXCEPT 2

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        CircularPrimes p = new CircularPrimes();
        Set<Integer> result = p.getCircularPrimes(1000000);

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
        System.out.println(result.stream().sorted().distinct().collect(Collectors.toList()));
        System.out.println(result.size());
    }

    public static class CircularPrimes {
        private Map<Integer, Boolean> isPrime = new HashMap<>();

        public Set<Integer> getCircularPrimes(int limit) {
            CombinationGenerator c = new CombinationGenerator(new int[]{1, 3, 5, 7, 9});

            Set<Integer> result = new HashSet<>();
            int next;

            while ((next = c.next()) < limit)
                if (isPrimeAllRotations(next))
                    result.add(next);

            result.add(2);
            result.remove(1);

            return result;
        }

        public boolean isPrimeAllRotations(int next) {
            int current = next;
            do {
                if (!isPrime(current)) return false;
            } while ((current = rot(current)) != next);
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

        private int rot(int number) {
            int result = number / 10;
            result += (number % 10) * Math.pow(10, Math.floor(Math.log10(number)));
            return result;
        }
    }

    public static class CombinationGenerator {
        private int[] components;
        private int base;
        private List<Integer> values;

        public CombinationGenerator(int[] components) {
            Arrays.sort(components);
            this.components = components;
            this.base = components.length;
            this.values = new ArrayList<>();
        }

        int next() {
            increment();
            return decode(values);
        }

        private int decode(List<Integer> values) {
            int result = 0;

            for (int i = values.size() - 1; i >= 0; i--) {
                result *= 10;
                result += components[values.get(i)];
            }

            return result;
        }

        private void increment() {
            increment(values, 0);
        }

        private void increment(List<Integer> values, int i) {
            if (values.size() == i) {
                values.add(0);
            } else {
                values.set(i, values.get(i) + 1);
                if (values.get(i) >= base) {
                    values.set(i, values.get(i) - base);
                    increment(values, i + 1);
                }
            }
        }
    }
}
