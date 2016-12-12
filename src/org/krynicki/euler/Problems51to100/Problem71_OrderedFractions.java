package org.krynicki.euler.Problems51to100;

import org.krynicki.euler.util.ContinuedFraction;

/**
 * Created by K on 2016-11-17.
 */
public class Problem71_OrderedFractions {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        ContinuedFraction cFraction = new ContinuedFraction(0, 2, 3); // exactly 3/7

        cFraction = cFraction.left(); // smaller

        while (cFraction.value().den() <= 1E6) {
            cFraction = cFraction.right(); // bigger
        }
        cFraction = cFraction.parent();

        ContinuedFraction.Fraction pFraction = cFraction.value();

        long t2 = System.currentTimeMillis();

        System.out.println("Result");
        System.out.println(cFraction);
        System.out.println(pFraction);
        System.out.println(pFraction.toDouble());

        System.out.println("================");
        System.out.println(t2 - t1);
    }
}

