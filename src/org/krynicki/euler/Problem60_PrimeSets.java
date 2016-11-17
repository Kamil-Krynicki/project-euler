package org.krynicki.euler;

import org.krynicki.euler.util.PrimeGenerator;

import java.util.Arrays;

/**
 * Created by K on 2016-11-09.
 */
public class Problem60_PrimeSets {
    /*
    The primes 3, 7, 109, and 673, are quite remarkable. By taking any two primes and concatenating them in
    any order the result will always be prime. For example, taking 7 and 109, both 7109 and 1097 are prime.
    The sum of these four primes, 792, represents the lowest sum for a set of four primes with this property.

    Find the lowest sum for a set of five primes for which any two primes concatenate to produce another prime.
    */

    static PrimeGenerator generator = new PrimeGenerator(10000);

    public static void main(String... args) {
        final long t1 = System.currentTimeMillis();

        int[] vals = {0, 0, 0, 0, 0};

        resolve(vals, 0);

        int sum = 0;
        for (int val: vals) {
            sum += val;
        }

        System.out.println(sum);

        final long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
    }

    static boolean resolve(int[] vals, int num) {
        if(num == 5) {
            System.out.println(Arrays.toString(vals));
            return true;
        }

        int currentPrime = generator.nextPrime(num>0?vals[num-1]:2);

        while(currentPrime < 10000) {
            if (areAllConcatsPrime(vals, num, currentPrime)) {
                vals[num] = currentPrime;
                if(resolve(vals, num+1)){
                    return true;
                }
            }

            currentPrime = generator.nextPrime(currentPrime);
        }
        return false;
    }

    private static boolean areAllConcatsPrime(int[] vals, int num, int currentPrime) {
        for(int i = 0; i<num; i++) {
            if(!isConcatPrime(vals[i], currentPrime)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isConcatPrime(int val1, int val2) {
        return checkPrime(Integer.valueOf(val1+""+val2)) && checkPrime(Integer.valueOf(val2+""+val1));
    }

    private static boolean checkPrime(int i) {
        if(i < 10000) {
            return generator.isPrime(i);
        }
        for (int num = 2; num < Math.sqrt(i) ; num ++) {
            if (i % num == 0) {
                return false;
            }
        }
        return true;
    }


}
