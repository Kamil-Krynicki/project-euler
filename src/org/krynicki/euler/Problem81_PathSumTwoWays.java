package org.krynicki.euler;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by K on 2016-11-27.
 */
public class Problem81_PathSumTwoWays {


    static int[][] matrix;
    static int[][] memo;
    private static int size;

    public static void main(String[] args) throws IOException {

        long t1 = System.currentTimeMillis();

        matrix = load(args[0]);

        //matrix = new int[][]{{131, 673, 234, 103, 18},
        //{201, 96, 342, 965, 150},
        //{630, 803, 746, 422, 111},
        //{537, 699, 497, 121, 956},
        //{805, 732, 524, 37, 331}};
        //size = 5;

        printMatrix(matrix);
        System.out.println(solve());
        printMatrix(memo);

        long t2 = System.currentTimeMillis();
        System.out.print(t2-t1);

    }

    private static int solve() {
        memo = new int[size][size];

        return findMinFrom(0, 0);
    }

    private static int findMinFrom(int x, int y) {
        if(x == size - 1 && y == size - 1) {
            return matrix[x][y];
        }

        if(memo[x][y] > 0) {
            return memo[x][y];
        }


        int result = Integer.MAX_VALUE;

        if(x < size - 1) {
            result = Math.min(result, findMinFrom(x + 1, y));
        }

        if(y < size - 1) {
            result = Math.min(result, findMinFrom(x, y + 1));
        }

        result += matrix[x][y];

        memo[x][y] = result;

        return result;
    }

    static void printMatrix(int[][] matrix) {
        for(int[] row: matrix) {
            System.out.println(Arrays.toString(row));
        }
    }


    static int[][] load(String path) throws IOException {
        int[][] result;
        BufferedReader reader = new BufferedReader(new FileReader(new File(path)));

        String line = reader.readLine();
        size = line.split(",").length;
        result = new int[size][size];

        int tmpY = 0;
        int tmpX = 0;
            do {
                tmpX = 0;
                for (String value : line.split(",")) {
                    result[tmpY][tmpX++] = Integer.parseInt(value);
                }
                tmpY++;
            } while ((line = reader.readLine()) != null);

        return result;
    }
}
