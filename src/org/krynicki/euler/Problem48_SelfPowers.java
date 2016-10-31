package org.krynicki.euler;

import java.io.IOException;

/**
 * Created by kamil.krynicki on 31/10/2016.
 */
public class Problem48_SelfPowers {
    /*
    The series, 11 + 22 + 33 + ... + 1010 = 10405071317.

    Find the last ten digits of the series, 11 + 22 + 33 + ... + 10001000.
    */

    public static void main(String[] args) throws IOException {

        Problem48_SelfPowers n = new Problem48_SelfPowers();
        long t1 = System.currentTimeMillis();

        System.out.println((new SelfPowers(10, 1000)).getSum());

        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
    }

    static class SelfPowers {
        private final long relevantDigits;
        private final long sum;

        public SelfPowers(int maxDigits, int maxValue) {
            this.relevantDigits = (long) Math.pow(10, maxDigits);

            long sum = 0;
            for (int i = 1; i <= maxValue; i++) {
                sum += limitedPow(i, i);
                sum %= relevantDigits;
            }

            this.sum = sum;
        }

        public long getSum() {
            return sum;
        }

        private long limitedPow(int base, int exponent) {
            long result;
            if (exponent == 1) {
                result = base;
            } else {
                result = limitedSquare(limitedPow(base, exponent / 2));
                result *= exponent % 2 == 1 ? base : 1;
            }
            return result % relevantDigits;
        }

        private long limitedSquare(long val) {
            long result = 0;
            long multiplier = val;
            int step = 1;
            while (multiplier > 0) {
                result += val * (multiplier % 10) % (relevantDigits / step) * step;
                result %= relevantDigits;
                multiplier /= 10;
                step *= 10;
            }

            return result % (relevantDigits);
        }
    }
}
