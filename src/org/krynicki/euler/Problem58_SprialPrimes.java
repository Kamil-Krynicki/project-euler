package org.krynicki.euler;

import java.util.Arrays;

/**
 * Created by kamil.krynicki on 08/11/2016.
 */
public class Problem58_SprialPrimes {
    /*
    Starting with 1 and spiralling anticlockwise in the following way,
    a square spiral with side length 7 is formed.

    37 36 35 34 33 32 31
    38 17 16 15 14 13 30
    39 18  5  4  3 12 29
    40 19  6  1  2 11 28
    41 20  7  8  9 10 27
    42 21 22 23 24 25 26
    43 44 45 46 47 48 49

    It is interesting to note that the odd squares lie along the bottom right diagonal, but what is more interesting
    is that 8 out of the 13 numbers lying along both diagonals are prime; that is, a ratio of 8/13 ≈ 62%.

    If one complete new layer is wrapped around the spiral above, a square spiral with side length 9 will be formed.
    If this process is continued, what is the side length of the square spiral for which the ratio of primes along
    both diagonals first falls below 10%?
    */

    public static void main(String... args) {
        GrowingSquare g = new GrowingSquare();

        final long t1 = System.currentTimeMillis();
        int primes = 0;
        int total = 1;
        do {
            g.grow();
            primes += g.countPrimeCorners();
            total += 4;
        } while (10 * primes > total);

        System.out.println("Size: "+g.size());
        final long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
    }

    static class GrowingSquare {
        private final int[] corners;
        private int size;

        GrowingSquare() {
            this.corners = new int[4];
            Arrays.fill(corners, 1);
            size = 1;
        }

        public void grow() {
            size ++;
            corners[0] = corners[3] + size;
            corners[1] = corners[0] + size;
            corners[2] = corners[1] + size;
            corners[3] = corners[2] + size;
            size ++;
        }

        public String toString() {
            String result = "";

            result += corners[1] + ", ";
            result += corners[0];
            result += '\n';
            result += corners[2] + ", ";
            result += corners[3];

            return result;
        }

        public int countPrimeCorners() {
            int result = 0;
            for (int i = 0; i < 3; i++) {
                if (checkPrime(corners[i])) result++;
            }
            return result;
        }

        private boolean checkPrime(int i) {
            for (int num = 2; num < Math.sqrt(i) ; num ++) {
                if (i % num == 0) {
                    return false;
                }
            }
            return true;
        }

        public int size() {
            return size;
        }
    }
}
