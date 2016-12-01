package org.krynicki.euler;

import org.krynicki.euler.util.PrimeGenerator;

import java.io.IOException;
import java.util.*;

/**
 * Created by K on 2016-12-01.
 */
public class Problem87_PrimePowerTriples {
    /*
    The smallest number expressible as the sum of a prime square, prime cube, and prime fourth power is 28. In fact,
    there are exactly four numbers below fifty that can be expressed in such a way:

    28 = 22 + 23 + 24
    33 = 32 + 23 + 24
    49 = 52 + 23 + 24
    47 = 22 + 33 + 24

    How many numbers below fifty million can be expressed as the sum of a prime square, prime cube, and prime fourth power?
    */

    static Set<Integer> count;
    static int max;
    static PrimeGenerator generator;

    public static void main(String[] args) throws IOException {
        max = (int)5E7;

        long t1 = System.currentTimeMillis();

        generator = PrimeGenerator.getPrimesBelow((int) Math.sqrt(max));

        List<Set<Integer>> powers = new ArrayList<>();

        count = new HashSet<>();

        solve(4, 0);

        System.out.println(count.size());



        long t2 = System.currentTimeMillis();
        System.out.print(t2 - t1);
    }

    static void solve(int pow, int currentSum) {

        if(pow > 2) {
            for(int prime : generator.getPrimes()) {
                int nextSum = (int) (currentSum + Math.pow(prime, pow));
                if(nextSum < max) {
                    solve(pow - 1, nextSum);
                }
            }
        } else if(pow == 2) {
            for(int prime : generator.getPrimes()) {
                int nextSum = currentSum + prime * prime;
                if(nextSum < max) {
                    count.add(nextSum);
                } else {
                    return;
                }
            }
        }
    }


}
