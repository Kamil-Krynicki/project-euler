package org.krynicki.euler.Problems1to50;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by kamil.krynicki on 24/10/2016.
 */
public class Problem36_DoubleBasePalindromes {
    /*
    The decimal number, 585 = 1001001001 (binary), is palindromic in both bases.

    Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.

    (Please note that the palindromic number, in either base, may not include leading zeros.)
     */
    public static void main(String[] args) {
        PalindromeChecker p = new PalindromeChecker();

        Set<Integer> result = new HashSet<>();
        result.add(0);
        long t1 = System.currentTimeMillis();

        for (int i = 1; i < 100000; i += 2) {
            if (p.isPalindrome10(i) && p.isPalindrome2(i)) {
                result.add(i);
            }
        }

        for (int i = 300001; i < 400000; i += 2) {
            if (p.isPalindrome10(i) && p.isPalindrome2(i)) {
                result.add(i);
            }
        }

        for (int i = 500001; i < 600000; i += 2) {
            if (p.isPalindrome10(i) && p.isPalindrome2(i)) {
                result.add(i);
            }
        }

        for (int i = 700001; i < 800000; i += 2) {
            if (p.isPalindrome10(i) && p.isPalindrome2(i)) {
                result.add(i);
            }
        }

        for (int i = 900001; i < 1000000; i += 2) {
            if (p.isPalindrome10(i) && p.isPalindrome2(i)) {
                result.add(i);
            }
        }

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
        System.out.println(result.stream().reduce(Integer::sum));
        System.out.println(result.stream().sorted().distinct().collect(Collectors.toList()));
        System.out.println(result.size());
    }

    public static class PalindromeChecker {

        public PalindromeChecker() {
        }

        boolean isPalindrome10(int number) {
            return number == reverse(number);
        }

        int reverse(int number) {
            int result = 0;

            do {
                result = result * 10 + number % 10;
            } while ((number /= 10) > 0);

            return result;
        }

        boolean isPalindrome2(int number) {
            return number == (Integer.reverse(number)) >>> (31 - len(number, 2));
        }

        private int len(int number, int base) {
            return (int) (Math.log(number) / Math.log(base));
        }
    }
}
