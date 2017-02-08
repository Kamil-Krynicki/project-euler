package org.krynicki.euler.util;

import com.google.common.base.Preconditions;

/**
 * Created by K on 2017-02-08.
 */
public class LastDigits {
    public static long lastDigitsPow(int base, int exponent, int digits) {
        Preconditions.checkArgument(base>=0);
        Preconditions.checkArgument(exponent > 0);
        Preconditions.checkArgument(digits > 0);
        return innerLastDigitsPow(base, exponent, (long)Math.pow(10, digits));
    }

    public static long lastDigitsMul(long a, long b, int digits) {
        Preconditions.checkArgument(a>=0);
        Preconditions.checkArgument(b>=0);
        Preconditions.checkArgument(digits > 0);
        return innerLastDigitsMul(a, b, (long)Math.pow(10, digits));
    }

    private static long innerLastDigitsMul(long a, long b, long remainder) {
        if(b == 0) return 0;
        if(b == 1) return a%remainder;

        return ((innerLastDigitsMul(a, b >>> 1, remainder) << 1) + innerLastDigitsMul(a, b % 2, remainder))%remainder;
    }

    private static long innerLastDigitsPow(int base, int exponent, long remainder) {
        if (exponent == 1) return base;

        long result = innerLastDigitsPow(base, exponent >> 1, remainder);

        if (exponent % 2 == 0) {
            return innerLastDigitsMul(result, result, remainder);
        } else {
            return innerLastDigitsMul(result, result, remainder) * base % remainder;
        }
    }
}
