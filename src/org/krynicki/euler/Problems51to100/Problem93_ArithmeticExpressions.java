package org.krynicki.euler.Problems51to100;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

        Set<Component> dupa = base.sumChildren();

        //System.out.println(dupa);

        Set<Component> dupa2 = new HashSet<>();
        Set<Component> dupa3 = new HashSet<>();
        Set<Component> dupa4 = new HashSet<>();

        System.out.println(dupa.size());


        for(Component a : dupa) {
            dupa2.addAll(a.children());
        }
        System.out.println(dupa2.size());

        for(Component a : dupa2) {
            dupa3.addAll(a.children());
        }
        System.out.println(dupa3.size());


        for(Component a : dupa3) {
            dupa4.addAll(a.children());
        }

        System.out.println(dupa4.size());
        System.out.println(dupa4);


        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    static class Component {
        private boolean[] digitsUsed;
        private int val;

        public Component(Component base, int digitUsed, int newVal) {
            this.digitsUsed = Arrays.copyOf(base.digitsUsed, 10);
            this.digitsUsed[digitUsed] = true;
            this.val = newVal;
        }

        public Component() {
            digitsUsed = new boolean[10];
            val = 0;
        }

        public Set<Component> children() {
            Set<Component> result = new HashSet<>();
                result.addAll(sumChildren());
                result.addAll(divChildren());
                result.addAll(mulChildren());
                result.addAll(subChildren());
            return result;
        }

        private Set<Component> sumChildren() {
            Set<Component> result = new HashSet<>();

            for(int i=0;i<10;i++) {
                if(!digitsUsed[i]) {
                    result.add(new Component(this, i, this.val+i));
                }
            }

            return result;
        }

        private Set<Component> mulChildren() {
            Set<Component> result = new HashSet<>();

            for(int i=0;i<10;i++) {
                if(!digitsUsed[i]) {
                    result.add(new Component(this, i, this.val*i));
                }
            }

            return result;
        }

        private Set<Component> divChildren() {
            Set<Component> result = new HashSet<>();

            for(int i=0;i<10;i++) {
                if(!digitsUsed[i]) {
                    if(i > 0 && this.val % i == 0) result.add(new Component(this, i, this.val/i));
                    if(this.val > 0 && i % this.val == 0) result.add(new Component(this, i, i/this.val));
                }
            }

            return result;
        }


        private Set<Component> subChildren() {
            Set<Component> result = new HashSet<>();

            for(int i=0;i<10;i++) {
                if(!digitsUsed[i]) {
                    if(this.val-i>=0) result.add(new Component(this, i, this.val-i));
                    if(i-this.val>=0) result.add(new Component(this, i, i-this.val));
                }
            }

            return result;
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

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(digitsUsed);
            result = 31 * result + val;
            return result;
        }

        public String toString() {
            return val + " with "+ digits().toString();
        }

        private StringBuilder digits() {
            StringBuilder result = new StringBuilder();

            for(int i=0;i<10;i++) {
                if(digitsUsed[i]) {
                    result.append(i);
                    result.append(";");
                }
            }

            return result;
        }
    }
}
