package org.krynicki.euler.Problems51to100;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by K on 03.04.2017.
 */

/*
Arranged probability
Problem 100
If a box contains twenty-one coloured discs, composed of fifteen blue discs and six red discs, and two discs were taken
at random, it can be seen that the probability of taking two blue discs, P(BB) = (15/21)×(14/20) = 1/2.

The next such arrangement, for which there is exactly 50% chance of taking two blue discs at random, is a box
containing eighty-five blue discs and thirty-five red discs.

By finding the first arrangement to contain over 1012 = 1,000,000,000,000 discs in total, determine the number of blue
discs that the box would contain.
*/


// WAY OUT OF RANGE OF LONG
public class Problem100_ArrangedProbability {

    public static void main(String... args) throws IOException {

        long t1 = System.currentTimeMillis();

        long i = (long) 10E12;
        double t = 0;
        while(true) {
            t = blues(i++);

            if(t == (int)t) {
                System.out.println(t);
                break;
            }

        }


        long t2= System.currentTimeMillis();

        System.out.println(t2 - t1);
    }

    static double blues(long total) {
        return (2 + 2*Math.sqrt(2*total*total-2*total + 1)) / 4;
    }



}
