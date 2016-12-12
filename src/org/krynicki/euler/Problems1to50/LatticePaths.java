package org.krynicki.euler.Problems1to50;

/**
 * Created by kamil.krynicki on 10/10/2016.
 */
public class LatticePaths {
    // Starting in the top left corner of a 2×2 grid, and only being able to move
    // to the right and down, there are exactly 6 routes to the bottom right corner.


    // solutions including binomial product are fine just for a lattice, this below is fine for any graph
    // IMHO more valuable than (2*x!)/2(y!)
    public static void main(String[] args) {
        LatticePaths l = new LatticePaths(20, 20);

        long t1 = System.currentTimeMillis();
        System.out.println(l.countPathsFrom(0, 0));
        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
    }

    private int sizeY;
    private int sizeX;
    private long[][] memo;

    public LatticePaths(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.memo = new long[sizeX + 1][sizeY + 1];
    }

    public long countPathsFrom(int x, int y) {
        if (memo[x][y] > 0) {
            return memo[x][y];
        }

        if (isGoal(x, y)) {
            return 1;
        }

        long result = 0;

        if (x < sizeX) {
            result += countPathsFrom(x + 1, y);
        }

        if (y < sizeY) {
            result += countPathsFrom(x, y + 1);
        }

        memo[x][y] = result;
        return result;
    }

    private boolean isGoal(int x, int y) {
        return x == sizeX && y == sizeY;
    }
}
