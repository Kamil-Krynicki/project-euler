package org.krynicki.euler;

import java.math.BigInteger;

/**
 * Created by K on 2016-10-10.
 */
public class PowerDigitSum {
    /*
    215 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

    What is the sum of the digits of the number 21000?
     */

    public static void main(String[] args){
        long t1 = System.currentTimeMillis();
        PowerDigitSum p = new PowerDigitSum(1000);
        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
    }


    public PowerDigitSum(int n){
        BigInteger val = BigInteger.valueOf(2).pow(n);

        BigInteger[] tmp;

        int sum = 0;

        do {
            tmp = val.divideAndRemainder(BigInteger.TEN);
            sum += tmp[1].intValue();
            val = tmp[0];

        } while(val.compareTo(BigInteger.ZERO) > 0);

        System.out.println(sum);

    }
}
