package org.krynicki.euler;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by kamil.krynicki on 15/11/2016.
 */
public class Problem66_DiophantineEquation {
    /*
    Consider quadratic Diophantine equations of the form:

    x2 – Dy2 = 1

    For example, when D=13, the minimal solution in x is 6492 – 13×1802 = 1.

    It can be assumed that there are no solutions in positive integers when D is square.

    By finding minimal solutions in x for D = {2, 3, 5, 6, 7}, we obtain the following:

    32 – 2×22 = 1
    22 – 3×12 = 1
    92 – 5×42 = 1
    52 – 6×22 = 1
    82 – 7×32 = 1

    Hence, by considering minimal solutions in x for D ≤ 7, the largest x is obtained when D=5.

    Find the value of D ≤ 1000 in minimal solutions of x for which the largest value of x is obtained.
    */

    static SquareChecker squareChecker = new SquareChecker();

    static public void main(String... args) {
        final long t1 = System.currentTimeMillis();

        Set<Long> result = new HashSet<>();

        for (int D = 61; D <= 61; D++) {
            if (!isSquare(D)) {
                System.out.println("====================================");
                System.out.println("D=" + D);
                long y = 1;
                double xD;
                while (!isWhole((xD = Math.sqrt(1 + D * y * y)))) {
                    y++;
                }

                result.add((long) xD);

                System.out.println("y=" + y);
                System.out.println("x=" + (long)xD);
            }
        }


        final long t2 = System.currentTimeMillis();

        result.stream().sorted().forEach(System.out::println);

        System.out.println(t2 - t1);
    }

    private static boolean isWhole(double num) {
        return num%1==0;
    }

    private static boolean isSquare(long num) {
        return squareChecker.isSquare(num);
    }

    static class SquareChecker {
        TreeSet<Long> squares = new TreeSet<>();
        long maxNum = 1;
        long maxSq = 1;

        boolean isSquare(long num) {
            if (num > maxSq) {
                do {
                    squares.add(maxSq);
                    maxNum++;
                } while ((maxSq = maxNum * maxNum) < num);
                squares.add(maxSq);
            }
            return squares.contains(num);
        }
    }
}
