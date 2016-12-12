package org.krynicki.euler.Problems1to50;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.krynicki.euler.util.PrimeGenerator;

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
            }
        }

        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);

    }

    static void checkArithmetic(List<Integer> list) {
        int q;
        for(int a1=0;a1<list.size();a1++) {
            for(int a2=a1+1;a2<list.size();a2++) {
                for(int a3=a2+1;a3<list.size();a3++) {
                    q = list.get(a2) - list.get(a1);
                    if(list.get(a3)-list.get(a2)== q) {
                        System.out.println("a1:"+list.get(a1) +", a2:"+list.get(a2)+", a3:"+list.get(a3));
                    }
                }
            }
        }
    }

    static String hash(int i) {
        char[] result = Integer.toString(i).toCharArray();
        Arrays.sort(result);
        return String.copyValueOf(result);
    }
}
