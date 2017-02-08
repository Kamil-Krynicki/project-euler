package org.krynicki.euler.Problems51to100;

import org.krynicki.euler.util.LastDigits;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by K on 2017-02-08.
 */
public class Problem97_LargeNonMersenePrime {
    /*
    The first known prime found to exceed one million digits was discovered in 1999, and is a Mersenne prime of the
    form 26972593−1; it contains exactly 2,098,960 digits. Subsequently other Mersenne primes, of the form 2p−1,
    have been found which contain more digits.

    However, in 2004 there was found a massive non-Mersenne prime which contains 2,357,207 digits: 28433×2^7830457+1.

    Find the last ten digits of this prime number.
    */

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        //System.out.println( LastDigits.lastDigitsPow(2,2009, 10));
        System.out.println(LastDigits.lastDigitsMul(28433, LastDigits.lastDigitsPow(2,7830457, 10), 10) + 1);

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }
}
