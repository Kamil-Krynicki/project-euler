package org.krynicki.euler.Problems51to100;

import java.math.BigInteger;

/**
 * Created by kamil.krynicki on 07/11/2016.
 */
public class Problem57_SquareRootConvergents {
    /*
    It is possible to show that the square root of two can be expressed as an infinite continued fraction.

    √ 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...

    By expanding this for the first four iterations, we get:

    1 + 1/2 = 3/2 = 1.5
    1 + 1/(2 + 1/2) = 7/5 = 1.4
    1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
    1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...

    The next three expansions are 99/70, 239/169, and 577/408, but the eighth expansion, 1393/985, is the first example
    where the number of digits in the numerator exceeds the number of digits in the denominator.

    In the first one-thousand expansions, how many fractions contain a numerator with more digits than denominator?
    */

    // observation - numerator (n+1) = numerator (n) + 2*denominator(n)
    // observation - denominator (n+1) = numerator(n) + denominator(n)
    // observation - so: numerator (n+1) = denominator(n+1) + denominator(n)

    public static void main(String... args) {
        Fraction f = new Fraction();


        int count = 0;
        for(int i=0;i<1000;i++) {
            f.next();
            int num = f.numerator.toString().length();
            int den = f.denominator.toString().length();
            if(num > den) {
                count++;
                System.out.println("num:" + num);
                System.out.println("den:" + den);
                System.out.println("=================");
            }
        }
        System.out.println(count);

    }

    static class Fraction {
        BigInteger numerator;
        BigInteger denominator;

        Fraction() {
            numerator = BigInteger.valueOf(3);
            denominator = BigInteger.valueOf(2);
        }

        void next() {
            BigInteger oldDenominator = denominator;

            denominator = denominator.add(numerator);
            numerator = denominator.add(oldDenominator);
        }

        @Override
        public String toString() {
            return "Fraction{" +
                    "numerator=" + numerator +
                    ", denominator=" + denominator +
                    '}';
        }
    }


}
