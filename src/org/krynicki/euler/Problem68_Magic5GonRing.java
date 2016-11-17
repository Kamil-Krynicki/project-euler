package org.krynicki.euler;

import org.krynicki.euler.LexicographicPermutations.PermutationsGenerator;

import java.util.*;

/**
 * Created by K on 2016-11-15.
 */
public class Problem68_Magic5GonRing {
    /*
    Consider the following "magic" 3-gon ring, filled with the numbers 1 to 6, and each line adding to nine.


    Working clockwise, and starting from the group of three with the numerically lowest external node
    (4,3,2 in this example), each solution can be described uniquely. For example,
    the above solution can be described by the set: 4,3,2; 6,2,1; 5,1,3.

    It is possible to complete the ring with four different totals: 9, 10, 11, and 12. There are eight solutions in total.

    Total	Solution Set
    9	4,2,3; 5,3,1; 6,1,2
    9	4,3,2; 6,2,1; 5,1,3
    10	2,3,5; 4,5,1; 6,1,3
    10	2,5,3; 6,3,1; 4,1,5
    11	1,4,6; 3,6,2; 5,2,4
    11	1,6,4; 5,4,2; 3,2,6
    12	1,5,6; 2,6,4; 3,4,5
    12	1,6,5; 3,5,4; 2,4,6
    By concatenating each group it is possible to form 9-digit strings; the maximum string for a 3-gon ring is 432621513.

    Using the numbers 1 to 10, and depending on arrangements, it is possible to form 16- and 17-digit strings.
    What is the maximum 16-digit string for a "magic" 5-gon ring?
    */

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        int[] components = {10, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        PermutationsGenerator generator = new PermutationsGenerator();

        Set<Long> result = new HashSet<>();
        do {
            if (isMagic(components)) {
                List<Integer> magic = magic(components);

                long val = 0;

                for (int num : magic) {
                    val *= Math.pow(10, String.valueOf(num).length());
                    val += num;
                }

                result.add(val);

                System.out.println(magic);
            }
            generator.next(components);
        } while (components[0] == 10);


        long t2 = System.currentTimeMillis();

        System.out.println("================");

        result.stream().sorted().forEach(System.out::println);

        System.out.println(t2 - t1);
    }

    private static List<Integer> magic(int[] components) {
        int[] sums = Arrays.copyOf(components, components.length / 2);

        for (int i = 0; i < sums.length; i++) {
            sums[i] *= 10;
            sums[i] += components[i + sums.length];
        }

        for (int i = 0; i < sums.length; i++) {
            sums[(i + sums.length - 1) % sums.length] *= 10;
            sums[(i + sums.length - 1) % sums.length] += components[i + sums.length];
        }

        return rotate(sums);

    }

    private static LinkedList<Integer> rotate(int[] sums) {
        LinkedList<Integer> result = new LinkedList<Integer>();

        for (int i : sums) {
            result.add(i);
        }

        int min = Collections.min(result);
        while (result.peek() != min) {
            Collections.rotate(result, 1);
        }
        return result;
    }

    private static boolean isMagic(int[] components) {
        int[] sums = Arrays.copyOf(components, components.length / 2);

        for (int i = 0; i < sums.length; i++) {
            sums[i] += components[i + sums.length];
            sums[(i + sums.length - 1) % sums.length] += components[i + sums.length];
        }

        for (int i = 1; i < sums.length; i++) {
            if (sums[i - 1] != sums[i]) return false;
        }

        return true;
    }
}
