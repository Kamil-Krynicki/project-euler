package org.krynicki.euler;

import java.util.*;

/**
 * Created by K on 2016-10-27.
 */
public class Problem46_GoldbachsOtherConjecture {
    /*
    It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.

    9 = 7 + 2×12
    15 = 7 + 2×22
    21 = 3 + 2×32
    25 = 7 + 2×32
    27 = 19 + 2×22
    33 = 31 + 2×12

    It turns out that the conjecture was false.

    What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
    */

    public static void main(String[] args) {
        Problem46_GoldbachsOtherConjecture p = new Problem46_GoldbachsOtherConjecture();
        long t1 = System.currentTimeMillis();
        p.search();
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    public Problem46_GoldbachsOtherConjecture() {
        primes = new TreeSet<>();
        nonPrimes = new TreeSet<>();
        powers = new LinkedList<>();

        primes.add(2);
        primes.add(3);
        primes.add(5);
        primes.add(7);
        nonPrimes.add(9);
        powers.add(2);
        powers.add(8);
    }

    TreeSet<Integer> primes;
    TreeSet<Integer> nonPrimes;
    LinkedList<Integer> powers;


    void search() {
        int current = nonPrimes.last();

        while(findDecomposition(current)) {
            current = nextComplexOddNumber();
            generateDoublePowers(current);
        }
        System.out.println(current+" cannot be decomposed");
    }

    private boolean findDecomposition(int current) {
        for(int power: powers) {
            if(primes.contains(current-power)) {
                System.out.println(current+" can be decomposed as "+(current-power)+" + 2*"+(powers.indexOf(power)+1)+"^2");
                return true;
            }
        }
        return false;
    }

    private int nextComplexOddNumber() {
        int i = Math.max(primes.last(), nonPrimes.last())+2;
        while(isPrime(i)) {
            primes.add(i);
            i+=2;
        }
        nonPrimes.add(i);
        return i;
    }

    private boolean isPrime(int i) {
        for(int prime:primes.headSet(primes.higher((int) Math.sqrt(i)))) {
            if(i%prime==0) {
                return false;
            }
        }
        return true;
    }

    private void generateDoublePowers(int max) {
        while(powers.peekLast() < max) {
            powers.addLast(powers.peekLast() + 4* powers.size()+2);
        }
    }

}
