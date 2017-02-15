package org.krynicki.euler.Problems51to100;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by kamil.krynicki on 14/02/2017.
 */

/*
By replacing each of the letters in the word CARE with 1, 2, 9, and 6 respectively, we form a square number: 1296 = 362.
What is remarkable is that, by using the same digital substitutions, the anagram, RACE, also forms a square number: 9216 = 962.
We shall call CARE (and RACE) a square anagram word pair and specify further that leading zeroes are not permitted, neither
may a different letter have the same digital value as another letter.

Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common English
words, find all the square anagram word pairs (a palindromic word is NOT considered to be an anagram of itself).

What is the largest square number formed by any member of such a pair?

NOTE: All anagrams formed must be contained in the given text file.
*/

public class Problem98_AnagramicSquares {
    public static void main(String... args) throws IOException {

        long t1 = System.currentTimeMillis();
        BufferedReader fr = new BufferedReader(new FileReader(new File(args[0])));

        Map<String, List<String>> anagrams2 = new HashMap<>();

        for (String s : fr.readLine().split(",")) {
            s = s.substring(1, s.length() - 1);
            String s2 = toBucket(s);
            if (!anagrams2.containsKey(s2)) {
                anagrams2.put(s2, new LinkedList<>());
            }
            anagrams2.get(s2).add(s);
        }

        //System.out.println(anagrams2);
        //System.out.println(anagrams2.size());
        Map<String, List<String>> collect2 = anagrams2.entrySet().stream()
                .filter(t -> t.getValue().size() > 1)
                .filter(t -> t.getKey().length() == 3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(collect2);


        Set<Long> squares = new HashSet<>();
        Set<String> patterns = new HashSet<>();
        Map<String, List<String>> anagrams = new HashMap<>();

        for (long i = 1, j = 1; j < 1E3; i++, j += 2 * i - 1) {
            String s = toBucket(j);
            if (!anagrams.containsKey(s)) {
                anagrams.put(s, new LinkedList<>());
            }
            anagrams.get(s).add(String.valueOf(j));
        }


        //System.out.println(anagrams);
        //System.out.println(anagrams.size());
        Map<String, List<String>> collect = anagrams.entrySet().stream()
                .filter(t -> t.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(collect);

        List<String> how = collect2.get("HOW");
        List<String> x = collect.get("256");

        for (int i = 0; i < how.size(); i++) {
            for (int j = 0; j < how.size(); j++) {
                System.out.print(how.get(i).toCharArray()[j] - x.get(i).toCharArray()[j] - 'A' - '0');
            }
            System.out.println();
        }

        System.out.println(how);
        System.out.println(x);

        //for(List<String> a : collect2.values()) {
        //    for(int i = 0; i<a.size(); i++) {
        //        for(Long val : squares) {
        //           int[] map = map(a.get(i).toCharArray(), val);
        //            for(int j = i + 1; j<a.size(); j++) {
        //                if(squares.contains(unmap(a.get(j).toCharArray(), map))) {
        //                    System.out.println(a.get(i));
        //                    System.out.println(a.get(j));
        //                    System.out.println(val);
        //                    System.out.println(unmap(a.get(j).toCharArray(), map));
        //                    System.out.println("================");
        //                }
        //            }
        //        }
        //    }
        //}


        long t2 = System.currentTimeMillis();


        System.out.println(t2 - t1);
    }

    public static int[] map(char[] chars, long vals) {
        int[] result = new int['Z' - 'A' + 1];

        for (int i = chars.length - 1; i >= 0; i--) {
            result[chars[i] - 'A'] = (int) (vals % 10);
            vals /= 10;
        }

        return result;
    }

    public static long unmap(char[] chars, int[] map) {
        long result = 0;

        for (char c : chars) {
            result += map[c - 'A'];
            result *= 10;
        }

        result /= 10;

        return result;
    }

    public static String toBucket(String i) {
        char[] data = i.toCharArray();

        Arrays.sort(data);

        return String.valueOf(data);
    }

    public static String toBucket(long i) {
        char[] data = String.valueOf(i).toCharArray();

        Arrays.sort(data);

        return String.valueOf(data);
    }
}
