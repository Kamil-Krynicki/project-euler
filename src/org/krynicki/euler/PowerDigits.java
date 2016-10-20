package org.krynicki.euler;

/**
 * Created by kamil.krynicki on 20/10/2016.
 */
public class PowerDigits {
    /*
    Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:

    1634 = 14 + 64 + 34 + 44
    8208 = 84 + 24 + 04 + 84
    9474 = 94 + 44 + 74 + 44

    As 1 = 14 is not a sum it is not included.

    The sum of these numbers is 1634 + 8208 + 9474 = 19316.

    Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
    */

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        PowerDigits f = new PowerDigits(5);
        int sum = 0;
        for(int i=2;i<f.maxDigitPowerSum();i++) {
            if(i == f.sumDigitPowers(i)) {
                sum +=i;
            }
        }
        System.out.println("Sum of digits is "+sum);

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    private int[] powers;

    public PowerDigits(int power) {
        powers = new int[10];

        for (int i = 1; i < 10; i++) {
            powers[i] = (int) Math.pow(i, power);
        }
    }

    public int sumDigitPowers(int number) {
        int sum = 0;
        int tmp = number;
        while(tmp != 0) {
            sum+=powers[tmp%10];
            tmp/=10;
        }
        return sum;
    }

    public int maxDigitPowerSum() {
        int sum = 0;
        int maxValue = 1;
        do {
            sum += powers[9];
            maxValue *= 10;
        } while (sum > maxValue);
        return sum;
    }
}
