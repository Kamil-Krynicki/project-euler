package org.krynicki.euler.Problems1to50;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

/**
 * Created by kamil.krynicki on 14/10/2016.
 */
public class Problem22_NameScore {

    public static final int DOUBLE_QUOTES = 2 * 'A' - 2 * '"' - 2;

    /*
    Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first
    names, begin by sorting it into alphabetical order. Then working out the alphabetical value for each name,
    multiply this value by its alphabetical position in the list to obtain a name score.

    For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53,
    is the 938th name in the list. So, COLIN would obtain a score of 938 × 53 = 49714.

    What is the total of all the name scores in the file?
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));

        long t1 = System.currentTimeMillis();
        String[] names = reader.readLine().split(",");
        Arrays.sort(names);
        int collect = IntStream
                .range(0, names.length)
                .map(new IntUnaryOperator() {
                    @Override
                    public int applyAsInt(int i) {
                        int sum = DOUBLE_QUOTES;
                        char[] chars = names[i].toCharArray();
                        for (char c : chars) {
                            sum += c;
                        }
                        return (i + 1) * (sum - ('A' - 1) * chars.length);
                    }
                })
                .sum();

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
        System.out.println(collect);
    }
}
