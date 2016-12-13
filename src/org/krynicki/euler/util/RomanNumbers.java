package org.krynicki.euler.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
* Created by kamil.krynicki on 13/12/2016.
*/
public class RomanNumbers {
    Map<Integer, String> substitutions;

    public RomanNumbers() {
        substitutions = new LinkedHashMap<>();

        substitutions.put(1000,"M");
        substitutions.put(900,"CM");
        substitutions.put(500,"D");
        substitutions.put(400,"CD");
        substitutions.put(100,"C");
        substitutions.put(90,"XC");
        substitutions.put(50,"L");
        substitutions.put(40,"XL");
        substitutions.put(10,"X");
        substitutions.put(9,"IX");
        substitutions.put(5,"V");
        substitutions.put(4,"IV");
        substitutions.put(1,"I");
    }

    public String toRoman(int i) {
        return toRomanIter(new StringBuffer(), i);
    }

    private String toRomanIter(StringBuffer acc, int i) {
        for(Map.Entry<Integer, String> entry:substitutions.entrySet())
            if (i >= entry.getKey())
                return toRomanIter(acc.append(entry.getValue()), i - entry.getKey());
        return acc.toString();
    }

    public int fromRoman(String romanNumeral) {
        return fromRomanIter(romanNumeral, 0);
    }

    private int fromRomanIter(String romanNumeral, int acc) {
        for(Map.Entry<Integer, String> entry:substitutions.entrySet())
            if (romanNumeral.startsWith(entry.getValue()))
                return fromRomanIter(romanNumeral.substring(entry.getValue().length()), acc + entry.getKey());
        return acc;
    }
}
