package org.krynicki.euler.Problems1to50;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamil.krynicki on 11/10/2016.
 */
public class Problem18_PathInTriangle {
    /*
        By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.

        3
        7 4
        2 4 6
        8 5 9 3

        That is, 3 + 7 + 4 + 9 = 23.

        Find the maximum total from top to bottom of the triangle below:

        75
        95 64
        17 47 82
        18 35 87 10
        20 04 82 47 65
        19 01 23 75 03 34
        88 02 77 73 07 63 67
        99 65 04 28 06 16 70 92
        41 41 26 56 83 40 80 70 33
        41 48 72 33 47 32 37 16 94 29
        53 71 44 65 25 43 91 52 97 51 14
        70 11 33 28 77 73 17 78 39 68 17 57
        91 71 52 38 17 14 91 43 58 50 27 29 48
        63 66 04 68 89 53 67 30 73 16 69 87 40 31
        04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
     */


    public static void main(String[] args) throws IOException {

        List<List<Short>> in = new ArrayList<>();

        //in.add(Arrays.asList(new Short[]{75}));
        //in.add(Arrays.asList(new Short[]{95,64}));
        //in.add(Arrays.asList(new Short[]{17,47,82}));
        //in.add(Arrays.asList(new Short[]{18,35,87,10}));
        //in.add(Arrays.asList(new Short[]{20, 4,82,47,65}));
        //in.add(Arrays.asList(new Short[]{19, 1,23,75, 3,34}));
        //in.add(Arrays.asList(new Short[]{88, 2,77,73, 7,63,67}));
        //in.add(Arrays.asList(new Short[]{99,65, 4,28, 6,16,70,92}));
        //in.add(Arrays.asList(new Short[]{41,41,26,56,83,40,80,70,33}));
        //in.add(Arrays.asList(new Short[]{41,48,72,33,47,32,37,16,94,29}));
        //in.add(Arrays.asList(new Short[]{53,71,44,65,25,43,91,52,97,51,14}));
        //in.add(Arrays.asList(new Short[]{70,11,33,28,77,73,17,78,39,68,17,57}));
        //in.add(Arrays.asList(new Short[]{91,71,52,38,17,14,91,43,58,50,27,29,48}));
        //in.add(Arrays.asList(new Short[]{63,66, 4,68,89,53,67,30,73,16,69,87,40,31}));
        //in.add(Arrays.asList(new Short[]{04,62,98,27,23, 9,70,98,73,93,38,53,60, 4,23}));

        BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));

        long t1 = System.currentTimeMillis();
        String line;
        List<Short> newList;
        while ((line = reader.readLine()) != null) {
            newList = new ArrayList<>();

            for(String value:line.split(" ")) {
                newList.add(Short.valueOf(value));
            }

            in.add(newList);
        }
        long t2 = System.currentTimeMillis();
        System.out.print(t2-t1);

        Problem18_PathInTriangle pathInTriangle = new Problem18_PathInTriangle(in);
        pathInTriangle.maxPath();
    }

    private short[][] values;
    private int[][] memo;

    public Problem18_PathInTriangle(List<List<Short>> in) {
        int rowCount = in.size();

        this.values = new short[rowCount][];
        this.memo = new int[rowCount][];

        for (int i = 0; i < rowCount; i++) {
            values[i] = new short[in.get(i).size()];
            memo[i] = new int[in.get(i).size()];
            for (int j = 0; j < in.get(i).size(); j++) {
                values[i][j] = in.get(i).get(j);
            }
        }

        for (int j = 0; j < in.get(rowCount - 1).size(); j++) {
            memo[rowCount - 1][j] = in.get(rowCount - 1).get(j);
        }
    }

    public int maxPath() {
        return maxPath(0, 0);
    }

    private int maxPath(int row, int column) {
        if (memo[row][column] == 0) {
            memo[row][column] = Math.max(maxPath(row + 1, column), maxPath(row + 1, column + 1)) + values[row][column];
        }

        return memo[row][column];
    }
}
