package org.krynicki.euler.Problems51to100;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by K on 2016-11-24.
 */
public class Problem80_SquareRootDigitalExpansion {
    public static final MathContext ROUNDING = new MathContext(102, RoundingMode.HALF_DOWN);
    /*
    It is well known that if the square root of a natural number is not an integer, then it is irrational. The decimal expansion of such square roots is infinite without any repeating pattern at all.

    The square root of two is 1.41421356237309504880..., and the digital sum of the first one hundred decimal digits is 475.

    For the first one hundred natural numbers, find the total of the digital sums of the first one hundred decimal digits for all the irrational square roots.
    */

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();


        int result = 0;
        int j = 2;
        for(int i=2;i<100;i++) {
            if(j*j == i) {
                j++;
                continue;
            }
            result+=sumDigits(getApprox(i));
        }

        System.out.println(result);


        long t2 = System.currentTimeMillis();

        System.out.println("==========");
        System.out.println(t2 - t1);
    }

    static int sumDigits(BigDecimal num) {
        char[] digits = num.toPlainString().replace(".","").toCharArray();
        int sum = -100*0x30;
        for(int i=0;i<100;i++) {
            sum += digits[i];
        }
        return sum;
    }

    static BigDecimal getApprox(int i) {
        BigDecimal in = BigDecimal.valueOf(i);
        BigDecimal next = in.divide(BigDecimal.valueOf(10));
        BigDecimal current;

        do {
            current = next;
            next = newtonStep(in, current);
        } while (next.compareTo(current) != 0);

        return current;
    }

    static BigDecimal newtonStep(BigDecimal num, BigDecimal guess) {
        return guess.subtract(guess.multiply(guess).subtract(num)
                                .divide(BigDecimal.valueOf(2).multiply(guess), ROUNDING)
                        , ROUNDING);
    }
}
