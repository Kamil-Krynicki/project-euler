package org.krynicki.euler.Problems51to100;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by K on 2016-11-21.
 */
public class Problem74_DigitFactorialChains {
    /*
    The number 145 is well known for the property that the sum of the factorial of its digits is equal to 145:

    1! + 4! + 5! = 1 + 24 + 120 = 145

    Perhaps less well known is 169, in that it produces the longest chain of numbers that link back to 169; it turns out that there are only three such loops that exist:

    169 → 363601 → 1454 → 169
    871 → 45361 → 871
    872 → 45362 → 872

    It is not difficult to prove that EVERY starting number will eventually get stuck in a loop. For example,

    69 → 363600 → 1454 → 169 → 363601 (→ 1454)
    78 → 45360 → 871 → 45361 (→ 871)
    540 → 145 (→ 145)

    Starting with 69 produces a chain of five non-repeating terms, but the longest non-repeating chain with a starting number below one million is sixty terms.

    How many chains, with a starting number below one million, contain exactly sixty non-repeating terms?
    */
    public static final double iterations = 1E6;
    public static final int searchedLen = 60;

    static int[] factorials;
    static int[] memo;

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        factorials = new int[10];
        factorials[0] = 1;
        for (int i = 1; i < factorials.length; i++) {
            factorials[i] = factorials[i - 1] * i;
        }

        memo = new int[6 * factorialOfDigits(9) + 1];

        int sum = 0;
        Set<Integer> currentPath;

        for(int start = 1; start <= iterations;start++) {
            if(memo[start]!=0) {
                continue;
            }

            currentPath = new LinkedHashSet<>();

            int current = start;
            int len;

            do {
                if(memo[current]!=0) break;

                currentPath.add(current);
                current = factorialOfDigits(current);
            } while (!currentPath.contains(current));

            len = currentPath.size() + memo[current];

            if(len == searchedLen) {
                sum++;
            }

            boolean found = false;
            for(int i:currentPath) {
                if(i == current) found = true;
                memo[i] = len;
                if(!found) len--;
            }
        }

        long t2 = System.currentTimeMillis();

        System.out.println(sum);
        System.out.println(t2 - t1);
    }

    static public int factorialOfDigits(int num) {
        int tmp = num;
        int result = 0;

        while (tmp > 0) {
            result += factorials[tmp % 10];
            tmp /= 10;
        }

        return result;
    }
}
