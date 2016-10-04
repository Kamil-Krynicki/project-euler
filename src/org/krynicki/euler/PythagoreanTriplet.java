package org.krynicki.euler;

/**
 * Created by kamil.krynicki on 03/10/2016.
 */
public class PythagoreanTriplet {
    /*
    * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
    *
    * a2 + b2 = c2
    * For example, 32 + 42 = 9 + 16 = 25 = 52.
    *
    * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
    * Find the product abc.
    */

    private int a, b, c;

    private long product;

    public PythagoreanTriplet(int sum) {
        int sumSq = sum * sum;

        int i = sum / 2 + 1;
        while (sumSq % (2 * i) != 0 && i < sum) {
            i++;
        }

        a = sum - sumSq / (2 * i);
        b = sum - i;
        c = (int) Math.sqrt(a * a + b * b);

        product = a * b * c;
    }


    public static void main(String[] args) {
        PythagoreanTriplet t = new PythagoreanTriplet(9);

        System.out.println(t.get());
    }

    private long get() {
        return this.product;
    }
}
