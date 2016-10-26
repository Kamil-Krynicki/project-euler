package org.krynicki.euler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kamil.krynicki on 26/10/2016.
 */
public class Problem42_CodedTriangleNumbers {
    /*
    The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1);
    so the first ten triangle numbers are:

    1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

    By converting each letter in a word to a number corresponding to its alphabetical
    position and adding these values we form a word value. For example, the word value
    for SKY is 19 + 11 + 25 = 55 = t10. If the word value is a triangle number then we shall call the word a triangle word.

    Using words.txt (right click and 'Save Link/Target As...'), a 16K text
    file containing nearly two-thousand common English words, how many are triangle words?
    */

    private Set<Integer> triangleNumbers;

    public static void main(String[] args) throws IOException {
        Problem42_CodedTriangleNumbers p = new Problem42_CodedTriangleNumbers();

        BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));

        String[] names = reader.readLine().split(",");
        long t1 = System.nanoTime();
        p.generateNumbers(p.getLetterSum("ZZZZZZZZZZ".toCharArray()));
        int count = 0;

        for(String name : names) {
            int letterSum = p.getLetterSum(name.toCharArray());
            if(p.isTriangle(letterSum)) {
                count++;
            }
        }

        long t2 = System.nanoTime();

        System.out.println(t2 - t1);
        System.out.println(count);
    }

    public boolean isTriangle(int n) {
        return triangleNumbers.contains(n);
    }

    void generateNumbers(int max) {
        triangleNumbers = new HashSet<>();

        int i = 1;
        int nextNumber;

        while((nextNumber = i++*i >> 1) < max) {
            triangleNumbers.add(nextNumber);
        }
    }

    int getLetterSum(char[] letters) {
        int result = ('A'-1)*(2-letters.length);
        for(int i = 1; i < letters.length-1;i++) {
            result += letters[i];
        }
        return result;
    }
}
