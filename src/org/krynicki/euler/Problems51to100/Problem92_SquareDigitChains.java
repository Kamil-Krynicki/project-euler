package org.krynicki.euler.Problems51to100;

/**
 * Created by kamil.krynicki on 17/01/2017.
 */
public class Problem92_SquareDigitChains {
    private static int[] squares;
    private static int[] memo;
    private static int result;
    /*
    A number chain is created by continuously adding the square of the digits in a number to form a new number until it has been seen before.

    For example,

    44 → 32 → 13 → 10 → 1 → 1
    85 → 89 → 145 → 42 → 20 → 4 → 16 → 37 → 58 → 89

    Therefore any chain that arrives at 1 or 89 will become stuck in an endless loop. What is most amazing is that EVERY starting number will eventually arrive at 1 or 89.

    How many starting numbers below ten million will arrive at 89?
    */

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        squares = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int i = 0; i < squares.length; i++) {
            squares[i] *= squares[i];
        }

        result = 0;

        double max = 1E7;
        memo = new int[(int) max];
        memo[1] = 1;
        memo[89] = 89;


        for(int i = 1; i< max;i++) {
            if(resolve(i) == 89) result++;
        }

        System.out.println(result);
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    public static int resolve(int in) {
        if(memo[in] == 1) return 1;
        if(memo[in] == 89) return 89;
        return (memo[in] = resolve(next(in)));
    }

    public static int next(int in) {
        int result = 0;
        int tmp = in;

        while (tmp > 0) {
            result += squares[tmp % 10];
            tmp /= 10;
        }

        return result;
    }
}
