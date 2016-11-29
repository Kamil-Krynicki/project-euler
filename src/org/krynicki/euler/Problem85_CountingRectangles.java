package org.krynicki.euler;

import java.util.TreeSet;

/**
 * Created by kamil.krynicki on 29/11/2016.
 */
public class Problem85_CountingRectangles {
    // observation - I had this idea that the count of rectangles in a grid of p x q: C(p, q) = C(p - 1, q) + pC(1, q)

    // it was about 20 mins of math, so I'll upload photos
    /*
    By counting carefully it can be seen that a rectangular grid measuring 3 by 2 contains eighteen rectangles:

    Although there exists no rectangular grid that contains exactly two million rectangles, find the area of the grid with the nearest solution.
    */

    public static final int OBJECTIVE = 2000000;

    static int[][] memo;

    public static void main(String[] args) {
        int size = 3000; // an assumption
        memo = new int[size][size];
        memo[1][1] = 1;

        long t1 = System.currentTimeMillis();

        int i = 1, j = 1;
        TreeSet<Grid> results = new TreeSet<>();

        int count;
        while(i <= j) {
            while ((count = get(i, j++)) < OBJECTIVE);
            results.add(new Grid(i, j, count));

            i++;

            while ((count = get(i, j--)) > OBJECTIVE);
            results.add(new Grid(i, j, count));
        }

        long t2 = System.currentTimeMillis();

        System.out.println(results.first().area());
        System.out.println(results.first());
        System.out.println("==========");
        System.out.println(t2 - t1);
    }

    private static int get(int i, int j) {
        if (i == 0 || j == 0) {
            return 0;
        }

        if (memo[i][j] == 0) {
            int min = Math.min(i, j);
            int max = Math.max(i, j);

            memo[i][j] = get(max - 1, min) + max * get(1, min);
            memo[j][i] = memo[i][j];
        }

        return memo[i][j];
    }

    private static class Grid implements Comparable<Grid> {
        private final int x;
        private final int y;
        private final int count;

        private Grid(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        public int x() {
            return x;
        }

        public int y() {
            return y;
        }

        public int count() {
            return count;
        }

        public int area() {
            return x*y;
        }

        public int objective() {
            return Math.abs(count - OBJECTIVE);
        }

        @Override
        public int compareTo(Grid that) {
            return this.objective() - that.objective();
        }

        @Override
        public String toString() {
            return "Grid{" +
                    "x=" + x +
                    ", y=" + y +
                    ", count=" + count +
                    '}';
        }
    }
}
