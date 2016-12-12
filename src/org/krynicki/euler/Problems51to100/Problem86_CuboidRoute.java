package org.krynicki.euler.Problems51to100;

/**
 * Created by kamil.krynicki on 12/12/2016.
 */
public class Problem86_CuboidRoute {
    // observation 1: it's closely related to pythagorean triplets PT

    // observation 2: there is lots of memo use here

    // and see if the minimum pair forms a PT
    // for any a, b, c sides we need to find the minimum third side for (a+b, c), (a, b+c) or (a+c, b), which are the thee ways to cross the room,
    /*
    A spider, S, sits in one corner of a cuboid room, measuring 6 by 5 by 3, and a fly, F, sits in the opposite corner.
    By travelling on the surfaces of the room the shortest "straight line" distance from S to F is 10 and the path
    is shown on the diagram.

    However, there are up to three "shortest" path candidates for any given cuboid and the shortest route doesn't
    always have integer length.

    It can be shown that there are exactly 2060 distinct cuboids, ignoring rotations, with integer dimensions,
    up to a maximum size of M by M by M, for which the shortest route has integer length when M = 100. This is the least
    value of M for which the number of solutions first exceeds two thousand; the number of solutions when M = 99 is 1975.

    Find the least value of M such that the number of solutions first exceeds one million.
     */
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        int result = 0;
        int M = 0;
        int max = (int) 1E6;
        do {
            M++;
            for (int a = 1; a <= M; a++) {
                for (int b = a; b <= M; b++) {
                    result += (checkPaths(a, b, M) ? 1 : 0);
                }
            }
        } while (result < max);

        System.out.println(result);
        System.out.println(M);

        long t2 = System.currentTimeMillis();

        System.out.println("==========");
        System.out.println(t2 - t1);
    }

    private static boolean checkPaths(int a, int b, int c) {
        return isInteger(Math.sqrt(c * c + (a + b) * (a + b)));
    }

    private static boolean isInteger(double val) {
        return Math.abs(val - (int) val) < 1E-4;
    }
}
