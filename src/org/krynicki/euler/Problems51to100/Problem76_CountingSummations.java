package org.krynicki.euler.Problems51to100;

import java.util.Arrays;

/**
 * Created by K on 2016-11-23.
 */
public class Problem76_CountingSummations {
    /*
    It is possible to write five as a sum in exactly six different ways:

    4 + 1
    3 + 2
    3 + 1 + 1
    2 + 2 + 1
    2 + 1 + 1 + 1
    1 + 1 + 1 + 1 + 1

    How many different ways can one hundred be written as a sum of at least two positive integers?
    */

    static int[][] memo;

    public static void main(String[] args) {
        int max = 100;

        memo = new int[max + 1][max + 1];

        long t1 = System.currentTimeMillis();

        System.out.println(sumCombinations(100));

        long t2 = System.currentTimeMillis();

        System.out.println("==========");
        System.out.println(t2 - t1);
    }

    public static int sumCombinations(int i){
        return L(i, i)-1;
    }

    public static int L(int n, int p) {
        if(n == 0) return 1;
        if(p == 1) return 1;

        if(memo[n][p] > 0) {
            return memo[n][p];
        }

        int result = 0;
        for(int i = p>n?n:p; i >= 1;i--) {
            result += L(n-i, i);
        }

        memo[n][p] = result;

        return result;
    }
}
