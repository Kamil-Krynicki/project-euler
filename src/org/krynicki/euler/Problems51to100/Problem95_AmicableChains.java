package org.krynicki.euler.Problems51to100;

import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by K on 2017-01-23.
 */
public class Problem95_AmicableChains {

    private static int max;
    private static int[] checked;
    private static int[] divisorSums;

    /*
            The proper divisors of a number are all the divisors excluding the number itself. For example, the proper divisors
            of 28 are 1, 2, 4, 7, and 14. As the sum of these divisors is equal to 28, we call it a perfect number.

            Interestingly the sum of the proper divisors of 220 is 284 and the sum of the proper divisors of 284 is 220, forming
            a chain of two numbers. For this reason, 220 and 284 are called an amicable pair.

            Perhaps less well known are longer chains. For example, starting with 12496, we form a chain of five numbers:

            12496 → 14288 → 15472 → 14536 → 14264 (→ 12496 → ...)

            Since this chain returns to its starting point, it is called an amicable chain.

            Find the smallest member of the longest amicable chain with no element exceeding one million.
             */
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        max = (int) 1E6;
        checked = new int[max];
        divisorSums = new int[max];

        Arrays.fill(divisorSums, 1);

        sieve();

        for (int i = 1; i < max; i++) {
            if (checked[i] == 0) {
                markChain(i);
            }
        }

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    private static void markChain(int start) {
        Deque<Integer> chain = new LinkedList<>();

        int next = start;

        while (checked[next]==0) {
            chain.push(next);
            checked[next] = start;
            next = divisorSums[next];
            if (next >= max) return;
        }

        if(checked[next] == start) {
           chain.push(next);
           while(!chain.peekFirst().equals(chain.pollLast()));
           System.out.println(Collections.min(chain) + " : " + (chain.size() - 1));
        }
    }

    // stole this one. It's good
    private static void sieve() {
        for(int n = 2; n < divisorSums.length; n++) {
            if(divisorSums[n] != 1) {divisorSums[n] -= n; continue;}
            for(int m = n + n; m < divisorSums.length; m += n) {
                int pow = n, factor = 1;
                while(m % pow == 0) {factor += pow; pow *= n;}
                divisorSums[m] *= factor;
            }
        }
    }
}
