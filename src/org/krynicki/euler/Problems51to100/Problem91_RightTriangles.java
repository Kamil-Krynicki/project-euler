package org.krynicki.euler.Problems51to100;

/**
 * Created by K on 2017-01-16.
 */
public class Problem91_RightTriangles {
/*
The points P (x1, y1) and Q (x2, y2) are plotted at integer co-ordinates and are joined to the origin, O(0,0), to form ΔOPQ.


There are exactly fourteen triangles containing a right angle that can be formed when each co-ordinate lies between 0 and 2 inclusive; that is,
0 ≤ x1, y1, x2, y2 ≤ 2.


Given that 0 ≤ x1, y1, x2, y2 ≤ 50, how many right triangles can be formed?
 */

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        int max = 50;

        long v = 0;

        for (int y1 = max; y1 >= 0; y1--) {
            for (int x1 = 0; x1 <= max; x1++) {
                if (y1 == 0 && x1 == 0) continue;
                for (int y2 = y1; y2 >= 0; y2--) {
                    for (int x2 = (y2 < y1 ? 0 : x1 + 1); x2 <= max; x2++) {
                        if (y2 == 0 && x2 == 0) continue;
                        if (isRightTriangle(0, 0, x1, y1, x2, y2)) {
                            v++;
                        }
                    }
                }
            }
        }

        System.out.println(v);
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    private static boolean isRightTriangle(int x0, int y0, int x1, int y1, int x2, int y2) {
        return isPerpendicular(x1 - x0, y1 - y0, x2 - x0, y2 - y0) ||
                isPerpendicular(x0 - x1, y0 - y1, x2 - x1, y2 - y1) ||
                isPerpendicular(x1 - x2, y1 - y2, x0 - x2, y0 - y2);
    }

    private static boolean isPerpendicular(int xA, int yA, int xB, int yB) {
        return (xA * xB + yA * yB) == 0;
    }
}
