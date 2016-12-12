package org.krynicki.euler.Problems1to50;

/**
 * Created by kamil.krynicki on 20/10/2016.
 */
public class CoinSums {
    /*
    In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:

    1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
    It is possible to make £2 in the following way:

    1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
    How many different ways can £2 be made using any number of coins?

     */

    int[] coins = {200, 100, 50, 20, 10, 5, 2, 1};

    public static void main(String[] args) {
        long t1 = System.nanoTime();
        CoinSums a = new CoinSums();
        System.out.println(a.countCompositionWays(200));
        long t2 = System.nanoTime();
        System.out.println(t2 - t1);
    }

    int[][] memo;

    int countCompositionWays(int pence) {
        memo = new int[pence + 1][coins.length];

        for (int i = 1; i < pence + 1; i++) {
            for (int j = 1; j < coins.length; j++) {
                memo[i][j] = -1;
            }
            memo[i][coins.length - 1] = 1;
        }

        memo[0][coins.length - 1] = 0;

        return recurseCount(0, pence);
    }

    int recurseCount(int coin, int pence) {
        if (memo[pence][coin] > 0) {
            return memo[pence][coin];
        }

        int ways = 0;
        int newValue = pence;
        do {
            ways += recurseCount(coin + 1, newValue);
        } while ((newValue -= coins[coin]) > 0);

        if (newValue == 0) {
            ways++;
        }

        memo[pence][coin] = ways;
        return ways;
    }
}
