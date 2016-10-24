package org.krynicki.euler;

import java.io.IOException;
import java.util.Arrays;

import static org.krynicki.euler.LexicographicPermutations.PermutationsGenerator;

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

    public static void main(String[] args) throws IOException {
        int[] nums = {9, 1, 8, 2, 7, 5, 6, 7, 8, 9};

        PermutationsGenerator n = new PermutationsGenerator();

        long t1 = System.currentTimeMillis();
        for (int i = 1; i < 100; i++) {
            dupa(nums, 1);
            n.prv(nums);
        }
        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
        System.out.println(Arrays.toString(nums));
    }

    private int[] pows = {1, 10, 100, 1000, 10000, 100000, 1000000};

    private static int dupa(int[] nums, int firstEnd) {
        int val = convert(nums, 0, firstEnd);

        int i = 2;
        int len;
        int currentStep = (int) (Math.log10(val) + 1);
        do {
            len = (int) (Math.log10(i * val) + 1);
            i++;
            currentStep += len;
        } while ((convert(nums, currentStep, len) == i * val) && (currentStep < nums.length));

        System.out.println(check(nums, val, 0));

        return 0;
    }

    public static int convert(int[] values, int start, int len) {
        int result = 0;
        for (int i = start; i < start + len && i < values.length; i++) {
            result *= 10;
            result += values[i];
        }
        return result;
    }

    public static boolean check(int[] values, int value, int start) {
        char[] digitArray = Integer.toString(value).toCharArray();
        for (int i = start; i < digitArray.length && i < values.length; i++) {
            if ((digitArray[i] - 0x30) != values[i]) {
                return false;
            }
        }
        return true;
    }


}
