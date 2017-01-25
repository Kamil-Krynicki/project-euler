package org.krynicki.euler.Problems51to100;

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
        for(String row: sudokuSetup.split(",")) {
            int y = 0;
            for(char c : row.toCharArray()) {
                int value = c - '0';
                if(value>0) m.put(value, x, y);
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
        private int emptySpaces;
        private Map<Integer, SudokuLayer> layers;
        private int[][] board;

        private int size;
        private int blockSize;
        private final String hRule;

        public SudokuModel(int size, int blockSize) {
            this.board = new int[size][size];

            this.layers = new HashMap<>();
            for(int i = 1; i<10; i++) {
                this.layers.put(i, new SudokuLayer(size, blockSize));
            }

            this.size = size;
            this.blockSize = blockSize;

            this.hRule = makeHrule(size);
            this.emptySpaces = size*size;
        }

        private String makeHrule(int size) {
            StringBuilder b = new StringBuilder();
            for(int i=0;i<=2*size;i++){
                b.append('-');
            }
            return b.toString();
        }

        public void put(int value, int atX, int atY) {
            for(Map.Entry<Integer, SudokuLayer> layer : layers.entrySet()) {
                if(layer.getKey() != value)
                    layer.getValue().occupy(atX, atY);
                else
                    layer.getValue().put(atX, atY);
            }
            board[atX][atY] = value;
            emptySpaces--;
        }

        public boolean isSolved() {
            return emptySpaces==0;
        }


        public void solve() {
            while (!isSolved()) {
                Coords coords;
                boolean found = false;
                for(Map.Entry<Integer, SudokuLayer> value : layers.entrySet()) {
                    coords = value.getValue().nextStep();
                    if(coords.isValid()) {
                        put(value.getKey(), coords.x, coords.y);
                        found = true;
                        break;
                    }
                }

                if(!found) {
                    System.out.print("Unable to make progress from:");
                    System.out.print(this);
                    break;
                }
            }
        }

        public Coords nextStep(int value) {
            return layers.get(value).nextStep();
        }

        @Override
        public String toString() {
            StringBuilder b = new StringBuilder();

            for(int[] boardRow : board){
                b.append(hRule);
                b.append('\n');
                for(int field : boardRow){
                    b.append('|');

                    if(field == 0) {
                        b.append(' ');
                    } else {
                        b.append(field);
                    }
                }
                b.append("|\n");
            }
            b.append(hRule);

            return b.toString();
        }
    }

    static class SudokuLayer {
        private boolean[][] occupied;
        private int size = 9;
        private int blockSize = 3;

        public SudokuLayer(int size, int blockSize) {
            this.occupied = new boolean[size][size];
            this.size = size;
            this.blockSize = blockSize;
        }

        public void put(int x, int y) {
            Preconditions.checkArgument(x < size);
            Preconditions.checkArgument(y < size);
            Preconditions.checkArgument(x >= 0);
            Preconditions.checkArgument(y >= 0);

            Preconditions.checkArgument(canPut(x, y));

            markColumn(x);
            markRow(y);
            markBlock(x, y);
        }

        public void occupy(int x, int y) {
            occupied[x][y] = true;
        }

        public Coords nextStep() {
            for (int x = 0; x < size; x++) {
                if(hasUniquePlacementColumn(x)) {
                    return getPlaceInColumn(x);
                }
            }

            for (int y = 0; y < size; y++) {
                if(hasUniquePlacementRow(y)) {
                    return getPlaceInRow(y);
                }
            }

            for (int y = 0; y < size; y += blockSize) {
                for (int x = 0; x < size; x += blockSize) {
                    Coords result = canPlaceUniquelyInBlock(x, y);
                    if (result.isValid()) {
                        return result;
                    }
                }
            }

            return Coords.INVALID;
        }

        public boolean hasUniquePlacementRow(int y) {
            return IntStream.range(0, size).filter( t -> isUnoccupied(t, y)).count() == 1;
        }

        private Coords getPlaceInRow(int y) {
            return new Coords(IntStream.range(0, size).filter( t -> isUnoccupied(t, y)).findFirst().getAsInt(), y);
        }

        public boolean hasUniquePlacementColumn(int x) {
            return IntStream.range(0, size).filter( t -> isUnoccupied(x, t)).count() == 1;
        }

        private Coords getPlaceInColumn(int x) {
            return new Coords(x, IntStream.range(0, size).filter( t -> isUnoccupied(x, t)).findFirst().getAsInt());
        }

        private boolean isUnoccupied(int x, int y) {
            return !occupied[x][y];
        }

        private boolean isUnoccupied(Coords coords) {
            return !occupied[coords.x][coords.y];
        }

        private Coords canPlaceUniquelyInBlock(int x, int y) {
            int xOffset = x / (size / blockSize) * blockSize;
            int yOffset = y / (size / blockSize) * blockSize;

            Coords result = Coords.INVALID;

            for (int i = xOffset; i < xOffset + blockSize; i++)
                for (int j = yOffset; j < yOffset + blockSize; j++)
                    if (isUnoccupied(i, j))
                        if (!result.isValid()) result = new Coords(i, j);
                        else return Coords.INVALID;

            return result;
        }

        class RowIterator implements Iterator<Coords> {
            private int x;
            private int y;

            private int max;

            public RowIterator(int y, int max) {
                this.y = y;
                this.x = 0;
                this.max = max;
            }

            @Override
            public boolean hasNext() {
                return x < max;
            }

            @Override
            public Coords next() {
                return new Coords(x++, y);
            }
        }

        private void markBlock(int x, int y) {
            int xOffset = x / (size / blockSize) * blockSize;
            int yOffset = y / (size / blockSize) * blockSize;

            for (int i = xOffset; i < xOffset + blockSize; i++)
                for (int j = yOffset; j < yOffset + blockSize; j++)
                    occupy(i, j);
        }

        private void markRow(int y) {
            for (int i = 0; i < size; i++) occupy(i, y);
        }

        private void markColumn(int x) {
            for (int i = 0; i < size; i++) occupy(x, i);
        }

        private boolean canPut(int x, int y) {
            return isUnoccupied(x, y);
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
        public final static Coords INVALID = new Coords() {
            @Override
            public boolean isValid() {
                return false;
            }

            public String toString() {
                return "Coords{INVALID}";
            }
        };

        public final int x;
        public final int y;

        public Coords() {
            this.x = Integer.MIN_VALUE;
            this.y = Integer.MIN_VALUE;
        }

        public Coords(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isValid() {
            return x >= 0 && y >= 0;
        }

        @Override
        public String toString() {
            return "Coords{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
