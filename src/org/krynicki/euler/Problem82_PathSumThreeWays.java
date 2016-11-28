package org.krynicki.euler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by K on 2016-11-27.
 */
public class Problem82_PathSumThreeWays {


    static int[][] memo;
    static int[][] matrix;
    private static int size;

    public static void main(String[] args) throws IOException {

        long t1 = System.currentTimeMillis();

        //matrix = load(args[0]);

        matrix = new int[][]{
        {131, 673, 234, 103, 18 },
        {201, 96,  342, 965, 150},
        {630, 803, 746, 422, 111},
        {537, 699, 497, 121, 956},
        {805, 732, 524, 37,  331}};
        size = 5;



        printMatrix(matrix);
        System.out.println(solve());
        printMatrix(memo);

        long t2 = System.currentTimeMillis();
        System.out.print(t2-t1);

    }

    private static int solve() {
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost() - o2.cost();
            }
        });

        Map<Node, Integer> visited = new HashMap<>();

        for(int i=1;i<size;i++) {
            for (int j = 0; j < size ; j++) {
                visited.put(new Node(i, j), Integer.MAX_VALUE);
            }
        }

        for(int i=0;i<size;i++) {
            Node e = new Node(0, i, matrix[i][0]);
            queue.add(e);
            visited.put(e, matrix[i][0]);
        }

        while(true) {
            Node best = queue.poll();

            if(best.x == size - 1) {
                return best.cost();
            }

            Iterator<Node> neighbours = best.neighbours();
            Node toAdd;
            int stepCost;
            while(neighbours.hasNext()) {
                toAdd = neighbours.next();
                toAdd.setCost(matrix[toAdd.y][toAdd.x] + best.cost());
                if(visited.get(toAdd) > toAdd.cost()) {
                    visited.put(toAdd, toAdd.cost());
                    queue.remove(toAdd);
                    queue.add(toAdd);
                }
            }
        }
    }

    static void printMatrix(int[][] matrix) {
        for(int[] row: matrix) {
            System.out.println(Arrays.toString(row));
        }
    }


    static int[][] load(String path) throws IOException {
        int[][] result;
        BufferedReader reader = new BufferedReader(new FileReader(new File(path)));

        String line = reader.readLine();
        size = line.split(",").length;
        result = new int[size][size];

        int tmpY = 0;
        int tmpX = 0;
        do {
            tmpX = 0;
            for (String value : line.split(",")) {
                result[tmpY][tmpX++] = Integer.parseInt(value);
            }
            tmpY++;
        } while ((line = reader.readLine()) != null);

        return result;
    }

    private static class Node {
        final int x;
        final int y;
        int cost;

        private Node(int x, int y) {
            this.x = x;
            this.y = y;
            cost = Integer.MAX_VALUE;
        }

        private Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        public int value() {
            return matrix[y][x];
        }

        public int cost() {
            return cost;
        }

        public void setCost(int i) {
            this.cost = i;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (x != node.x) return false;
            if (y != node.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        public Iterator<Node> neighbours() {
            return new Iterator<Node>() {
                private int current = 0;

                private int[] xOffsets = {1, 0, 0};
                private int[] yOffsets = { 0,-1, 1};

                @Override
                public boolean hasNext() {
                    if(current >= xOffsets.length) {
                        return false;
                    }

                    while(( x + xOffsets[current]  < 0 || x + xOffsets[current] >= size) ||
                            ( y + yOffsets[current]  < 0 || y + yOffsets[current] >= size)) {
                        current++;
                        if(current == xOffsets.length) {
                            return false;
                        }
                    }

                    return true;
                }

                @Override
                public Node next() {
                    if(current ==  xOffsets.length) {
                        return null;
                    }

                    Node result = new Node(x + xOffsets[current], y + yOffsets[current++]);

                    current++;

                    return result;
                }
            };
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", cost=" + cost +
                    '}';
        }
    }
}
