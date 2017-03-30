package org.krynicki.euler.Problems51to100;

import java.io.IOException;

/**
 * Created by K on 30.03.2017.
 */

/*
Comparing two numbers written in index form like 211 and 37 is not difficult, as any calculator would confirm that
211 = 2048 < 37 = 2187.

However, confirming that 632382518061 > 519432525806 would be much more difficult, as both numbers contain over three
million digits.

Using base_exp.txt (right click and 'Save Link/Target As...'), a 22K text file containing one thousand lines with a
base/exponent pair on each line, determine which line number has the greatest numerical value.

NOTE: The first two lines in the file represent the numbers in the example given above.
 */
public class Problem99_LargestExponential {
    public static void main(String... args) {
        System.out.println(isLarger(632382, 518061, 519432, 525806));
        System.out.println(isLarger(519432, 525806, 632382, 518061));
    }

    public static boolean isLarger(double aBase, double aExponent, double bBase, double bExponent) {
        if(aBase < bBase && aExponent < bExponent)
            return true;

        if(aBase > bBase && aExponent > bExponent)
            return false;

        if(aBase < bBase && aExponent > bExponent) {
            double newABase = aBase;
            double newBBase = bBase / newABase;

            double newAExponent = aExponent;
            double newBExponent = bExponent / newAExponent;

            return isLarger(newABase, newAExponent, newBBase, newBExponent);
        }

        if(aBase > bBase && aExponent < bExponent) {
            double newBBase = bBase;
            double newABase = aBase / newBBase;

            double newBExponent = bExponent;
            double newAExponent = aExponent / newBExponent;

            return isLarger(newABase, newAExponent, newBBase, newBExponent);
        }

        return false;
    }

}
