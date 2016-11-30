package org.krynicki.euler;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * Created by K on 2016-11-30.
 */
public class Problem84_MonopolyOdds {
    public static final int SIDES = 4;

    private static List<String> CC = Arrays.asList("GO", "JAIL", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
    private static List<String> CH = Arrays.asList("GO", "JAIL", "C1", "E3", "H2", "R1", "nextR", "nextR", "nextU", "-3", "", "", "", "", "", "");
    private static List<String> board = Arrays.asList("GO", "A1", "CC1", "A2", "T1", "R1", "B1", "CH1", "B2", "B3", "JAIL", "C1", "U1",
            "C2", "C3", "R2", "D1", "CC2", "D2", "D3", "FP", "E1", "CH2", "E2", "E3", "R3", "F1", "F2", "U2", "F3",
            "G2J", "G1", "G2", "CC3", "G3", "R4", "CH3", "H1", "T2", "H2");

    private static Map<String, Integer> boardMap;

    public static void main(String[] args) throws IOException {

        int position = 0;

        boardMap = new HashMap<>();

        for(int i=0;i<board.size();i++) {
            boardMap.put(board.get(i), i);
        }

        long t1 = System.currentTimeMillis();

        int[] visits = new int[board.size()];
        visits[0]++;
        int totalVisits = 1;

        int consecutiveDoubles = 0;
        while (totalVisits < 1E8) {

            int diceA = dice(SIDES);
            int diceB = dice(SIDES);

            consecutiveDoubles = diceA == diceB ? consecutiveDoubles + 1 : 0;

            if (consecutiveDoubles == 3) {
                consecutiveDoubles = 0;
                position = goTo("JAIL");
            } else {
                position += diceA + diceB;
                position %= board.size();
                switch (board.get(position)) {
                    case "G2J":
                        position = goTo("JAIL");
                        break;
                    case "CC1":
                    case "CC2":
                    case "CC3":
                        position = cc(position);
                        break;
                    case "CH1":
                    case "CH2":
                    case "CH3":
                        position = ch(position);
                        if(board.get(position).equals("CC3")) {
                            position = cc(position);
                        }
                        break;
                }
            }

            visits[position]++;

            totalVisits++;
        }

        System.out.println(Arrays.toString(visits));


        IntStream.range(0, visits.length).boxed().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(visits[o2], visits[o1]);
            }
        }).forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer + " : "+board.get(integer) +" : "+ visits[integer]);
            }
        });



        long t2 = System.currentTimeMillis();
        System.out.println(t2- t1);
    }

    private static int cc(int position) {
        String card = CC.get(dice(CC.size())-1);

        if(card.length() == 0) {
            return position;
        } else {
            return goTo(card);
        }

    }

    private static int ch(int position) {
        String card = CH.get(dice(CH.size())-1);

        if(card.length() == 0) {
            return position;
        } else {
            switch (card) {
                case "-3" : return (position - 3 + board.size())%board.size();
                case "nextR" : return goTo("R", position);
                case "nextU" : return goTo("U", position);
                default: return goTo(card);
            }
        }
    }

    private static int goTo(String r, int after) {
        int i = after;
        while(!board.get(i).startsWith(r)) {
            i++;
            i%=board.size();
        }
        return i;
    }

    private static int goTo(String field) {
        return boardMap.get(field);
    }

    public static int dice(int sides) {
        return ThreadLocalRandom.current().nextInt(sides) + 1;
    }


}
