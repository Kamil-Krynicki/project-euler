package org.krynicki.euler.Problems1to50;

/**
 * Created by K on 2016-10-02.
 */
public class SumOfSquares {
    long result;

    public SumOfSquares(int max) {
        result = 0;
        for (int i = 1; i < max; i++) {
            final int constant = max * max + max;
            result += i * ((constant) - i*i - i);
        }
    }

    public long getResult() {
        return result;
    }

    public static void main(String[] args) {
        SumOfSquares s = new SumOfSquares(100);

        System.out.println(s.getResult());
    }
}
