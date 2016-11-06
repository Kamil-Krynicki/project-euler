package org.krynicki.euler;

import java.io.IOException;
import java.util.*;


/**
 * Created by K on 2016-11-04.
 */
public class Problem54_PokerHands {
    public static void main(String[] args) throws IOException {

        long t1 = System.currentTimeMillis();

        List<Hand> hands = new LinkedList<>();
        hands.add(new Hand(new char[][]{"4D".toCharArray(),
                "4S".toCharArray(),
                "4C".toCharArray(),
                "5H".toCharArray(),
                "5D".toCharArray()}));
        hands.add(new Hand (new char[][]{"4D".toCharArray(),
                "5S".toCharArray(),
                "6C".toCharArray(),
                "7H".toCharArray(),
                "8D".toCharArray()}));

        hands.add(new Hand (new char[][]{"4D".toCharArray(),
                "4S".toCharArray(),
                "4C".toCharArray(),
                "4H".toCharArray(),
                "8D".toCharArray()}));

        hands.add(new Hand (new char[][]{"4D".toCharArray(),
                "5D".toCharArray(),
                "6D".toCharArray(),
                "7D".toCharArray(),
                "8D".toCharArray()}));

        hands.add(new Hand (new char[][]{"AD".toCharArray(),
                "KD".toCharArray(),
                "QD".toCharArray(),
                "JD".toCharArray(),
                "TD".toCharArray()}));

        long t2 = System.currentTimeMillis();


        Collections.sort(hands, new Comparator<Hand>() {
            @Override
            public int compare(Hand o1, Hand o2) {
                return o2.totalWeight-o1.totalWeight;
            }
        });
        System.out.print(hands);

        Collections.sort(hands, new Comparator<Hand>() {
            @Override
            public int compare(Hand o1, Hand o2) {
                if(o1.totalWeight!=o2.totalWeight) {
                    return o2.totalWeight-o1.totalWeight;
                } else {
                return 0;
                }

            }
        });
        System.out.print(hands);
        System.out.println(t2 - t1);
    }

    static class Hand {
        @Override
        public String toString() {
            return "Hand{" +
                    "totalWeight=" + totalWeight +
                    ", types=" + types +
                    '}';
        }

        short[] decks = new short[4];
        short[] values = new short[15];

        int totalWeight;

        SortedSet<HandType> types = new TreeSet<>();

        public Hand(char[][] cardCodes) {
            short value;
            for(char[] cardCode:cardCodes) {
                decks[deck(cardCode[1])]++;
                value = value(cardCode[0]);
                values[value]++;
//                types.add(new Cards(value));
            }
            addMultiCard();
            addFlush();
            addStraight();

            totalWeight = totalWeigth();
        }

        private int totalWeigth() {
            int result = 0;
            for(HandType ht:types) {
                result+=ht.weight();
            }
            return result;
        }

        private void addMultiCard() {
            for(int i = 0;i<values.length;i++) {
                if(values[i]>1) {
                    types.add(new Cards(i, values[i]));
                }
            }
        }

        private void addFlush() {
            if(isFlush()) {
                types.add(new Flush(maxValue()));
            }
        }

        private boolean isFlush() {
            int i = decks.length-1;

            while(decks[i]==0 && i>=0) {
                i--;
            }

            i--;

            while(i>=0) {
                if(decks[i--]>0) return false;
            }

            return true;
        }

        private void addStraight() {
            if(isStraight()) {
                types.add(new Straight(maxValue()));
            }
        }

        private boolean isStraight() {
            int i = values.length-1;

            while(values[i]==0 && i>=0) {
                i--;
            }

            while(values[i]==1 && i>=0) {
                i--;
            }

            while(i>=0) {
                if(values[i--]>0) return false;
            }

            return true;
        }

        private int maxValue() {
            int result = 14;

            while(values[result] == 0) {
                result--;
            }

            return result;
        }

        private short value(char c) {
            switch (c) {
                case 'A':
                    return 14;
                case 'K':
                    return 13;
                case 'Q':
                    return 12;
                case 'J':
                    return 11;
                case 'T':
                    return 10;
                default:
                    return (short) (c - 0x30);
            }
        }

        private short deck(char c) {
            switch (c) {
                case 'D':
                    return 0;
                case 'S':
                    return 1;
                case 'H':
                    return 2;
                case 'C':
                    return 3;
            }
            return -1;
        }
    }
}

abstract class HandType implements Comparable<HandType> {
    private int value;

    public HandType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


    abstract int weight();

    @Override
    public String toString() {
        return "HandType{" +
                "value=" + value +
                '}';
    }


    @Override
    public int compareTo(HandType o) {
        return o.weight()-this.weight();
    }
}

class Cards extends HandType {
    private short count;

    public Cards(int value) {
        super(value);
        count = 1;
    }

    @Override
    int weight() {
        switch (count) {
            case 1:return 0;
            case 2:return 3;
            case 3:return 7;
            case 4:return 15;
        }
        return -1;
    }

    public Cards(int value, short count) {
        super(value);
        this.count = count;
    }

    @Override
    public String toString() {
        return "Set of "+count +" cards " + super.toString();
    }
}

class Flush extends HandType {
    public Flush(int value) {
        super(value);
    }

    @Override
    int weight() {
        return 9;
    }

    @Override
    public String toString() {
        return "Flush "+ super.toString();
    }

}

class Straight extends HandType {
    public Straight(int value) {
        super(value);
    }

    @Override
    int weight() {
        return 8;
    }
    @Override
    public String toString() {
        return "Straight "+ super.toString();
    }
}
