package org.krynicki.euler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by K on 2016-11-01.
 */
public class Problem50_ConsecutivePrimeSum {

    static QuadraticPrimes.PrimeGenerator gen = new QuadraticPrimes.PrimeGenerator(100);
    static Map<Integer, Result> memo = new HashMap<>();

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        System.out.println(maxSequence(100000));


        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }


    static Result maxSequence(int max) {
        Result maxResult = new Result(0,0);
        Result curr = new Result(1,0);



        int j = 1;

        for(int i=0;i<max;i=gen.nextPrime(i)) {

            if(curr.lastVal == 0) {
                curr = maxSequence(i, 0,0, max);
            }
            else {
                curr = maxSequence(curr.lastVal, curr.len-1, curr.sum-i, max);
            }

            if(curr.len>maxResult.len) {
                maxResult = curr;
            }

            j++;
        }


        return maxResult;
    }

    private static Result maxSequence(int prvNumber, int startLen, int startSum, int max) {
        int nextPrime = prvNumber;

        int currentSum = startSum;
        int currentLen = startLen;

        Result result = new Result(0,0);

        while(currentSum < max) {
            if (gen.isPrime(currentSum)) {
                result.len = currentLen;
                result.sum = currentSum;
                result.lastVal = nextPrime;
            }

            nextPrime = gen.nextPrime(nextPrime);

            currentLen++;
            currentSum += nextPrime;

        }

        return result;
    }

    private static boolean isPrime(int num) {
        for(int i=2;i<Math.sqrt(num);i++) {
            if(num%i==0) {
                return false;
            }
        }
        return true;
    }

    static class Result {
        int len;
        int sum;
        int lastVal = 0;

        public Result(int len, int sum) {
            this.len = len;
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "len=" + len +
                    ", sum=" + sum +
                    ", lastVal=" + lastVal +
                    '}';
        }
    }
}
