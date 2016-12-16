package org.krynicki.euler.Problems51to100;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kamil.krynicki on 16/12/2016.
 */
public class Problem90_CubeDigitPairs {
    /*
    Each of the six faces on a cube has a different digit (0 to 9) written on it; the same is done to a second cube.
    By placing the two cubes side-by-side in different positions we can form a variety of 2-digit numbers.

    For example, the square number 64 could be formed:


    In fact, by carefully choosing the digits on both cubes it is possible to display all of the square numbers below
    one-hundred: 01, 04, 09, 16, 25, 36, 49, 64, and 81.

    For example, one way this can be achieved is by placing {0, 5, 6, 7, 8, 9} on one cube and {1, 2, 3, 4, 8, 9} on
    the other cube.

    However, for this problem we shall allow the 6 or 9 to be turned upside-down so that an arrangement like
    {0, 5, 6, 7, 8, 9} and {1, 2, 3, 4, 6, 7} allows for all nine square numbers to be displayed;
    otherwise it would be impossible to obtain 09.

    In determining a distinct arrangement we are interested in the digits on each cube, not the order.

    {1, 2, 3, 4, 5, 6} is equivalent to {3, 6, 4, 1, 2, 5}
    {1, 2, 3, 4, 5, 6} is distinct from {1, 2, 3, 4, 5, 9}

    But because we are allowing 6 and 9 to be reversed, the two distinct sets in the last example both
    represent the extended set {1, 2, 3, 4, 5, 6, 9} for the purpose of forming 2-digit numbers.

    How many distinct arrangements of the two cubes allow for all of the square numbers to be displayed?
    */

    static char[][] squares = {
            "01".toCharArray(),
            "04".toCharArray(),
            "09".toCharArray(),
            "16".toCharArray(),
            "25".toCharArray(),
            "36".toCharArray(),
            "49".toCharArray(),
            "64".toCharArray(),
            "81".toCharArray()};

    static List<String> cubes = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        long t1 = System.currentTimeMillis();

        generateCubes("0123456789".toCharArray());

        int count = 0;

        for (int j = 0; j < cubes.size(); j++) {
            char[] cubeA = cubes.get(j).toCharArray();
            for (int k = j; k < cubes.size(); k++) {
                if (canAssemble(cubeA, cubes.get(k).toCharArray())) count++;
            }
        }

        System.out.println(count);

        long t2 = System.currentTimeMillis();
        System.out.print(t2 - t1);
    }

    static public void generateCubes(char[] chars) {
        generate(6, -1, "0123456789".toCharArray(), "");
    }

    static public void generate(int left, int used, char[] chars, String acc) {
        if (left == 0) {
            cubes.add(acc);
        } else {
            for (int i = used + 1; i <= chars.length - left; i++) {
                generate(left - 1, i, chars, acc + chars[i]);
            }
        }
    }

    static boolean canAssemble(char[] cubeA, char[] cubeB) {
        for (char[] square : squares)
            if (!canAssemble(square, cubeA, cubeB)
                    && !canAssemble(square, cubeB, cubeA)) return false;
        return true;
    }

    private static boolean canAssemble(char[] square, char[] cubeA, char[] cubeB) {
        return contains(square[0], cubeA) && contains(square[1], cubeB);
    }

    private static boolean contains(char val, char[] cube) {
        if (val == '6' || val == '9') {
            return Arrays.binarySearch(cube, '6') >= 0 || Arrays.binarySearch(cube, '9') >= 0;
        } else {
            return Arrays.binarySearch(cube, val) >= 0;
        }
    }
}
