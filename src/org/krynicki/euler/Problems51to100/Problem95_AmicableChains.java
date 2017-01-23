package org.krynicki.euler.Problems51to100;

import java.util.Arrays;

/**
 * Created by K on 2017-01-23.
 */
public class Problem95_AmicableChains {

    private static int max;
    private static int[] chains;

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

        max = (int) 1E4;
        chains = new int[max];
        chains[0] = -1;


        for(int i = 1210; i< max; i++){
            if(chains[i]==0) markChain(i);
        }

        System.out.println(Arrays.toString(chains));

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    private static void markChain(int start) {
        chains[start] = innerMarkChain(start, 1);
    }

    private static int innerMarkChain(int current, int depth) {
        if(chains[current] == 0) {
            chains[current] = depth;
            int next = sumOfDivisors(current);
            if (next <= max) {
                return (chains[current] = innerMarkChain(next, depth+1));
            } else {
                return (chains[current] = -1);
            }
        } else if(chains[current] > 0) {
            return depth + chains[current] - 1;
        } else {
            return -1;
        }
    }

    public static int sumOfDivisors(int val) {
        int div = 1;
        int result = 1;

        while(++div <= Math.sqrt(val)) {
            if (val % div == 0) {
                result += div;
                result += val / div;
            }
        }

        return result;
    }
}
