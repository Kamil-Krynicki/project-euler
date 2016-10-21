package org.krynicki.euler;

/**
 * Created by kamil.krynicki on 21/10/2016.
 */
public class Problem33DigitCancellingFractions {
    /*
    The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may
    incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.

    We shall consider fractions like, 30/50 = 3/5, to be trivial examples.

    There are exactly four non-trivial examples of this type of fraction, less than one in value,
    and containing two digits in the numerator and denominator.

    If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
    */

    // observation 1: these fractions conform to ix/yi, ix/iy. xi/yi. xi/iy pattern, where i, x, y are in (1, 2... 9)

    public static void main(String[] args) {

        long t1 = System.nanoTime();

        int denominatorVariantA;
        int denominatorVariantB;
        int numeratorVariantA;
        int numeratorVariantB;
        double fraction;

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                for (int k = 1; k < 10; k++) {
                    fraction = (double) j / k;
                    denominatorVariantA = i * 10 + k;
                    denominatorVariantB = k * 10 + i;
                    numeratorVariantA = i * 10 + j;
                    numeratorVariantB = j * 10 + i;

                    check(fraction, numeratorVariantA, denominatorVariantA);
                    check(fraction, numeratorVariantA, denominatorVariantB);
                    check(fraction, numeratorVariantB, denominatorVariantB);
                    check(fraction, numeratorVariantB, denominatorVariantA);
                }
            }
        }
        long t2 = System.nanoTime();

        System.out.println(t2 - t1);


    }

    private static void check(double reduced, int numerator, int denominator) {
        if (reduced < 1 && Math.abs((double) numerator / denominator - reduced) < 0.0001) {
            System.out.println(numerator + "/" + denominator);
        }
    }

}
