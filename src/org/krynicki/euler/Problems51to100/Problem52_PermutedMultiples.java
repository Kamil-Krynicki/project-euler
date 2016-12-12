package org.krynicki.euler.Problems51to100;

import java.io.IOException;

/**
 * Created by K on 2016-11-03.
 */
public class Problem52_PermutedMultiples {
    /*
    It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits,
    but in a different order.

    Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
    */

    // observation - x has to be between (10^n, 2*10^n), otherwise 6x would have more digits than x

    public static void main(String[] args) throws IOException {
        Problem52_PermutedMultiples k = new Problem52_PermutedMultiples();
        long t1 = System.currentTimeMillis();

        System.out.println(find());

        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
    }

    private static int find() {
        for (int range = 10; ; range *= 10) {
            for (int i = range; 6 * i < 10 * range; ++i) {
                if (checkPermutations(i))
                    return i;
            }
        }
    }

    private static boolean checkPermutations(int i) {
        for (int j = 2 * i; j <= 6 * i; j += i) {
            if (!isPermutation(i, j)) {
                return false;
            }
        }
        return true;
    }

    static boolean isPermutation(int a, int b) {
        byte digits[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        short digitCount = 0;

        for (; a != 0; a /= 10) {
            ++digits[a % 10];
            ++digitCount;
        }

        for (; b != 0; b /= 10) {
            if (digits[b % 10]-- == 0)
                return false;
            --digitCount;
        }

        return digitCount == 0;
    }
}
