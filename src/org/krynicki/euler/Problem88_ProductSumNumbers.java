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

        for(int i=2;i<=1000;i++) {
            p.generate(i);
        }

        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
        System.out.println("SUM:");
        System.out.println(result.stream().reduce(Integer::sum));
    }

    private void generate(int k) {
        //int[] values = new int[k];
        //Arrays.fill(values, 1);

        int i = 0;
        int maxSum;
        int maxMul;
        do {
            maxSum = k;
            maxMul = 1;
            do {
                i++;
                maxSum++;
                maxMul= (int)Math.pow(2,i);
            } while (maxSum > maxMul);
        } while (!redistribute(k, i, i, k, 1, k));
    }

    boolean redistribute(int k, int max, int sum, int currentSum, int currentMul, int num) {
        if (currentMul == currentSum) {
           // System.out.println("FOUND");
           // System.out.println("For k = "+num);
           // //System.out.println("SUM = " + currentSum);
           // System.out.println("MUL = " + currentMul);
           // System.out.println(Arrays.toString(values));
//
            result.add(currentSum);
          // memo.put(num, currentSum);

            return true;
        }

        int newSum = currentSum;
        int newMul = currentMul;
        int newScope = sum;

        for (int i = 2; i <= sum && i <= max; i++) {
            newSum++;
            newMul*=i;
            newMul/=(i-1);
            newScope--;
            if(newSum >= newMul) {
              //  values[k-1] = i;
                if (redistribute(k - 1, i, newScope, newSum, newMul, num)) {
                    return true;
                }
             //   values[k-1] = i-1;
            }
        }
        return false;
    }
}
