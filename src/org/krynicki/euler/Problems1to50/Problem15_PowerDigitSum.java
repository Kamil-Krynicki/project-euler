package org.krynicki.euler.Problems1to50;

import java.math.BigInteger;

/**
 * Created by K on 2016-10-10.
 */
public class Problem15_PowerDigitSum {
    /*
    215 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

    What is the sum of the digits of the number 21000?
     */

    public static void main(String[] args){
        long t1 = System.currentTimeMillis();
        Problem15_PowerDigitSum p = new Problem15_PowerDigitSum(1000);
        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
    }


    public Problem15_PowerDigitSum(int n){
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
