package org.krynicki.euler;

/**
 * Created by kamil.krynicki on 25/10/2016.
 */
public class Problem40_Champernowes {
    /*
    An irrational decimal fraction is created by concatenating the positive integers:

    0.123456789101112131415161718192021...

    It can be seen that the 12th digit of the fractional part is 1.

    If dn represents the nth digit of the fractional part, find the value of the following expression.

    d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000
    */

    public static void main(String[] args) {

        int result = 1;
        long t1 = System.nanoTime();
        Champernownes c = new Champernownes();
        for(int i=1;i<=1E6;i*=10)
        {
            result*=c.getDigit(i);
        }
        long t2 = System.nanoTime();
        System.out.println(result);
        System.out.println(t2 - t1);

    }

    public static class Champernownes {
        int[] numbersPerDigitLen;
        int[] base;

        public Champernownes() {
            this.base = new int[9];
            this.base[0] = 0;
            this.base[1] = 10;
            for (int i = 2; i < 9; i++) {
                base[i] = base[i-1]*10;
            }

            this.numbersPerDigitLen = new int[9];
            this.numbersPerDigitLen[0] = 0;
            for (int i = 1; i < 9; i++) {
                numbersPerDigitLen[i] = i * (base[i]-base[i-1]) + numbersPerDigitLen[i - 1];
            }
        }

        int getDigit(int digitNumber) {
            int numberSize = 0;

            do {
                numberSize++;
            } while (digitNumber > numbersPerDigitLen[numberSize]);

            int digit = (digitNumber - numbersPerDigitLen[numberSize - 1]) % numberSize;
            int number = (digitNumber - numbersPerDigitLen[numberSize - 1]) / numberSize;

            return String.valueOf(number + base[numberSize - 1]).toCharArray()[digit] - 0x30;
        }
    }
}
