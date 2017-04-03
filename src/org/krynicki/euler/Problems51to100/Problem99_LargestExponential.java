package org.krynicki.euler.Problems51to100;

import com.google.common.primitives.Doubles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by K on 30.03.2017.
 */

/*
Comparing two numbers written in index form like 211 and 37 is not difficult, as any calculator would confirm that
211 = 2048 < 37 = 2187.

However, confirming that 632382518061 > 519432525806 would be much more difficult, as both numbers contain over three
million digits.

Using base_exp.txt (right click and 'Save Link/Target As...'), a 22K text file containing one thousand lines with a
base/exponent pair on each line, determine which line number has the greatest numerical value.

NOTE: The first two lines in the file represent the numbers in the example given above.
 */
public class Problem99_LargestExponential {
    public static void main(String... args) throws IOException {

        long t1 = System.currentTimeMillis();


        BufferedReader fr = new BufferedReader(new FileReader(new File(args[0])));

        double base, baseMax = 1;
        double exp, expMax = 1;

        int lineNo = 1, lineNoMax = -1;

        String line;
        while ((line = fr.readLine()) != null) {

            String[] split = line.split(",");
            base = Integer.valueOf(split[0]);
            exp = Integer.valueOf(split[1]);

            if(compare(base, exp, baseMax, expMax) > 0) {
                baseMax = base;
                expMax = exp;
                lineNoMax = lineNo;
            }

            lineNo++;
        }

        long t2= System.currentTimeMillis();

        System.out.println(lineNoMax);

        System.out.println(t2 - t1);
    }

    public static int compare(double aBase, double aExponent, double bBase, double bExponent) {
        return Doubles.compare(aExponent * Math.log(aBase),  bExponent * Math.log(bBase));
    }
}
