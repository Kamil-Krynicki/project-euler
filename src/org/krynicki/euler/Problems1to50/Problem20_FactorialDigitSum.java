package org.krynicki.euler.Problems1to50;

import java.math.BigInteger;

/**
 * Created by K on 2016-10-13.
 */
public class Problem20_FactorialDigitSum {
    /*
    n! means n × (n − 1) × ... × 3 × 2 × 1

    For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
    and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.

    Find the sum of the digits in the number 100!
     */

    public static void main(String[] args) {
        Problem20_FactorialDigitSum f = new Problem20_FactorialDigitSum();

        System.out.print(f.sumDigits(f.factorial(100)));
    }

    int sumDigits(BigInteger in) {
        int sum = 0;
        BigInteger[] tmp;
        while(in.compareTo(BigInteger.ZERO) > 0) {
            tmp = in.divideAndRemainder(BigInteger.TEN);
            sum += tmp[1].intValue();
            in = tmp[0];
        }
        return sum;
    }

     BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;

        for(BigInteger i = BigInteger.valueOf(2); i.compareTo(BigInteger.valueOf(n))<0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(i);
        }

        return result;
    }
}
