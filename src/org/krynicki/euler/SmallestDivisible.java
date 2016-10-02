package org.krynicki.euler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 2016-10-02.
 */
public class SmallestDivisible {
    /*
    * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
    * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
    */

    private boolean[] divisibleBy;
    private int size;
    private long result;

    SmallestDivisible(int size) {
        this.size = size;
        divisibleBy = new boolean[size + 1];
        divisibleBy[0] = true;
        divisibleBy[1] = true;

        this.result = 1;

        for (int i = 2; i <= size; i++) {
            int val = i;
            while (val < size) {
                if (!divisibleBy[val]) {
                    result *= i;
                    divisibleBy[val] = true;
                }
                val *= i;
            }
            for (int j : derivedGuaratees(i)) {
                divisibleBy[j] = true;
            }
        }
    }

    public static void main(String[] args) {
        SmallestDivisible t = new SmallestDivisible(100);

        System.out.println(t.getResult());
    }

    private Iterable<Integer> derivedGuaratees(int num) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 2; i < num; i++) {
            if (divisibleBy[i]) {
                if (num * i <= size) {
                    result.add(num * i);
                }
            }
        }
        return result;
    }

    public long getResult() {
        return result;
    }
}
