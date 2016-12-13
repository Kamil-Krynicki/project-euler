package org.krynicki.euler.Problems51to100;

import org.krynicki.euler.util.RomanNumbers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by kamil.krynicki on 13/12/2016.
 */
public class Problem89_RomanNumerals {

    public static void main(String[] args) throws IOException {
        RomanNumbers converter = new RomanNumbers();
        long t1 = System.currentTimeMillis();

        List<String> numerals = Files.readAllLines(Paths.get(args[0]));

        int saved = 0;

        for(String numeral:numerals) {
            saved += numeral.length() - converter.toRoman(converter.fromRoman(numeral)).length();
        }

        System.out.println("saved:"+saved);

        long t2 = System.currentTimeMillis();
        System.out.print(t2 - t1);
    }
}
