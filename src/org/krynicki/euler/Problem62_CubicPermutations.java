package org.krynicki.euler;

import org.krynicki.euler.Problem45_TrianglePentagonHexagon.Generator;

import javax.persistence.GeneratedValue;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by K on 2016-11-10.
 */
public class Problem62_CubicPermutations {
    /*
    The cube, 41063625 (3453), can be permuted to produce two other cubes: 56623104 (3843) and 66430125 (4053).
    In fact, 41063625 is the smallest cube which has exactly three permutations of its digits which are also cube.

    Find the smallest cube for which exactly five permutations of its digits are cube.
    */

    public static void main(String[] args) {
        PriorityQueue<Generator> queue = new PriorityQueue<>();

        final long t1 = System.currentTimeMillis();

        System.out.println(find().stream().sorted().collect(Collectors.toList()));

        final long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
    }

    static List<Long> find() {
        Map<String, List<Long>> values = new HashMap<>();

        long number;
        String numberHash;
        List<Long> currentList;

        long i = 1;

        while(true) {
            number = i*i*i;
            numberHash = hash(number);

            currentList = values.get(numberHash);

            if(currentList == null) {
                currentList = new ArrayList<>(10);
                values.put(numberHash, currentList);
            }

            currentList.add(number);

            if(currentList.size() == 5) {
                return currentList;
            }
            i++;
        }
    }

    static String hash(long i) {
        int[] digitCount = new int[10];

        char[] chars = String.valueOf(i).toCharArray();
        for(char digit: chars) {
            digitCount[digit - 0x30]++;
        }

        return digitsToString(digitCount);
    }

    private static String digitsToString(int[] digitCounts) {
        StringBuilder builder = new StringBuilder(digitCounts.length);

        for(int count: digitCounts) {
            builder.append(count);
        }

        return builder.toString();
    }
}
