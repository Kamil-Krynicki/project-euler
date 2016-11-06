package org.krynicki.euler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
 * Created by K on 2016-11-04.
 */
public class Problem54_PokerHands {
    public static void main(String[] args) throws IOException {

        long t1 = System.currentTimeMillis();

        BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));

        Hand player1;
        Hand player2;

        String line;
        String[] cards;

        Comparator<Hand> comparator = new CompareHandByValue();

        int player1victories = 0;

        while ((line = reader.readLine()) != null) {
            cards = line.split(" ");

            player1 = new Hand(Arrays.copyOfRange(cards, 0, 5));
            player2 = new Hand(Arrays.copyOfRange(cards, 5, 10));

            System.out.println("======================");
            System.out.println(player1);
            System.out.println(player2);

            if (comparator.compare(player1, player2) < 0) {
                player1victories++;
                System.out.println("Player 1 victory");
            } else {
                System.out.println("Player 2 victory");
            }
        }

        System.out.println(player1victories);

        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
    }

    static class CompareHandByValue implements Comparator<Hand> {
        @Override
        public int compare(Hand hand1, Hand hand2) {
            if (hand1.totalWeight() != hand2.totalWeight()) {
                return hand2.totalWeight() - hand1.totalWeight();
            } else {
                Queue<HandItem> o1bestItems = new LinkedList<>(hand1.handItems);
                Queue<HandItem> o2BestItems = new LinkedList<>(hand2.handItems);

                while (!o1bestItems.isEmpty() && !o2BestItems.isEmpty()) {
                    HandItem item1 = o1bestItems.poll();
                    HandItem item2 = o2BestItems.poll();

                    if (item1.weight() != item2.weight()) {
                        return item2.weight() - item1.weight();
                    } else if (item1.value() != item2.value()) {
                        return item2.value() - item1.value();
                    }
                }

                if (!o1bestItems.isEmpty()) {
                    return -1;
                }

                if (!o2BestItems.isEmpty()) {
                    return 1;
                }

                return 0;
            }
        }
    }

    static class Hand {
        private List<HandItem> handItems;

        public Hand(String[] cards) {
            handItems = new LinkedList<>();

            short[] values = new short[15];
            short[] decks = new short[4];

            for (String card : cards) {
                values[value(card.charAt(0))]++;
                decks[deck(card.charAt(1))]++;
            }

            for (int i = 0; i < values.length; i++) {
                if (values[i] > 0) {
                    handItems.add(new CardSet(i, values[i]));
                }
            }

            if (isFlush(decks)) {
                handItems.add(new Flush(maxValue(values)));
            }

            if (isStraight(values)) {
                handItems.add(new CardSequence(maxValue(values)));
            }

            Collections.sort(handItems, new Comparator<HandItem>() {
                @Override
                public int compare(HandItem o1, HandItem o2) {
                    return o2.value() - o1.value();
                }
            });

            Collections.sort(handItems, new Comparator<HandItem>() {
                @Override
                public int compare(HandItem o1, HandItem o2) {
                    return o2.weight() - o1.weight();
                }
            });
        }

        public int totalWeight() {
            int result = 0;
            for (HandItem ht : handItems) {
                result += ht.weight();
            }
            return result;
        }

        private boolean isFlush(short[] decks) {
            int i = decks.length - 1;

            while (decks[i] == 0 && i-- >= 0);
            while (--i >= 0) if (decks[i] > 0) return false;

            return true;
        }

        private boolean isStraight(short[] values) {
            int i = values.length - 1;

            while (values[i] == 0 && i-- >= 0);
            while (values[i] == 1 && i-- >= 0);
            while (i >= 0) if (values[i--] > 0) return false;

            return true;
        }

        private int maxValue(short[] values) {
            int result = values.length-1;

            while (values[result] == 0) result--;

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

abstract class HandItem {
    private int value;

    public HandItem(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    abstract int weight();

    @Override
    public String toString() {
        return "{" + value + "}";
    }
}

class CardSet extends HandItem {
    private short count;

    public CardSet(int value, short count) {
        super(value);
        this.count = count;
    }

    @Override
    int weight() {
        switch (count) {
            case 1:
                return 1;
            case 2:
                return 3;
            case 3:
                return 7;
            case 4:
                return 15;
            default:
                return -1;
        }
    }

    @Override
    public String toString() {
        return "Set of " + count + " cards " + super.toString();
    }
}

class Flush extends HandItem {
    public Flush(int value) {
        super(value);
    }

    @Override
    int weight() {
        return 9;
    }

    @Override
    public String toString() {
        return "Flush with max" + super.toString();
    }

}

class CardSequence extends HandItem {
    public CardSequence(int value) {
        super(value);
    }

    @Override
    int weight() {
        return 8;
    }

    @Override
    public String toString() {
        return "CardSequence from " + super.toString() + " down";
    }
}
