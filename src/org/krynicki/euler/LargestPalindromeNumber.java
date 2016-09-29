package org.krynicki.euler;

/**
 * Created by kamil.krynicki on 29/09/2016.
 */
public class LargestPalindromeNumber {
    /**
     * A palindromic number reads the same both ways.
     * The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
     *
     * Find the largest palindrome made from the product of two 3-digit numbers.
     */

    int invert(int s) {
        int result = 0;

        while(s!=0) {
            result+=s%10;
            result*=10;
            s/=10;
        }

        return (result/10);
    }

    int length(int s){
        int result = 0;

        while(s!=0) {
            s/=10;
            result++;
        }

        return result;
    }

    int glue(int s1, int s2) {
        return (int) (s1*Math.pow(10, length(s2))+s2);
    }

    void generatePalindromeNumbers(short digit) {
        int max = (int) Math.pow(10, length(digit) - 1);
        int min = (int) Math.pow(10, length(digit - 1));
    }


    static public void main(String[] args) {
        LargestPalindromeNumber t = new LargestPalindromeNumber();
        short s1 = (short) 542;
        System.out.println(t.glue(s1, t.invert(s1)));
    }

}




