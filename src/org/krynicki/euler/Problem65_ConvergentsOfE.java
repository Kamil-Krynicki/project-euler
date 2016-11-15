package org.krynicki.euler;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by K on 2016-11-14.
 */
public class Problem65_ConvergentsOfE {
    /*
    Hence the sequence of the first ten convergents for √2 are:

    1, 3/2, 7/5, 17/12, 41/29, 99/70, 239/169, 577/408, 1393/985, 3363/2378, ...
    What is most surprising is that the important mathematical constant,
    e = [2; 1,2,1, 1,4,1, 1,6,1 , ... , 1,2k,1, ...].

    The first ten terms in the sequence of convergents for e are:

    2, 3, 8/3, 11/4, 19/7, 87/32, 106/39, 193/71, 1264/465, 1457/536, ...
    The sum of digits in the numerator of the 10th convergent is 1+4+5+7=17.

    Find the sum of digits in the numerator of the 100th convergent of the continued fraction for e.
     */
    static public void main(String... args) {
        final long t1 = System.currentTimeMillis();

        int[] eExpansion = new int[103];
        eExpansion[1] = 2;

        int k = 1;

        for(int i = 2; i<=100;) {
            eExpansion[i++] = 1;
            eExpansion[i++] = 2*k++;
            eExpansion[i++] = 1;
        }

        BigInteger denominator;
        BigInteger numerator;
        BigInteger tmp;
        int j = 100;
        denominator = BigInteger.ONE;
        numerator = BigInteger.valueOf(eExpansion[j]);

        for(int i=j-1;i>0;i--) {
            tmp = numerator;
            numerator = denominator;
            denominator = tmp;
            numerator = BigInteger.valueOf(eExpansion[i]).multiply(denominator).add(numerator);
        }

        int sum = 0;
        for(char c:numerator.toString().toCharArray()) {
            sum+=c-0x30;
        }

        final long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);

    }
}
