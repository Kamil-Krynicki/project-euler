package org.krynicki.euler.util;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by K on 2016-11-17.
 */
public class ContinuedFraction {
    final int a0;
    final int[] ai;

    public ContinuedFraction(int a0, int... ai) {
        this.a0 = a0;
        this.ai = ai;
    }

    public ContinuedFraction rightChild() {
        int[] ai = Arrays.copyOf(this.ai, this.ai.length);
        ai[ai.length-1]++;
        return new ContinuedFraction(a0, ai);
    }


    public ContinuedFraction leftChild() {
        int[] ai = Arrays.copyOf(this.ai, this.ai.length+1);
        ai[ai.length-2]--;
        ai[ai.length-1]=2;
        return new ContinuedFraction(a0, ai);
    }

    public ContinuedFraction parent() {
        if(ai[ai.length-1]>2) {
            int[] ai = Arrays.copyOf(this.ai, this.ai.length);
            ai[ai.length-1]--;
            return new ContinuedFraction(a0, ai);
        } else {
            int[] ai = Arrays.copyOf(this.ai, this.ai.length-1);
            ai[ai.length-1]++;
            return new ContinuedFraction(a0, ai);
        }
    }

    public Fraction value() {
        int den = 1;
        int num = 0;

        int tmp;
        for(int i = ai.length-1;i>=0;i--) {
            num += ai[i]*den;
            tmp = num;
            num = den;
            den = tmp;
        }

        num+= a0*den;

        return new Fraction(num, den);
    }

    public String toString() {
        return "["+a0+";"+Arrays.toString(ai)+"]";
    }

    public class Fraction {
        private int numerator;
        private int denomintor;

        public Fraction(int num, int den) {
            numerator = num;
            denomintor = den;
        }

        public String toString() {
            return numerator +"/"+denomintor;
        }

        public double toDouble() {
            return (double)numerator/denomintor;
        }
    }
}
