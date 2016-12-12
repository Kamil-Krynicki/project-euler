package org.krynicki.euler.Problems1to50;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kamil.krynicki on 29/09/2016.
 */
public class LargestPalindromeNumber {
    /**
     * A palindromic number reads the same both ways.
     * The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
     * <p>
     * Find the largest palindrome made from the product of two 3-digit numbers.
     */

    int palindromize(int s) {
        int result = s;

        while (s != 0) {
            result *= 10;
            result += s % 10;
            s /= 10;
        }

        return result;
    }

    void generatePalindromeNumbers(int digit) {
        if (digit % 2 == 0) {
            digit /= 2;
        } else {
            digit /= 2;
        }

        int max = (int) Math.pow(10, digit) - 1;
        int min = (int) Math.pow(10, digit - 1);

        int curr = max;

        while (curr >= min) {
            System.out.println(palindromize(curr));
            curr--;
        }
    }

    int invert(int s) {
        int result = 0;

        while (s != 0) {
            result += s % 10;
            result *= 10;
            s /= 10;
        }

        return (result / 10);
    }

    boolean isPalindrome(int i) {
        return i == invert(i);
    }


    static public void main(String[] args) {
        LargestPalindromeNumber t = new LargestPalindromeNumber();

        Set<Integer> result = new HashSet<>();

        for (int i = 100; i < 1000; i++) {
            for (int j = 100; j < 1000; j++) {
                if (t.isPalindrome(i * j)) {
                    result.add(i*j);
                }
            }
        }

        System.out.println(Collections.max(result));
    }

}




