package org.krynicki.euler;

import org.krynicki.euler.util.PermutationsGenerator;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by K on 2016-10-26.
 */
public class Problem43_SubstringDivisibility {
    /*
    The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, but it also has a rather interesting sub-string divisibility property.

    Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:

    d2d3d4=406 is divisible by 2
    d3d4d5=063 is divisible by 3
    d4d5d6=635 is divisible by 5
    d5d6d7=357 is divisible by 7
    d6d7d8=572 is divisible by 11
    d7d8d9=728 is divisible by 13
    d8d9d10=289 is divisible by 17
    Find the sum of all 0 to 9 pandigital numbers with this property.
    */

    static int[] divisors = {2,3,5,7,11,13,17};

    public static void main(String[] args) throws IOException {
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] startNum = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        PermutationsGenerator n = new PermutationsGenerator();


        long sum = 0;
        long t1 = System.currentTimeMillis();
        do {
            if(check(nums)) {
                sum+=convert(nums, 0, nums.length);
            }
            n.next(nums);
        } while( !Arrays.equals(nums, startNum));
        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
        System.out.println(sum);
    }

    public static long convert(int[] values, int start, int len) {
        long result = 0;
        for (int i = start; i < start + len && i < values.length; i++) {
            result *= 10;
            result += values[i];
        }
        return result;
    }

    private static boolean check(int[] nums) {
        int rollingResult = 100*nums[0]+10*nums[1]+nums[2];
        for(int i=3;i<=9;i++) {
            rollingResult-=100*nums[i-3];
            rollingResult*=10;
            rollingResult+=nums[i];

            if(rollingResult%divisors[i-3]!=0) {
                return false;
            }
        }
        return true;
    }
}
