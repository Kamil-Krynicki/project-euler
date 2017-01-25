package org.krynicki.euler.Problems51to100;

import com.google.common.base.Preconditions;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Created by kamil.krynicki on 25/01/2017.
 */

/*
"003020600,900305001,001806400,008102900,700000008,006708200,002609500,800203009,005010300"
 */
public class Problem96_Sudoku {


    static String sudokuSetup = "003020600,900305001,001806400,008102900,700000008,006708200,002609500,800203009,005010300";

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        SudokuModel m = new SudokuModel(9, 3);

        int x = 0;
        for (String row : sudokuSetup.split(",")) {
            int y = 0;
            for (char c : row.toCharArray()) {
                int value = c - '0';
                if (value > 0) m.put(value, x, y);
                y++;
            }
            x++;
        }

        System.out.println(m);
        m.solve();
        System.out.println(m);

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    static class SudokuModel {
        private final Set<SudokuLayer> layers;
        private final int[][] board;

        private int emptySpaces;

        public SudokuModel(int size, int blockSize) {
            Preconditions.checkArgument(size > 0);
            Preconditions.checkArgument(blockSize > 0);
            Preconditions.checkArgument(blockSize < size);

            this.board = new int[size][size];
            this.emptySpaces = size * size;

            this.layers = new HashSet<>();

            for (int i = 1; i < 10; i++)
                this.layers.add(new SudokuLayer(size, blockSize, i));
        }

        public void put(int value, int atX, int atY) {
            Preconditions.checkArgument(atX >= 0);
            Preconditions.checkArgument(atY >= 0);

            Preconditions.checkArgument(atX < board.length);
            Preconditions.checkArgument(atY < board[0].length);

            for (SudokuLayer layer : layers) {
                if (layer.symbol() != value)
                    layer.occupy(atX, atY);
                else
                    layer.put(atX, atY);
            }

            board[atX][atY] = value;
            emptySpaces--;
        }

        public boolean isSolved() {
            return emptySpaces == 0;
        }

        public void solve() {
            Coords coords = null;

            do {
                for (SudokuLayer layer : layers) {
                    coords = layer.nextStep();
                    if (coords != null) {
                        put(layer.symbol(), coords.x, coords.y);
                        break;
                    }
                }
            } while (!isSolved() && coords != null);

            if (coords == null) {
                System.out.print("Unable to make progress from:");
                System.out.print(this);
            }
        }

        @Override
        public String toString() {
            StringBuilder b = new StringBuilder();

            for (int[] boardRow : board) {
                for (int field : boardRow) {
                    b.append('|');
                    if (field == 0) b.append(' ');
                    else b.append(field);
                }
                b.append("|\n");
            }

            return b.toString();
        }
    }

    static class SudokuLayer {
        private final int symbol;
        private final boolean[][] occupied;
        private final int size;
        private final int blockSize;

        SudokuLayer(int size, int blockSize, int symbol) {
            Preconditions.checkArgument(size > 0);
            Preconditions.checkArgument(blockSize > 0);
            Preconditions.checkArgument(blockSize < size);

            this.symbol = symbol;
            this.occupied = new boolean[size][size];
            this.size = size;
            this.blockSize = blockSize;
        }

        public void put(int x, int y) {
            Preconditions.checkArgument(x < size);
            Preconditions.checkArgument(y < size);
            Preconditions.checkArgument(x >= 0);
            Preconditions.checkArgument(y >= 0);

            Preconditions.checkArgument(isUnoccupied(x, y));

            markColumn(x);
            markRow(y);
            markBlock(x, y);
        }

        public int symbol() {
            return symbol;
        }

        public void occupy(int x, int y) {
            Preconditions.checkArgument(x < size);
            Preconditions.checkArgument(y < size);
            Preconditions.checkArgument(x >= 0);
            Preconditions.checkArgument(y >= 0);

            occupied[x][y] = true;
        }

        public Coords nextStep() {
            for (int x = 0; x < size; x++)
                if (hasUniquePlacementColumn(x))
                    return getPlaceInColumn(x);

            for (int y = 0; y < size; y++)
                if (hasUniquePlacementRow(y))
                    return getPlaceInRow(y);

            for (int y = 0; y < size; y += blockSize)
                for (int x = 0; x < size; x += blockSize)
                    if (hasUniquePlacementInBlock(x, y))
                        return canPlaceUniquelyInBlock(x, y);

            return null;
        }

        public boolean hasUniquePlacementRow(int y) {
            return IntStream.range(0, size).filter(t -> isUnoccupied(t, y)).count() == 1;
        }

        private Coords getPlaceInRow(int y) {
            return new Coords(IntStream.range(0, size).filter(t -> isUnoccupied(t, y)).findFirst().getAsInt(), y);
        }

        public boolean hasUniquePlacementColumn(int x) {
            return IntStream.range(0, size).filter(t -> isUnoccupied(x, t)).count() == 1;
        }

        private Coords getPlaceInColumn(int x) {
            return new Coords(x, IntStream.range(0, size).filter(t -> isUnoccupied(x, t)).findFirst().getAsInt());
        }

        private boolean isUnoccupied(int x, int y) {
            return !occupied[x][y];
        }

        public boolean hasUniquePlacementInBlock(int x, int y) {
            int xOffset = x / (size / blockSize) * blockSize;
            int yOffset = y / (size / blockSize) * blockSize;

            return IntStream.range(xOffset, xOffset + blockSize)
                    .flatMap(t -> IntStream.range(yOffset, yOffset + blockSize)
                            .filter(u -> isUnoccupied(t, u))).count() == 1;
        }

        private Coords canPlaceUniquelyInBlock(int x, int y) {
            int xOffset = x / (size / blockSize) * blockSize;
            int yOffset = y / (size / blockSize) * blockSize;

            return IntStream.range(xOffset, xOffset + blockSize)
                    .boxed()
                    .flatMap(t -> IntStream.range(yOffset, yOffset + blockSize)
                            .filter(u -> isUnoccupied(t, u))
                            .mapToObj(u -> new Coords(t, u)))
                    .findAny()
                    .get();
        }

        private void markRow(int y) {
            IntStream.range(0, size).forEach(t -> occupy(t, y));
        }

        private void markColumn(int x) {
            IntStream.range(0, size).forEach(t -> occupy(x, t));
        }

        private void markBlock(int x, int y) {
            int xOffset = x / (size / blockSize) * blockSize;
            int yOffset = y / (size / blockSize) * blockSize;

            IntStream.range(xOffset, xOffset + blockSize)
                    .forEach(t -> IntStream.range(yOffset, yOffset + blockSize)
                            .forEach(u -> occupy(t, u)));
        }

        @Override
        public String toString() {
            StringBuilder b = new StringBuilder();
            for (boolean[] row : occupied) {
                for (boolean field : row) {
                    b.append(field ? 'x' : 'o');
                }
                b.append('\n');
            }

            return b.toString();
        }
    }


    static class Coords {
        public final int x;
        public final int y;

        public Coords(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
