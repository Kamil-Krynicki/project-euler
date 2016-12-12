package org.krynicki.euler.Problems51to100;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by K on 2016-11-23.
 */
public class Problem78_CoinSplits {
    /*
    Let p(n) represent the number of different ways in which n coins can be separated into piles. For example, five coins can be separated into piles in exactly seven different ways, so p(5)=7.

    OOOOO
    OOOO   O
    OOO   OO
    OOO   O   O
    OO   OO   O
    OO   O   O   O
    O   O   O   O   O
    Find the least value of n for which p(n) is divisible by one million.
    */

    static int[] memo;
    private static int divisible = (int) 1E6;

    public static void main(String[] args) {
        int max = 100000;
        memo = new int[max + 1];
        memo[0] = 1;
        memo[1] = 1;

        long t1 = System.currentTimeMillis();
        int i = 2;
        while (p(++i) != 0) ;
        System.out.println(i);

        long t2 = System.currentTimeMillis();

        System.out.println("==========");
        System.out.println(t2 - t1);
    }

    public static int p(int n) {
        if (memo[n] > 0) return memo[n];
        int k = 1;
        int g_k;
        int result = 0;

        while ((g_k = k * (3 * k - 1) / 2) <= n) {
            if (Math.abs(k) % 2 == 1) {
                result += p(n - g_k);
            } else {
                result -= p(n - g_k);
            }
            result += divisible;
            result %= divisible;
            k *= -1;
            if (k > 0) {
                k++;
            }
        }
        memo[n] = result;

        return result;

    }
}
