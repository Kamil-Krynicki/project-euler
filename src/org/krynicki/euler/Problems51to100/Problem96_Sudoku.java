package org.krynicki.euler.Problems51to100;

import com.google.common.base.Preconditions;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Created by kamil.krynicki on 25/01/2017.
 */

/*
"003020600,900305001,001806400,008102900,700000008,006708200,002609500,800203009,005010300"
 */
public class Problem96_Sudoku {


    static String sudokuSetu2 = "003020600,900305001,001806400,008102900,700000008,006708200,002609500,800203009,005010300";
    static String sudokuSetup = "483921600,967345821,251876493,008132976,729564138,006798200,002689500,800253069,695017382";

    //static String sudokuSetup = "48392160,967345821,251876493,08132976,729564138,0679820,0268950,80253069,695017382";
    public static void main(String[] args) throws IOException {
        long t1 = System.currentTimeMillis();

        SudokuModel m = new SudokuModel(9, 3);

        List<String> strings = FileUtils.readLines(FileUtils.getFile(args[0]), Charset.defaultCharset());

        int x = 0;
        int y = 0;
        for (String row : strings) {
            if (row.contains("Grid"))
                continue;

            y = 0;
            for (char c : row.toCharArray()) {
                int value = c - '0';
                if (value > 0) m.set(value, x, y);
                y++;
            }
            x++;

            if (x == 9) {
                System.out.println("BEFORE");
                System.out.println(m);
                SudokuSolver.solve(m);
                System.out.println("AFTER");
                System.out.println(m);
                m = new SudokuModel(9, 3);
                x = 0;
            }
        }


        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    static class SudokuSolver {
        public static void solve(SudokuModel model) {
            Coords coords = null;

            do {
                for (int v = 1; v < 10; v++) {
                    coords = model.nextStep(v);
                    if (coords != null) {
                        System.out.println("Val " + v + " to " + coords.x + "," + coords.y);
                        model.set(v, coords.x, coords.y);
                        break;
                    }
                }


                if (coords == null) {
                    coords = model.nakedVal();
                    if (coords != null) {
                        model.fix(coords.x, coords.y);
                        System.out.println("Val naked  to " + coords.x + "," + coords.y);
                    }
                }

                System.out.println(model);

            } while (!model.isFull() && coords != null);

            if (coords == null) {
                System.out.println("FAIL");

            } else {
                System.out.println("SUCCESS");
            }
        }
    }

    static class SudokuModel {
        private final boolean[][][] board;
        private final int[][] vals;

        private final int boardSize;
        private final int blockSize;
        private int emptySpaces;

        public SudokuModel(int boardSize, int blockSize) {
            Preconditions.checkArgument(boardSize > 0);
            Preconditions.checkArgument(blockSize > 0);
            Preconditions.checkArgument(blockSize < boardSize);

            this.board = new boolean[boardSize][boardSize][10];
            this.vals = new int[boardSize][boardSize];
            this.emptySpaces = boardSize * boardSize;

            this.boardSize = boardSize;
            this.blockSize = blockSize;
        }

        public void fix(int x, int y) {
            OptionalInt any = IntStream.range(1, 9).filter(v -> !board[x][y][v]).findAny();
            Preconditions.checkArgument(any.isPresent());
            set(any.getAsInt(), x, y);
        }

        public void set(int value, int x, int y) {
            Preconditions.checkArgument(x >= 0);
            Preconditions.checkArgument(y >= 0);

            Preconditions.checkArgument(x < boardSize);
            Preconditions.checkArgument(y < boardSize);

            Preconditions.checkArgument(!board[x][y][value]);

            removeFromRow(value, y);
            removeFromColumn(value, x);
            removeFromBlock(value, x, y);

            vals[x][y] = value;

            emptySpaces--;
        }

        private void removeFromRow(int value, int y) {
            IntStream.range(0, boardSize).forEach(x -> board[x][y][value] = true);
        }

        private void removeFromColumn(int value, int x) {
            IntStream.range(0, boardSize).forEach(y -> board[x][y][value] = true);
        }

        private void removeFromBlock(int value, int x, int y) {
            int xOffset = x / (boardSize / blockSize) * blockSize;
            int yOffset = y / (boardSize / blockSize) * blockSize;

            IntStream.range(xOffset, xOffset + blockSize)
                    .forEach(xi -> IntStream.range(yOffset, yOffset + blockSize)
                            .forEach(yi -> board[xi][yi][value] = true));
        }

        public boolean isFull() {
            return emptySpaces == 0;
        }

        public Coords nakedVal() {
            Optional<Coords> next = IntStream.range(0, boardSize)
                    .boxed()
                    .flatMap(x -> IntStream.range(0, boardSize)
                            .filter(y -> !isFixed(x, y))
                            .filter(y -> IntStream.rangeClosed(1, 9).filter(v -> !board[x][y][v]).count() == 1)
                            .mapToObj(y -> new Coords(x, y))
                    )
                    .findFirst();

            return next.isPresent() ? next.get() : null;
        }

        private boolean isFixed(Integer x, int y) {
            return vals[x][y] > 0;
        }

        public Coords nextStep(int v) {
            for (int x = 0; x < boardSize; x++)
                if (hasUniquePlacementColumn(x, v))
                    return getPlaceInColumn(x, v);

            for (int y = 0; y < boardSize; y++)
                if (hasUniquePlacementRow(y, v))
                    return getPlaceInRow(y, v);

            for (int y = 0; y < boardSize; y += blockSize)
                for (int x = 0; x < boardSize; x += blockSize)
                    if (hasUniquePlacementInBlock(x, y, v))
                        return canPlaceUniquelyInBlock(x, y, v);

            return null;
        }


        public boolean hasUniquePlacementRow(final int y, final int v) {
            return IntStream.range(0, boardSize)
                    .filter(x -> !isFixed(x, y))
                    .filter(x -> !board[x][y][v])
                    .count() == 1;
        }

        private Coords getPlaceInRow(final int y, final int v) {
            return new Coords(IntStream.range(0, boardSize).filter(t -> !isFixed(t, y)).filter(x -> !board[x][y][v]).findFirst().getAsInt(), y);
        }

        public boolean hasUniquePlacementColumn(final int x, final int v) {
            return IntStream.range(0, boardSize)
                    .filter(y -> !isFixed(x, y))
                    .filter(y -> !board[x][y][v])
                    .count() == 1;
        }

        private Coords getPlaceInColumn(final int x, final int v) {
            return new Coords(x, IntStream.range(0, boardSize).filter(y -> !isFixed(x, y)).filter(y -> !board[x][y][v]).findFirst().getAsInt());
        }

        public boolean hasUniquePlacementInBlock(int x, int y, int v) {
            int xOffset = x / (boardSize / blockSize) * blockSize;
            int yOffset = y / (boardSize / blockSize) * blockSize;

            return IntStream.range(xOffset, xOffset + blockSize)
                    .flatMap(xi -> IntStream.range(yOffset, yOffset + blockSize)
                            .filter(yi -> !isFixed(xi, yi))
                            .filter(yi -> !board[xi][yi][v])
                    )
                    .count() == 1;
        }

        private Coords canPlaceUniquelyInBlock(int x, int y, int v) {
            int xOffset = x / (boardSize / blockSize) * blockSize;
            int yOffset = y / (boardSize / blockSize) * blockSize;

            return IntStream.range(xOffset, xOffset + blockSize)
                    .boxed()
                    .flatMap(xi -> IntStream.range(yOffset, yOffset + blockSize)
                            .filter(yi -> !isFixed(xi, yi))
                            .filter(yi -> !board[xi][yi][v])
                            .mapToObj(yi -> new Coords(xi, yi)))
                    .findAny()
                    .get();
        }


        @Override
        public String toString() {
            StringBuilder b = new StringBuilder();


            for (int x = 0; x < boardSize; x++) {
                for (int y = 0; y < boardSize; y++) {
                    if (isFixed(x, y)) b.append(vals[x][y]);
                    else {
                        b.append('x');
                    }
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
