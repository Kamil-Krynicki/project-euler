package org.krynicki.euler;

import org.krynicki.euler.util.PrimeGenerator;

import java.util.Iterator;
import java.util.TreeSet;

import static org.krynicki.euler.QuadraticPrimes.PrimeGenerator;

/**
 * Created by K on 2016-11-17.
 */
public class Problem70_TotientPermutation {
    public static void main(String[] args) {
        long max = (long) 1E7;
        long t1 = System.currentTimeMillis();


        int n;
        int phiN;
        double nOverPhiN;

        double minRatio = Double.MAX_VALUE;
        int minN = -1;
        int minPhiN = -1;

        int p1;
        int p2;

        PrimeGenerator generator = PrimeGenerator.getPrimesBelow((int) Math.sqrt(max));

        Iterator<Integer> smallPrimes = new TreeSet<>(generator
                                        .getPrimes()
                                        .tailSet((int) Math.sqrt(max) / 2))
                                    .descendingIterator();

        while (smallPrimes.hasNext()) {
            p1 = smallPrimes.next();
            p2 = p1;
            while ((n = p1 * p2) < max) {
                if (isPermutation(n, phiN = n - p1 - p2 + 1)) {
                    if ((nOverPhiN = (double) n / phiN) < minRatio) {
                        minRatio = nOverPhiN;
                        minN = n;
                        minPhiN = phiN;
                    }
                }
                p2 = generator.nextPrime(p2);
            }
        }

        System.out.println(" n        = " + minN);
        System.out.println(" phi(n)   = " + minPhiN);
        System.out.println(" n/phi(n) = " + minRatio);


        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
    }

    static boolean isPermutation(int a, int b) {
        if (String.valueOf(a).length() != String.valueOf(b).length()) {
            return false;
        }

        byte digits[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (; a != 0; a /= 10) {
            ++digits[a % 10];
        }

        for (; b != 0; b /= 10) {
            if (digits[b % 10]-- == 0)
                return false;
        }

        return true;
    }
}
