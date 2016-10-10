package org.krynicki.euler;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
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

    private static final Deque<Long> CURRENT_WORK = new ArrayDeque<>(1024);

    public static void main(String[] args) {
        LongestCollatzSequence l = new LongestCollatzSequence();

        long t1 = System.currentTimeMillis();
        System.out.println(l.longestCollatz(1000000));
        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
    }

    private Map<Long, Integer> memo;

    public long longestCollatz(int max) {
        memo = new HashMap<Long, Integer>(max);
        memo.put(1l, 1);


        for (int i = 2; i < max; i++) {
            collatzDp(i);
        }

        int maxLen = Collections.max(memo.values());

        for (Map.Entry<Long, Integer> el : memo.entrySet()) {
            if (el.getValue() == maxLen) {
                return el.getKey();
            }
        }

        return -1;
    }

    private int collatzDp(long val) {
        Integer result = memo.get(val);
        if (result == null) {
            result = collatzDp(next(val)) + 1;
            memo.put(val, result);
        }
        return result;
    }

    private int collatzDpNonRecursive(long val) {
        long current = val;

        CURRENT_WORK.clear();

        while (!memo.containsKey(current)) {
            CURRENT_WORK.push(current);
            current = next(current);
        }

        int result = memo.get(current);

        while (!CURRENT_WORK.isEmpty()) {
            memo.put(CURRENT_WORK.pop(), ++result);
        }

        return result;

    }

    private long next(long n) {
        if ((n & 1) == 0) return n >> 1;
        else return (n << 2) - n + 1; // speeeeed!
    }
}
