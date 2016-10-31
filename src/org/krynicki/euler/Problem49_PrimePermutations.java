package org.krynicki.euler;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import static org.krynicki.euler.QuadraticPrimes.PrimeGenerator;

/**
 * Created by kamil.krynicki on 31/10/2016.
 */
public class Problem49_PrimePermutations {
    /*
    The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330,
    is unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit
    numbers are permutations of one another.

    There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property,
    but there is one other 4-digit increasing sequence.

    What 12-digit number do you form by concatenating the three terms in this sequence?
    */

    static PrimeGenerator generator = PrimeGenerator.getPrimesBelow(10000);

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        TreeSet<Integer> primes = (TreeSet<Integer>) generator.getPrimes();

        Map<String, List<Integer>> map = new LinkedHashMap<>();

        String hash;
        for (int i : primes.tailSet(primes.higher(999))) {
            hash = hash(i);

            if (!map.containsKey(hash)) {
                map.put(hash, new LinkedList<>());
            }

            map.get(hash).add(i);
        }

        List<Integer> integers;
        for (String entry : map.keySet()) {
            integers = map.get(entry);
            if(integers.size()>2) {
                checkArithmetic(integers);
//                System.out.println(entry + " with: " + integers);
            }
        }

        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);

    }

    static boolean checkArithmetic(List<Integer> list) {
        int[] vals = new int[list.size()-1];
        for(int i = 0; i<list.size()-1 ; i++) {
            vals[i] = list.get(i+1) - list.get(i);
        }

        for(int i = 1; i<vals.length ; i++) {
            if(vals[i]==vals[i-1]) {
                System.out.println(Arrays.toString(vals));
            }
        }

        return false;
    }

    static String hash(int i) {
        char[] result = Integer.toString(i).toCharArray();
        Arrays.sort(result);
        return String.copyValueOf(result);
    }

    class ArithmeticChecker {
        public boolean isArithmetic(int[] list) {
            for(int i=0;i<list.length;i++) {
                for(int j=i+1;i<list.length;j++) {
                    int interval = list[j] - list[i];
                    checkInterval(list, j+1, interval);
                }
            }
        }
    }
}
