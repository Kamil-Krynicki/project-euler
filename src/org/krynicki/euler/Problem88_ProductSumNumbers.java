package org.krynicki.euler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by kamil.krynicki on 26/10/2016.
 */
public class Problem88_ProductSumNumbers {
    /*
    A natural number, N, that can be written as the sum and product of a given set of at least two natural numbers, {a1, a2, ... , ak} is called a product-sum number: N = a1 + a2 + ... + ak = a1 × a2 × ... × ak.

    For example, 6 = 1 + 2 + 3 = 1 × 2 × 3.

    For a given set of size, k, we shall call the smallest N with this property a minimal product-sum number. The minimal product-sum numbers for sets of size, k = 2, 3, 4, 5, and 6 are as follows.

    k=2: 4 = 2 × 2 = 2 + 2
    k=3: 6 = 1 × 2 × 3 = 1 + 2 + 3
    k=4: 8 = 1 × 1 × 2 × 4 = 1 + 1 + 2 + 4
    k=5: 8 = 1 × 1 × 2 × 2 × 2 = 1 + 1 + 2 + 2 + 2
    k=6: 12 = 1 × 1 × 1 × 1 × 2 × 6 = 1 + 1 + 1 + 1 + 2 + 6

    Hence for 2≤k≤6, the sum of all the minimal product-sum numbers is 4+6+8+12 = 30; note that 8 is only counted once in the sum.

    In fact, as the complete set of minimal product-sum numbers for 2≤k≤12 is {4, 6, 8, 12, 15, 16}, the sum is 61.

    What is the sum of all the minimal product-sum numbers for 2≤k≤12000?
    */
    private static Set<Integer> result = new HashSet<>();
    private static Map<Integer, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
        Problem88_ProductSumNumbers p = new Problem88_ProductSumNumbers();

        long t1 = System.currentTimeMillis();

        for (int i = 2; i <= 12000; i++) {
            p.generate(i);
        }

        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
        System.out.println("SUM:");
        System.out.println(result.stream().reduce(Integer::sum));
    }

    private void generate(int k) {
        int i = 0;
        long maxSum = k;
        long maxMul = 1;

        while (++maxSum > (maxMul<<=1)) {
            i++;
        }

        while (!redistribute(i, i, k, 1)) {
            i++;
        }
    }

    boolean redistribute(int max, int toDistribute, int currentSum, int currentMul) {
        int newSum = currentSum;
        int newMul = currentMul;
        int leftToDistribute = toDistribute;

        for (int i = 2; i <= Math.min(toDistribute, max); i++) {
            newSum++;
            newMul /= (i - 1);
            newMul *= i;
            leftToDistribute--;
            if (newSum > newMul) {
                if (redistribute(i, leftToDistribute, newSum, newMul)) {
                    return true;
                }
            } else if (newSum == newMul) {
                result.add(currentSum);
                return true;
            }
        }
        return false;
    }
}
