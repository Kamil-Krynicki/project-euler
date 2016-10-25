package org.krynicki.euler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamil.krynicki on 03/10/2016.
 */
public class Problem7_NthPrime {
    private List<Long> primes;
    public NthPrime(int n) {
        primes = new ArrayList<>(n);

        primes.add(2l);

        long i = 3;

        while(primes.size()<n) {
            if(!isDivisible(i, primes)) {
                primes.add(i);
            }
            i+=2;
        }
    }

    long get() {
        return primes.get(primes.size()-1);
    }

    private boolean isDivisible(long i, List<Long> primes) {
        for(Long prime:primes) {
            if(i%prime==0) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        NthPrime t = new NthPrime(10001);

        System.out.println(t.get());
    }
}
