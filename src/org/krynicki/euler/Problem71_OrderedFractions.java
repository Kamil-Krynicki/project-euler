package org.krynicki.euler;

import org.krynicki.euler.util.ContinuedFraction;

/**
 * Created by K on 2016-11-17.
 */
public class Problem71_OrderedFractions {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        ContinuedFraction kamil = new ContinuedFraction(1,2,3,2);

        System.out.println(kamil);
        System.out.println(kamil.value());
        System.out.println(kamil.leftChild());
        System.out.println(kamil.leftChild().value());
        System.out.println(kamil.rightChild());
        System.out.println(kamil.rightChild().value());
        System.out.println(kamil.parent());
        System.out.println(kamil.parent().value());
        System.out.println(kamil.leftChild().parent());
        System.out.println(kamil.leftChild().parent().value());
        System.out.println(kamil.rightChild().parent());
        System.out.println(kamil.rightChild().parent().value());

        ContinuedFraction kamil2 = new ContinuedFraction(0, 2,3);

        System.out.println(kamil2);
        System.out.println(kamil2.value());
        System.out.println(kamil2.parent().value());
        System.out.println(kamil2.parent().rightChild().value());
        System.out.println(kamil2.parent().leftChild().value());
        System.out.println(kamil2.value().toDouble());
        System.out.println(kamil2.parent().value().toDouble());
        System.out.println(kamil2.parent().rightChild().value().toDouble());
        System.out.println(kamil2.parent().leftChild().value().toDouble());

        long t2 = System.currentTimeMillis();

        System.out.println("================");
        //System.out.println(Arrays.toString(primes));

        System.out.println(t2 - t1);
    }
}

