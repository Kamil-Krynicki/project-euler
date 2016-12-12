package org.krynicki.euler.Problems1to50;

/**
 * Created by K on 2016-09-28.
 */
public class Problem1_Multiples3And5 {

    /*
    * If we list all the natural numbers below 10 that are multiples of 3 or 5,
    * we get 3, 5, 6 and 9. The sum of these multiples is 23.
    *
    * Find the sum of all the multiples of 3 or 5 below 1000.
    */

    int sumMultiples(int val, int max) {
        int repetitions = (max - 1) / val;
        return val * repetitions * (repetitions + 1) / 2;
    }


    static public void main(String[] args) {
        Problem1_Multiples3And5 tester = new Problem1_Multiples3And5();

        System.out.println(tester.sumMultiples(3, 1000) + tester.sumMultiples(5, 1000) - tester.sumMultiples(15, 1000));
    }
}
