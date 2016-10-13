package org.krynicki.euler;

/**
 * Created by K on 2016-10-13.
 */
public class AmicableNumbers {
    /*
    Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
    If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.

    For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.

    Evaluate the sum of all the amicable numbers under 10000.
     */


    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        AmicableNumbers a = new AmicableNumbers(10000);
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }


    private int[] memo;

    public AmicableNumbers(int max) {
        memo = new int[max + 1];

        int total = 0;

        int numberA, numberB;

        for (int i = 1; i <= max; i++) {
            while(i<=max && memo[i] != 0) {
                i++;
            }
            numberA = i;
            numberB = i;
            do {
                if(memo[numberA] != 0) {
                    break;
                }
                numberA = numberB;
                numberB = getSum(numberA);
            }  while (numberA<=max && numberA != getSum(numberB));
            if(numberA == getSum(numberB) && numberA != numberB) {
                total += numberA;
                total += numberB;
            }

        }

        System.out.println(total);

    }

    private int getSum(int i) {
        if (i > memo.length) return 0;

        if (memo[i] == 0) {
            memo[i] = sumOfDivisors(i);
        }
        return memo[i];

    }

    private int sumOfDivisors(int n) {
        int val = n;
        int result = 1;
        int divisor = 2;

        while (divisor <= val / 2) {
            if (val % divisor == 0) {
                result += divisor;
            }
            divisor++;
        }

        return result;
    }
}
