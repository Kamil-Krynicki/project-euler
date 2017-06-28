package org.krynicki.euler.Other;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Threads;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Created by K on 20.06.2017.
 */
public class PrimePalindromes {
    private static final int MAX_VAL = 1661;

    public static void main(String... args) {
        long t1 = System.currentTimeMillis();

        System.out.println(find(MAX_VAL));

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    private static int find(int count) {
        byte[] digits = {3};
        int found = 1;
        int result = 0;

        while (found < count) {
            if (isOdd(digits) && isPrime(result = toInt(digits)))
                found++;

            digits = nextPalindrome(digits);
        }

        return result;
    }

    private static int findFunctional(int count) {
        return Stream.iterate(new byte[]{2}, PrimePalindromes::nextPalindrome)
                .filter(PrimePalindromes::isOdd)
                .mapToInt(PrimePalindromes::toInt)
                .filter(PrimePalindromes::isPrime)
                .limit(count)
                .max()
                .orElse(-1);
    }

    private static byte[] nextPalindrome(byte[] digits) {
        int len = digits.length;

        int i = (len - 1) / 2 + 1;

        while (--i >= 0 && digits[i] == 9)
            digits[len - i - 1] = digits[i] = 0;

        if (i < 0) {
            i = 0;
            len++;
            digits = new byte[len];
        }

        digits[len - i - 1] = digits[i] = (byte) (digits[i] + 1);

        return digits;
    }

    private static boolean isOdd(byte[] digits) {
        return (digits[digits.length - 1] & 1) == 1;
    }

    private static boolean isPrime(int value) {
        for (int i = 3; i <= Math.sqrt(value); i+=2)
            if (value % i == 0)
                return false;

        return true;
    }

    private static int toInt(byte[] digits) {
        int result = 0;

        for (byte d : digits) {
            result += d;
            result *= 10;
        }

        result /= 10;

        return result;
    }

    @Benchmark()
    @BenchmarkMode(value = Mode.AverageTime)
    @OutputTimeUnit(value = TimeUnit.MILLISECONDS)
    @Threads(value = 4)
    public void generationDeclarative() {
        find(MAX_VAL);
    }

    @Benchmark()
    @BenchmarkMode(value = Mode.AverageTime)
    @OutputTimeUnit(value = TimeUnit.MILLISECONDS)
    @Threads(value = 4)
    public void generationFunctional() {
        findFunctional(MAX_VAL);
    }

    @Test
    public void testDeclarative() {
        Assert.assertEquals(find(MAX_VAL), 159323951);
    }

    @Test
    public void testFunctional() {
        Assert.assertEquals(findFunctional(MAX_VAL), 159323951);
    }
}
