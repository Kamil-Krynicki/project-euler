package org.krynicki.euler;

import java.io.IOException;
import java.util.Arrays;

import org.krynicki.euler.util.PermutationsGenerator;

/**
 * Created by kamil.krynicki on 24/10/2016.
 */
public class Problem38_PandigitaMultiples {
    /*
    Take the number 192 and multiply it by each of 1, 2, and 3:

    192 × 1 = 192
    192 × 2 = 384
    192 × 3 = 576
    By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the concatenated product of 192 and (1,2,3)

    The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital, 918273645, which is the concatenated product of 9 and (1,2,3,4,5).

    What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n > 1?
    */
    private static PermutationsGenerator n = new PermutationsGenerator();
    public static void main(String[] args) throws IOException {
        int[] nums = {9, 8, 7, 6, 5, 4, 3, 2, 1};

        long t1 = System.currentTimeMillis();
        int k=0;
        while (!decomposesToConcatenation(nums, 3*k+1)) {
            n.prv(nums);
            k = ++k%2;
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

        System.out.println(Arrays.toString(nums));
    }

    private static boolean decomposesToConcatenation(int[] nums, int firstElement) {
        int val = n.convert(nums, 0, firstElement);
        int len = len(val);
        int currentStep = len;
        do {
            val += val;
            len = len(val);
            if ((val != n.convert(nums, currentStep, len))) {
                return false;
            }
            currentStep += len;
        } while (currentStep < nums.length);

        return true;
    }

    private static int len(int val) {
        return (int) (Math.log10(val) + 1);
    }

}
