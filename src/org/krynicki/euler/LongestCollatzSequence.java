package org.krynicki.euler;

import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by kamil.krynicki on 07/10/2016.
 */
public class LongestCollatzSequence {
    /*
    The following iterative sequence is defined for the set of positive integers:
    *
    * n → n/2 (n is even)
    * n → 3n + 1 (n is odd)
    *
    * Using the rule above and starting with 13, we generate the following sequence:
    *
    * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
    * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
    *
    * Which starting number, under one million, produces the longest chain?
    */

    public static void main(String[] args) {
        LongestCollatzSequence l = new LongestCollatzSequence();

        long t1 = System.currentTimeMillis();
        System.out.println(l.longestCollatz(1000000));
        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
    }

    private Map<Integer, Integer> memo;

    public int longestCollatz(int start) {
        memo = new HashMap<>();
        memo.put(1, 1);


        Deque<Integer> currentWork = new LinkedList<>();

        for (int i = 2; i < start; i++) {
            if (memo.containsKey(i)) continue;
            currentWork.push(i);
            while (!currentWork.isEmpty()) {
                int current = currentWork.peek();
                int next = next(current);
                if (!memo.containsKey(next)) {
                    currentWork.push(next);
                } else {
                    memo.put(currentWork.pop(), memo.get(next) + 1);
                }
            }
        }

        return Collections.max(memo.values());
    }

    private int collatzDP(int val) {
        if (!memo.containsKey(val)) {
            memo.put(val, collatzDP(next(val)) + 1);
        }
        return memo.get(val);
    }

    private int next(int n) {
        if (n % 2 == 0) return n >> 1;
        else return (n << 2) - n + 1; // speeeeed!
    }

    //            currentWork.push(i);
    //      while(!currentWork.isEmpty()) {
    //    int current = currentWork.peek();
    //    int next = next(current);
    //    if (!memo.containsKey(next)) {
    //        currentWork.push(next);
    //    }
    //    else {
    //        memo.put(currentWork.pop(), memo.get(next) + 1);
    //    }
    //}
}
