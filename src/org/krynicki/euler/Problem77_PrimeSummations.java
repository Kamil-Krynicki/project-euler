package org.krynicki.euler;

import org.krynicki.euler.util.PrimeGenerator;

import java.util.Arrays;

/**
 * Created by K on 2016-11-23.
 */
public class Problem77_PrimeSummations {
    /*
    It is possible to write ten as the sum of primes in exactly five different ways:

    7 + 3
    5 + 5
    5 + 3 + 2
    3 + 3 + 2 + 2
    2 + 2 + 2 + 2 + 2

    What is the first value which can be written as the sum of primes in over five thousand different ways?
    */

    static int[][] memo;
    static PrimeGenerator generator = PrimeGenerator.getPrimesBelow(30);

    public static void main(String[] args) {
        int max = 5000;

        memo = new int[100][100];

        long t1 = System.currentTimeMillis();
        int i = 10;

        while(sumCombinations(i++) < max);

        long t2 = System.currentTimeMillis();

        System.out.println(i - 1);
        System.out.println("==========");
        System.out.println(t2 - t1);
    }

    public static int sumCombinations(int i){
        return L(i, i);
    }

    public static int L(int n, int p) {
        if(n == 1 || n < 0) return 0;
        if(n == 0) return 1;
        if(p == 2) return n%2==0?1:0;

        if(memo[n][p] > 0) {
            return memo[n][p];
        }

        int result = 0;
        int i = (p>n?n:p) + 1;

        do {
            i = generator.prvPrime(i);
            result += L(n-i, i);
        } while(i > 2);

        memo[n][p] = result;

        return result;
    }
}
