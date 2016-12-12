package org.krynicki.euler.Problems1to50;

import org.krynicki.euler.util.PermutationsGenerator;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by kamil.krynicki on 17/10/2016.
 */
public class LexicographicPermutations {
    /*A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the
    digits 1, 2, 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it
    lexicographic order. The lexicographic permutations of 0, 1 and 2 are:

    012   021   102   120   201   210

    What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
    */

    public static void main(String[] args) throws IOException {
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        PermutationsGenerator n = new PermutationsGenerator();

        long t1 = System.currentTimeMillis();
        for (int i = 1; i < 1000000; i++) {
            n.next(nums);
        }
        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
        System.out.println(Arrays.toString(nums));
    }

}
