package org.krynicki.euler.Problems51to100;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by kamil.krynicki on 17/01/2017.
 */
public class Problem93_ArithmeticExpressions {
    /*
    By using each of the digits from the set, {1, 2, 3, 4}, exactly once, and making use of the four arithmetic
    operations (+, −, *, /) and brackets/parentheses, it is possible to form different positive integer targets.

    For example,

    8 = (4 * (1 + 3)) / 2
    14 = 4 * (3 + 1 / 2)
    19 = 4 * (2 + 3) − 1
    36 = 3 * 4 * (2 + 1)

    Note that concatenations of the digits, like 12 + 34, are not allowed.

    Using the set, {1, 2, 3, 4}, it is possible to obtain thirty-one different target numbers of which 36 is the maximum,
    and each of the numbers 1 to 28 can be obtained before encountering the first non-expressible number.

    Find the set of four distinct digits, a < b < c < d, for which the longest set of consecutive positive integers, 1 to n,
    can be obtained, giving your answer as a string: abcd.
    */

    // this would be much simpler in scala

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        Component base = new Component();


        Set<Component> digit1 = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            digit1.add(new Component(i));
        }

        final Set<Component> digit2;
        final Set<Component> digit3;
        final Set<Component> digit4;

        digit2 = digit1.stream().map(t -> t.children(digit1)).flatMap(t->t.stream()).collect(Collectors.toSet());
        digit3 = digit2.stream().map(t -> t.children(digit1)).flatMap(t->t.stream()).collect(Collectors.toSet());
        digit4 = digit3.stream().map(t -> t.children(digit1)).flatMap(t->t.stream()).collect(Collectors.toSet());
        digit4.addAll(digit2.stream().map(t -> t.children(digit2)).flatMap(t->t.stream()).collect(Collectors.toSet()));

        Map<String, List<Integer>> result = digit4.stream()
                .collect(Collectors.groupingBy(Component::getDigitsUsed, Collectors.mapping(Component::getVal, Collectors.toList())));

        Map.Entry<String, Integer> collect = result.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, t -> {
                    List<Integer> sorted = t.getValue().stream()
                            .filter(v -> v > 0)
                            .sorted()
                            .collect(Collectors.toList());
                    return sorted.stream()
                            .filter(v -> sorted.indexOf(v) == v - 1)
                            .collect(Collectors.toList()).size();
                }))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get();

        System.out.println(collect);

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    static private String digits(boolean[] digitsUsed) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            if (digitsUsed[i]) {
                result.append(i);
                result.append(";");
            }
        }

        return result.toString();
    }

    static class Component {
        private boolean[] digitsUsed;
        private int val;

        public String getDigitsUsed() {
            return digits(digitsUsed);
        }

        public int getVal() {
            return val;
        }

        public Component(int digitUsed) {
            this.digitsUsed = new boolean[10];
            this.digitsUsed[digitUsed] = true;
            this.val = digitUsed;
        }

        public Component(Component baseA, Component baseB, int newVal) {
            this.digitsUsed = new boolean[10];
            for (int i = 0; i < 10; i++) {
                this.digitsUsed[i] = baseA.digitsUsed[i] || baseB.digitsUsed[i];
            }
            this.val = newVal;
        }

        public Component() {
            digitsUsed = new boolean[10];
            val = 0;
        }

        public Set<Component> children(Set<Component> toCombine) {
            Set<Component> result = new HashSet<>();
            result.addAll(sumChildren(toCombine));
            result.addAll(divChildren(toCombine));
            result.addAll(mulChildren(toCombine));
            result.addAll(subChildren(toCombine));
            return result;
        }

        private Set<Component> sumChildren(Set<Component> toCombine) {
            return toCombine.stream()
                    .filter(this::canCombine)
                    .map(t -> new Component(t, this, this.val + t.val))
                    .collect(Collectors.toSet());
        }

        private Set<Component> mulChildren(Set<Component> toCombine) {
            return toCombine.stream()
                    .filter(this::canCombine)
                    .map(t -> new Component(this, t, this.val * t.val))
                    .collect(Collectors.toSet());
        }

        private Set<Component> divChildren(Set<Component> toCombine) {
            Set<Component> result = toCombine.stream()
                    .filter(t -> t.val > 0)
                    .filter(this::canCombine)
                    .filter(t -> this.val % t.val == 0)
                    .map(t -> new Component(this, t, this.val / t.val))
                    .collect(Collectors.toSet());

            if(this.val > 0) {
                result.addAll(toCombine.stream()
                        .filter(this::canCombine)
                        .filter(t -> t.val % this.val == 0)
                        .map(t -> new Component(this, t, t.val / this.val))
                        .collect(Collectors.toSet()));
            }

            return result;
        }

        private Set<Component> subChildren(Set<Component> toCombine) {
            return toCombine.stream()
                    .filter(this::canCombine)
                    .map(t -> Arrays.asList(
                            new Component(this, t, this.val - t.val),
                            new Component(this, t, t.val - this.val)
                    ))
                    .flatMap(t -> t.stream())
                    .collect(Collectors.toSet());
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Component component = (Component) o;

            if (val != component.val) return false;
            if (!Arrays.equals(digitsUsed, component.digitsUsed)) return false;

            return true;
        }

        private boolean canCombine(Component that) {
            for (int i = 0; i < 10; i++) {
                if (this.digitsUsed[i] && that.digitsUsed[i]) return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(digitsUsed);
            result = 31 * result + val;
            return result;
        }

        public String toString() {
            return val + " with " + digits(digitsUsed);
        }
    }
}
