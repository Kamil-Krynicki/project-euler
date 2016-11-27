package org.krynicki.euler;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kamil.krynicki on 24/11/2016.
 */
public class Problem79_PasscodeDerivation {
    /*
    A common security method used for online banking is to ask the user for three random characters from a passcode.
    For example, if the passcode was 531278, they may ask for the 2nd, 3rd, and 5th characters; the expected reply would be: 317.

    The text file, keylog.txt, contains fifty successful login attempts.

    Given that the three characters are always asked for in order, analyse the file so as to determine the shortest
    possible secret passcode of unknown length.
    */
    static String[] passCodes = {"319", "680","180","690","129","620","762","689","762","318","368","710","720","710","629","168",
            "160","689","716","731","736","729","316","729","729","710","769","290","719","680","318","389","162","289",
            "162","718","729","319","790","680","890","362","319","760","316","729","380","319","728","716"};


    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        Precedence dependencyMatrix = new Precedence();
        Set<Integer> candidates = new HashSet<>();

        for(String code:passCodes) {
            char[] chars = code.toCharArray();
            int before = chars[0] - 0x30;
            int after;
            candidates.add(before);
            for(int i = 1; i< chars.length; i++) {
                after = chars[i] - 0x30;
                dependencyMatrix.precedes(before, after);
                candidates.add(after);
                before = after;
            }
        }

        while(!candidates.isEmpty()) {
            for(int candidate:candidates) {
                if(dependencyMatrix.isFirst(candidate)) {
                    candidates.remove(candidate);
                    dependencyMatrix.remove(candidate);
                    System.out.print(candidate);
                    break;
                }
            }
        }
        System.out.println();

        long t2 = System.currentTimeMillis();

        System.out.println("==========");
        System.out.println(t2 - t1);
    }



    static class Precedence {
        public static final int DIGIT_COUNT = 10;
        boolean[][] state = new boolean[DIGIT_COUNT][DIGIT_COUNT];

        public void remove(int digit) {
            for(int i=0;i< DIGIT_COUNT;i++) {
                state[i][digit] = false;
                state[digit][i] = false;
            }
        }

        public void precedes(int before, int after) {
            state[before][after] = true;
        }

        public boolean isBefore(int digit, int after) {
            return state[digit][after];
        }

        public boolean isFirst(int digit) {
            for(int i=0;i< DIGIT_COUNT;i++) {
                if(isBefore(i, digit)) return false;
            }
            return true;
        }

        public boolean isLast(int digit) {
            for(int i=0;i< DIGIT_COUNT;i++) {
                if(isBefore(digit, i)) return false;
            }
            return true;
        }

        public boolean isUndetermined(int digit) {
            for(int i=0;i< DIGIT_COUNT;i++) {
                if(isBefore(i, digit) && isBefore(digit, i)) return true;
            }
            return false;
        }
    }


}
