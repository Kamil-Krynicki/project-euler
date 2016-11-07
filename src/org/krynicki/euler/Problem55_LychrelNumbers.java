package org.krynicki.euler;

import java.math.BigInteger;

/**
 * Created by kamil.krynicki on 07/11/2016.
 */
public class Problem55_LychrelNumbers {
    /*
    If we take 47, reverse and add, 47 + 74 = 121, which is palindromic.

    Not all numbers produce palindromes so quickly. For example,

    349 + 943 = 1292,
    1292 + 2921 = 4213
    4213 + 3124 = 7337

    That is, 349 took three iterations to arrive at a palindrome.

    Although no one has proved it yet, it is thought that some numbers, like 196, never produce a palindrome.
    A number that never forms a palindrome through the reverse and add process is called a Lychrel number.
    Due to the theoretical nature of these numbers, and for the purpose of this problem, we shall assume that a number is
    Lychrel until proven otherwise. In addition you are given that for every number below ten-thousand, it will either
    (i) become a palindrome in less than fifty iterations, or,
    (ii) no one, with all the computing power that exists, has managed so far to map it to a palindrome.
    In fact, 10677 is the first number to be shown to require over fifty iterations before producing a palindrome: 4668731596684224866951378664 (53 iterations, 28-digits).

    Surprisingly, there are palindromic numbers that are themselves Lychrel numbers; the first example is 4994.

    How many Lychrel numbers are there below ten-thousand?

    NOTE: Wording was modified slightly on 24 April 2007 to emphasise the theoretical nature of Lychrel numbers.
    */

    // obsevation - even though it causes overflow - for longs it gives the same result as for BigInt. The reason being - for long it works well
    // till about 30 repetitions, and there are no numbers that reach 30, but fail to reach 50.

    static public void main(String... args) {
        Lychrel k = new Lychrel();

        int result=0;
        long t1 = System.currentTimeMillis();
        for (long i = 1; i <= 10000; i++) {
            if (k.iterationsTillPalindrome(i) > 50) {
                result++;
            }
        }
        long t2 = System.currentTimeMillis();

        System.out.println("Count" + result);

        System.out.println(t2 - t1);
    }

    static class Lychrel {
        private final int longMax = 50;
        private final int bigIntMax = 50;

        public int iterationsTillPalindrome(BigInteger num) {
            int result = 0;
            do {
                result++;
                if (result > bigIntMax) {
                    return bigIntMax + 1;
                }
                num = num.add(reverse(num));
            } while (!isPalindrome(num));
            return result;
        }

        private BigInteger reverse(BigInteger num) {
            BigInteger result = BigInteger.ZERO;

            BigInteger tmp = num;
            BigInteger[] step;

            while (tmp.compareTo(BigInteger.ZERO) > 0) {
                result = result.multiply(BigInteger.TEN);
                step = tmp.divideAndRemainder(BigInteger.TEN);
                result = result.add(step[1]);
                tmp = step[0];
            }

            return result;
        }

        private boolean isPalindrome(BigInteger num) {
            return num.subtract(reverse(num)).equals(BigInteger.ZERO);
        }

        public int iterationsTillPalindrome(long num) {
            int result = 0;
            do {
                result++;
                if (result > longMax) {
                    return longMax + 1;
                }
                num += reverse(num);
            } while (!isPalindrome(num));
            return result;
        }

        public boolean isPalindrome(long num) {
            return num - reverse(num) == 0;
        }

        public long reverse(long num) {
            long result = 0;

            long tmp = num;

            while (tmp > 0) {
                result *= 10;
                result += tmp % 10;
                tmp /= 10;
            }

            return result;
        }
    }
}
