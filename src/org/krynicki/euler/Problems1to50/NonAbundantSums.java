package org.krynicki.euler.Problems1to50;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kamil.krynicki on 14/10/2016.
 */
public class NonAbundantSums {
    public static final int INT = 28123;
    /*
    A perfect number is a number for which the sum of its proper divisors is exactly equal to the number.
    For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.

    A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.

    As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as
    the sum of two abundant numbers is 24. By mathematical analysis, it can be shown that all integers greater than 28123
    can be written as the sum of two abundant numbers. However, this upper limit cannot be reduced any further by analysis
    even though it is known that the greatest number that cannot be expressed as the sum of two abundant numbers is less than this limit.

    Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.

     */

    public static void main(String[] args) throws IOException {

        long t1 = System.currentTimeMillis();
        NonAbundantSums n = new NonAbundantSums();
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);


    }

    long sum;

    public NonAbundantSums() {
        Set<Integer> abundant = new HashSet<>();
        for(int i=2; i< INT+1; i++) {
            if(i%2!=0 && i%5!=0) { // no chance!
                continue;
            }
            if(isAbundant(i)) {
                abundant.add(i);
            }
        }

        sum = 0;
        for(int i = 1; i<INT;i++) {
            System.out.print(i);
            if(!hasSum(i, abundant)) {
                sum += i;
            }
        }
    }

    boolean hasSum(int sum, Set<Integer> values) {
        for(Integer value:values) {
            if(values.contains(sum-value)) {
                System.out.print(" "+value +" and "+(sum-value));
                return true;
            }
        }
        return false;
    }

    boolean isAbundant(int number) {
        return number < sumOfDivisors(number);
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
