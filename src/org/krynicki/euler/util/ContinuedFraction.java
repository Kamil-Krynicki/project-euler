package org.krynicki.euler.util;

import java.util.Arrays;

/**
 * Created by K on 2016-11-17.
 */
public class ContinuedFraction {
    final int a0;
    final int[] ai;
    final int k;

    public ContinuedFraction(int a0, int... ai) {
        this.a0 = a0;
        this.ai = ai;
        this.k = ai.length;
    }

    public ContinuedFraction right() {
        if(k%2==1) {
            return childExpanded();
        } else {
            return childNotExpanded();
        }
    }

    public ContinuedFraction left() {
        if(k%2==0) {
            return childExpanded();
        } else {
            return childNotExpanded();
        }
    }

    private ContinuedFraction childNotExpanded() {
        int[] ai = Arrays.copyOf(this.ai, this.ai.length);
        ai[ai.length-1]++;
        return new ContinuedFraction(a0, ai);
    }

    private ContinuedFraction childExpanded() {
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
        private int num;
        private int den;

        public Fraction(int num, int den) {
            this.num = num;
            this.den = den;
        }

        public int num() {
            return num;
        }

        public int den() {
            return den;
        }

        public String toString() {
            return num +"/"+ den;
        }

        public double toDouble() {
            return (double) num / den;
        }
    }
}
