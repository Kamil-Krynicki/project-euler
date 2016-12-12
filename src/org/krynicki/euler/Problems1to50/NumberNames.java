package org.krynicki.euler.Problems1to50;

/**
 * Created by kamil.krynicki on 11/10/2016.
 */
public class NumberNames {
    /*
    If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.

    If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?


    NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters
    and 115 (one hundred and fifteen) contains 20 letters. The use of "and" when writing out numbers is in compliance with British usage.
    */

    public void main(String[] args) {
        new NumberNames(1000);
    }

    public NumberNames(int max) {
        if (max < 1) throw new IllegalArgumentException("max < 1");

        int sum = 0;

        for (int i = 1; i <= max; i++) {
            String s = numberName(i);
            System.out.println(i + " : " + s);
            sum += s.length();
        }

        System.out.println(sum);
    }

    public String numberName(int value) {
        String totalLen = "";
        int blockNumber = 0;
        int blockValue = 0;
        while (value != 0) {
            blockValue = value % 1000;
            value /= 1000;
            totalLen += blockValueName(blockValue) + blockName(blockNumber);
            blockNumber++;
        }

        return totalLen;
    }

    private String blockName(int blockNumber) {
        switch (blockNumber) {
            case 1:
                return "thousand";
            case 2:
                return "million";
            default:
                return "";
        }
    }

    private String blockValueName(int i) {
        if (i > 999) throw new IllegalArgumentException("i > 999");
        if (i < 0) throw new IllegalArgumentException("i < 0");

        int value = i;

        String result = "";

        int hundreds = value / 100;
        value -= hundreds * 100;
        int tens = value / 10;
        value -= tens * 10;
        int units = value;

        if (hundreds > 0) {
            result += unitsNames(hundreds) + "hundred";
        }

        if (hundreds > 0 && (tens > 0 || units > 0)) {
            result += "and";
        }

        if (tens > 1) {
            result += tensNames(tens) + unitsNames(units);
        } else if (tens == 1) {
            result += teenNames(tens * 10 + units);
        } else {
            result += unitsNames(units);
        }


        return result;
    }

    private String unitsNames(int i) {
        switch (i) {
            case 1:
                return "one";
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            case 6:
                return "six";
            case 7:
                return "seven";
            case 8:
                return "eight";
            case 9:
                return "nine";
            default:
                return "";
        }
    }

    private String teenNames(int i) {
        switch (i) {
            case 10:
                return "ten";
            case 11:
                return "eleven";
            case 12:
                return "twelve";
            case 13:
                return "thirteen";
            case 14:
                return "fourteen";
            case 15:
                return "fifteen";
            case 16:
                return "sixteen";
            case 17:
                return "seventeen";
            case 18:
                return "eighteen";
            case 19:
                return "nineteen";
            default:
                return "";
        }
    }

    private String tensNames(int i) {
        switch (i) {
            case 2:
                return "twenty";
            case 3:
                return "thirty";
            case 4:
                return "forty";
            case 5:
                return "fifty";
            case 6:
                return "sixty";
            case 7:
                return "seventy";
            case 8:
                return "eighty";
            case 9:
                return "ninety";
            default:
                return "";
        }
    }
}
