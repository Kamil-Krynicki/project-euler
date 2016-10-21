package org.krynicki.euler;

/**
 * Created by kamil.krynicki on 21/10/2016.
 */
public class Problem34_FactorialDigits {
    /*
    145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.

    Find the sum of all numbers which are equal to the sum of the factorial of their digits.

    Note: as 1! = 1 and 2! = 2 are not sums they are not included.
    */

    // observation 1 - this is almost the same as the Power Digits problem

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        FactorialDigits f = new FactorialDigits();

        int sum = 0;
        for (int i = 3; i < f.maxDigitFactorialSum(); i++) {
            if (i == f.sumDigitFactorials(i)) {
                sum += i;
            }
        }
        System.out.println("Sum of digits is " + sum);

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    static public class FactorialDigits {

        private int[] factorials;

        public FactorialDigits() {
            factorials = new int[10];

            factorials[0] = 1;
            factorials[1] = 1;
            for (int i = 2; i < 10; i++) {
                factorials[i] = factorials[i - 1] * i;
            }
        }

        public int sumDigitFactorials(int number) {
            int sum = 0;
            int tmp = number;
            while (tmp != 0) {
                sum += factorials[tmp % 10];
                tmp /= 10;
            }
            return sum;
        }

        public int maxDigitFactorialSum() {
            int sum = 0;
            int maxValue = 1;
            do {
                sum += factorials[9];
                maxValue *= 10;
            } while (sum > maxValue);
            return sum;
        }
    }
}
