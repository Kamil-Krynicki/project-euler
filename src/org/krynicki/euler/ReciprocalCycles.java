package org.krynicki.euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by kamil.krynicki on 18/10/2016.
 */
public class ReciprocalCycles {
    /*
    A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions
    with denominators 2 to 10 are given:

    1/2	= 	0.5
    1/3	= 	0.(3)
    1/4	= 	0.25
    1/5	= 	0.2
    1/6	= 	0.1(6)
    1/7	= 	0.(142857)
    1/8	= 	0.125
    1/9	= 	0.(1)
    1/10	= 	0.1

    Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle.
    It can be seen that 1/7 has a 6-digit recurring cycle.

    Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
     */

    private int[] visited;

    public ReciprocalCycles(int max) {
        visited = new int[max];
    }

    static public void main(String[] args) {
        ReciprocalCycles tester = new ReciprocalCycles(1000);
        long t1 = System.currentTimeMillis();

        List<Integer> lens = new ArrayList<>(1000);

        for(int i=2; i<1000;i++) {
            lens.add(tester.findCycleLen(i));
        }

        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
        System.out.println(Collections.max(lens));
    }

    int findCycleLen(int i) {
        Arrays.fill(visited, 0);

        int current = 1;
        int len = 0;

        do {
            visited[current] = ++len;
            current *= 10;
            current = current%i;
            if(current==0) {
                return 0;
            }

        } while (visited[current] <= 0);

        return len - visited[current] + 1;
    }
}
