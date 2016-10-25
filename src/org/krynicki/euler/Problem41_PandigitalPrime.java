package org.krynicki.euler;

import java.io.IOException;
import java.util.Arrays;

import static org.krynicki.euler.LexicographicPermutations.PermutationsGenerator;

/**
 * Created by kamil.krynicki on 25/10/2016.
 */
public class Problem41_PandigitalPrime {
    /*
    We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once.
    For example, 2143 is a 4-digit pandigital and is also prime.

    What is the largest n-digit pandigital prime that exists?
    */
    private static PermutationsGenerator n = new PermutationsGenerator();

    public static void main(String[] args) throws IOException {
        int[] nums = {7, 6, 5, 4, 3, 2, 1};

        int firstVal = n.convert(nums, 0, nums.length);
        int currVal;

        long t1 = System.nanoTime();
        do {
            n.prv(nums);
            currVal = n.convert(nums, 0, nums.length);
        } while (!isPrime(currVal) && currVal!=firstVal);
        long t2 = System.nanoTime();
        System.out.println(t2 - t1);
        System.out.println(Arrays.toString(nums));
        System.out.println(n.convert(nums, 0, nums.length));
    }

    public static boolean isPrime(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
