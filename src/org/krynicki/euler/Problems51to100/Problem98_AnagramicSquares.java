package org.krynicki.euler.Problems51to100;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        Map<String, List<String>> anagrams = new HashMap<>();

        for (String s : fr.readLine().split(",")) {
            s = s.substring(1, s.length() - 1);
            String s2 = toBucket(s);
            if (!anagrams.containsKey(s2)) {
                anagrams.put(s2, new LinkedList<>());
            }
            anagrams.get(s2).add(s);
        }

        Map<Integer, Map<String, List<String>>> collectA = anagrams.entrySet()
                .stream()
                .filter(t -> t.getValue().size() > 1)
                .collect(Collectors.groupingBy(t -> t.getKey().length(), Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

        int max = collectA.keySet().stream().max(Comparator.<Integer>naturalOrder()).get() - 1;

        Map<String, List<String>> patterns = new HashMap<>();
        for (long j = 1; j * j < Math.pow(10, max); j++) {
            String s = toBucket(j * j);
            if (!patterns.containsKey(s)) {
                patterns.put(s, new LinkedList<>());
            }
            patterns.get(s).add(String.valueOf(j * j));
        }

        Map<Integer, Map<String, List<String>>> collectB = patterns.entrySet()
                .stream()
                .filter(t -> t.getValue().size() > 1)
                .collect(Collectors.groupingBy(t -> t.getKey().length(), Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));


        for(int i = max; i > 0; i--) {
           if(checkAnagrams(collectA.get(i), collectB.get(i))) break;
        }


        long t2 = System.currentTimeMillis();


        System.out.println(t2 - t1);
    }

    private static boolean checkAnagrams(Map<String, List<String>> anagrams, Map<String, List<String>> patterns) {
        if(anagrams == null || patterns == null)
            return false;

        for(List<String> words : anagrams.values()) {
            for(List<String> squares : patterns.values()) {
                if(existsMatch(words, squares)) {
                    System.out.println(words);
                    System.out.println(squares);
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean existsMatch(List<String> words, List<String> numbers) {
        for (int j = 0; j < words.size() - 1; j++) {
            for (int i = 0; i < numbers.size() - 1; i++) {
                int[] map = map(words.get(j).toCharArray(), numbers.get(i).toCharArray());

                if(!isValid(map)) break;

                for (int k = j + 1; k < words.size(); k++) {
                    String word3 = unmap(words.get(k).toCharArray(), map);
                    if(numbers.contains(word3)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean isValid(int[] map) {
        long sizeA = IntStream.range(0, map.length).map(t -> map[t]).filter(t -> t >= 0).count();
        long sizeB = IntStream.range(0, map.length).map(t -> map[t]).filter(t -> t >= 0).distinct().count();

        return sizeA == sizeB;
    }

    public static int[] map(char[] chars, char[] vals) {
        int[] result = new int['Z' - 'A' + 1];

        Arrays.fill(result, -1);

        for (int i = chars.length - 1; i >= 0; i--) {
            result[chars[i] - 'A'] = vals[i] - '0';
        }

        return result;
    }

    public static String unmap(char[] chars, int[] map) {
        long result = 0;

        for (char c : chars) {
            result += map[c - 'A'];
            result *= 10;
        }

        result /= 10;

        return String.valueOf(result);
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
