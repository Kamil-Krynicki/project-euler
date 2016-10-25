package org.krynicki.euler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.krynicki.euler.LexicographicPermutations.PermutationsGenerator;

/**
 * Created by K on 2016-10-20.
 */
public class PandigitalProducts {
    /*
    We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example, the 5-digit number, 15234, is 1 through 5 pandigital.

    The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.

    Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.

    HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
     */

    // observation 1: the multiplicand and multiplier must be 3 and 2 digits long or 4 and 1 digits long.
    // In both cases the product is 4 digits long

    // observation 2: due to symmetry I only have to check the mentioned combinations and no other (i.e. no 1 and 4, for instance)
    // this results in 9x9999 + 99x999 tests, which is only <20 000
    private static PermutationsGenerator permGen = new PermutationsGenerator();
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] init = Arrays.copyOf(nums, 9);

        Set<Integer> result = new HashSet<>();

        long t1 = System.currentTimeMillis();
        do {
            result.add(check(nums, 1, 5));
            result.add(check(nums, 2, 5));
            permGen.next(nums);
        } while (!Arrays.equals(nums, init));

        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);

        System.out.println(result);
        System.out.println(result.stream().reduce(Integer::sum));
    }

    private static int check(int[] nums, int splitA, int splitB) {
        int val1 = permGen.convert(nums, 0, splitA);
        int val2 = permGen.convert(nums, splitA, splitB - splitA);
        int product = permGen.convert(nums, splitB, nums.length - splitB);

        if (val1 * val2 == product) {
            System.out.println(val1 + "x" + val2 + "=" + product);
            return product;
        }
        return 0;
    }
}
