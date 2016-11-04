package org.krynicki.euler;

import java.io.IOException;

/**
 * Created by kamil.krynicki on 04/11/2016.
 */
public class Problem53_CombinatoricSelections {
    // observation 1 - for r = n and r = 1 nCr = 1

    // observation 3 - (n+1)Cr = nCr * (1 + r/(n-r+1)) -> always grows
    // observation 2 - nC(r+1) = nCr * (n - r)/(r+1) -> it grows on n > 2r + 1
    /*
    There are exactly ten ways of selecting three from five, 12345:

    123, 124, 125, 134, 135, 145, 234, 235, 245, and 345

    In combinatorics, we use the notation, 5C3 = 10.

    In general,

    nCr =
    n!
    r!(n−r)!
    ,where r ≤ n, n! = n×(n−1)×...×3×2×1, and 0! = 1.
    It is not until n = 23, that a value exceeds one-million: 23C10 = 1144066.

    How many, not necessarily distinct, values of  nCr, for 1 ≤ n ≤ 100, are greater than one-million?
    */

    private static final int MAX_SUM = 1000000;
    private static final int MAX_N = 100;

    private static int[][] memo = new int[MAX_N+1][MAX_N+1];

    public static void main(String[] args) throws IOException {
        long t1 = System.nanoTime();

        int count = 0;

        for(int i=1;i<= MAX_N;i++) {
            memo[i][i] = 1;
        }

        for(int n = 2; n <=MAX_N; ++n) {
            for(int r = 1; r<n; ++r) {
                if (memo[n-1][r] >= MAX_SUM) {
                    count++;
                    memo[n][r] = MAX_SUM;
                } else {
                    memo[n][r] = (memo[n-1][r] * n)/(n-r);
                    if(memo[n][r] >= MAX_SUM) {
                        count++;
                    }
                }
            }
        }

        long t2 = System.nanoTime();

        System.out.println(count);

        System.out.println(t2 - t1);
    }

    static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
