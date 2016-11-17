package org.krynicki.euler.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by K on 2016-11-17.
 */
public class CombinationGenerator {
    private int[] components;
    private int base;
    private List<Integer> values;

    public CombinationGenerator(int[] components) {
        Arrays.sort(components);
        this.components = components;
        this.base = components.length;
        this.values = new ArrayList<>();
    }

    public int next() {
        increment();
        return decode(values);
    }

    private int decode(List<Integer> values) {
        int result = 0;

        for (int i = values.size() - 1; i >= 0; i--) {
            result *= 10;
            result += components[values.get(i)];
        }

        return result;
    }

    private void increment() {
        increment(values, 0);
    }

    private void increment(List<Integer> values, int i) {
        if (values.size() == i) {
            values.add(0);
        } else {
            values.set(i, values.get(i) + 1);
            if (values.get(i) >= base) {
                values.set(i, values.get(i) - base);
                increment(values, i + 1);
            }
        }
    }
}
