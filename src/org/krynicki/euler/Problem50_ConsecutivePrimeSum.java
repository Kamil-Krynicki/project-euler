package org.krynicki.euler;

import org.krynicki.euler.util.PrimeGenerator;

/**
 * Created by K on 2016-11-01.
 */
public class Problem50_ConsecutivePrimeSum {

    static int[] primes;
    static long[] sums;

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        int size = 1000;
        PrimeGenerator gen = new PrimeGenerator(size);
        primes = new int[size];
        sums = new long[size];

        int j=0;
        for(int prime:gen.getPrimes()) {
            primes[j++] = prime;
        }

        for(int i=1;i<sums.length;i++) {
            sums[i] = primes[i]+sums[i-1];
        }

        System.out.println(maxSequencePrimitive(1000000));

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    private static Result maxSequencePrimitive(int max) {
        Result maxResult = new Result(0,0);
        Result curr;

        for(int primeNumber=1;primeNumber<primes.length; primeNumber++) {
            curr = maxSequencePrimitive(primeNumber, maxResult.len, max);

            if(curr.len>maxResult.len) {
                maxResult = curr;
            }
        }

        return maxResult;
    }

    private static Result maxSequencePrimitive(int start, int minLen, int maxSum) {
        Result result = new Result(0,0);

        int end = sums.length-1;

        Long startSum = sums[start];
        while((sums[end]- startSum) > maxSum && end >= 0) {
            end--;
        }

        while(!isPrime((int) (sums[end]- startSum)) && end >= minLen) {
            end--;
        }

        if(end > minLen) {
            result.len = end-start;
            result.sum = sums[end]- startSum;
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
        long sum;

        public Result(int len, int sum) {
            this.len = len;
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "len=" + len +
                    ", sum=" + sum +
                    '}';
        }
    }
}
