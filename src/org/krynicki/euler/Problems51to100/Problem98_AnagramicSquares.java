package org.krynicki.euler.Problems51to100;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
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

        Map<Integer, Map<String, List<String>>> textAnagrams = getAnagrams(Arrays.stream(fr.readLine().split(",")).map(t -> t.substring(1, t.length() - 1)).collect(Collectors.toList()));

        int max = Collections.max(textAnagrams.keySet())-1;

        Map<Integer, Map<String, List<String>>> numericalAnagrams = getAnagrams(getSquares(max));

        for(int i = max; i > 0; i--) {
           if(canTranslate(textAnagrams.get(i), numericalAnagrams.get(i)))
               break;
        }

        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
    }

    private static Set<String> getSquares(int max) {
        Set<String> squares = new HashSet<>();
        for (long j = 1; j * j < Math.pow(10, max); j++) {
            squares.add(String.valueOf(j*j));
        }
        return squares;
    }

    private static Map<Integer, Map<String, List<String>>> getAnagrams(Iterable<String> strings) throws IOException {
        Map<String, List<String>> anagrams = new HashMap<>();

        for (String s : strings) {
            String bucket = anagramBucket(s);
            if (!anagrams.containsKey(bucket)) {
                anagrams.put(bucket, new LinkedList<>());
            }
            anagrams.get(bucket).add(s);
        }

        return anagrams.entrySet()
                .stream()
                .filter(t -> t.getValue().size() > 1)
                .collect(Collectors
                        .groupingBy(
                                t -> t.getKey().length(),
                                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)
                        )
                );
    }

    private static String anagramBucket(String i) {
        char[] data = i.toCharArray();

        Arrays.sort(data);

        return String.valueOf(data);
    }

    private static boolean canTranslate(Map<String, List<String>> fromAnagrams, Map<String, List<String>> toAnagrams) {
        if(fromAnagrams == null || toAnagrams == null)
            return false;

        for(List<String> words : fromAnagrams.values()) {
            for(List<String> squares : toAnagrams.values()) {
                if(existsMatch(words, squares)) {
                    System.out.println(words);
                    System.out.println(squares);
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean existsMatch(List<String> words, List<String> numbers) {
        for (int j = 0; j < words.size() - 1; j++) {
            for (int i = 0; i < numbers.size() - 1; i++) {
                int[] map = map(words.get(j), numbers.get(i));

                if(!isValid(map))
                    break;

                for (int k = j + 1; k < words.size(); k++) {
                    if(numbers.contains(unmap(words.get(k), map))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean isValid(int[] map) {
        long length = IntStream.range(0, map.length).map(t -> map[t]).filter(t -> t >= 0).count();
        long distinct = IntStream.range(0, map.length).map(t -> map[t]).filter(t -> t >= 0).distinct().count();

        return length == distinct;
    }

    private static int[] map(String from, String to) {
        int[] result = new int['Z' - 'A' + 1];

        Arrays.fill(result, -1);

        for (int i = from.length() - 1; i >= 0; i--) {
            result[from.charAt(i) - 'A'] = to.charAt(i) - '0';
        }

        return result;
    }

    private static String unmap(String chars, int[] map) {
        long result = 0;

        for (int i = 0; i< chars.length(); i++) {
            result += map[chars.charAt(i) - 'A'];
            result *= 10;
        }

        result /= 10;

        return String.valueOf(result);
    }

}
