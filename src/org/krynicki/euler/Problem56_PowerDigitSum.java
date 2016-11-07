package org.krynicki.euler;

import java.math.BigInteger;

/**
 * Created by kamil.krynicki on 07/11/2016.
 */
public class Problem56_PowerDigitSum {
    /*
    A googol (10100) is a massive number: one followed by one-hundred zeros; 100100 is almost unimaginably large:
    one followed by two-hundred zeros. Despite their size, the sum of the digits in each number is only 1.

    Considering natural numbers of the form, ab, where a, b < 100, what is the maximum digital sum?
    */

    public static void main(String... args) {
        long t1 = System.currentTimeMillis();
        int sum=0;
        int max=0;

        BigInteger factorA, factorB;
        for (int a = 1; a <= 100; a++) {
            factorA = BigInteger.valueOf(a);
            factorB = factorA;
            for (int b = 1; b <= 100; b++) {
                sum = digitSum(factorA);
                if(sum > max) {
                    max = sum;
                }
                factorA = factorA.multiply(factorB);
            }
        }
        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
        System.out.println(max);
    }

    static int digitSum(BigInteger val) {
        char[] digits = val.toString().toCharArray();
        int result = -0x30 * digits.length;

        for (char a : digits) {
            result += a;
        }

        return result;
    }

}
