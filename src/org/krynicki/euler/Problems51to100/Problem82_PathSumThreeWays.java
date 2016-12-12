package org.krynicki.euler.Problems51to100;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by K on 2016-11-27.
 */
public class Problem82_PathSumThreeWays {


    static int[][] memo;
    static int[][] matrix;
    private static int size;
    private static Node sink;
    private static Node source;

    public static void main(String[] args) throws IOException {

        long t1 = System.currentTimeMillis();

        matrix = load(args[0]);

        //matrix = new int[][]{
        //{131, 673, 234, 103, 18 },
        //{201, 96,  342, 965, 150},
        //{630, 803, 746, 422, 111},
        //{537, 699, 497, 121, 956},
        //{805, 732, 524, 37,  331}};
        //size = 5;

        //printMatrix(matrix);
        toGraph(matrix);

        System.out.println(solve());

        long t2 = System.currentTimeMillis();
        System.out.print(t2 - t1);

    }

    private static int solve() {
        PriorityQueue<Node> frontier = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });

        frontier.add(source);
        Node best = null;
        while (best != sink) {
            best = frontier.poll();
            for (Node neighbour : best.neighbours()) {
                int newCost = best.cost + neighbour.value();
                if (neighbour.cost > newCost) {
                    neighbour.cost = newCost;
                    neighbour.parentPointer = best;
                    frontier.add(neighbour);
                }
            }
        }

        return best.cost;
    }

    static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    static Node toGraph(int[][] matrix) {
        Node[][] graphMatrix = new Node[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                graphMatrix[j][i] = new Node(j, i, matrix[i][j]);
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (j > 0) {
                    graphMatrix[i][j - 1].addNeighbour(graphMatrix[i][j]);
                }
                if (j < size - 1) {
                    graphMatrix[i][j + 1].addNeighbour(graphMatrix[i][j]);
                }
                if (i > 0) {
                    graphMatrix[i - 1][j].addNeighbour(graphMatrix[i][j]);
                }
            }
        }

        sink = new Node();

        for (int i = 0; i < size; i++) {
            graphMatrix[size - 1][i].addNeighbour(sink);
        }

        source = new Node();

        for (int i = 0; i < size; i++) {
            source.addNeighbour(graphMatrix[0][i]);
        }

        source.cost = 0;

        return source;
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
        private final int value;

        private Set<Node> neighbours;

        Node parentPointer;

        int cost;

        private Node() {
            this.value = 0;
            this.neighbours = new HashSet<>();

            parentPointer = null;
            cost = Integer.MAX_VALUE;
        }

        private Node(int x, int y, int value) {
            this.value = value;
            this.neighbours = new HashSet<>();

            parentPointer = null;
            cost = Integer.MAX_VALUE;
        }

        public void addNeighbour(Node node) {
            this.neighbours.add(node);
        }

        public int value() {
            return value;
        }

        public Iterable<Node> neighbours() {
            return neighbours;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "cost=" + cost +
                    '}';
        }
    }
}
