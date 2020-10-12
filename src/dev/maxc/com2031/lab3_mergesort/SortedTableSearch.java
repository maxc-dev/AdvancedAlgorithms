package dev.maxc.com2031.lab3_mergesort;

/**
 * Sorted Table Search
 * <p>
 * COM2031: Divide and Conquer Labs
 * Autumn Semester 2020
 * Steve Schneider
 */

public class SortedTableSearch {

    /**
     * @param table a 2d table of integers with no repeats, where every row is increasing and every column is increasing
     * @param x     value being searched for
     * @return 1 if x is in the table, and 0 otherwise
     */


    public static int tableSearch(int[][] table, int x) {
        if (table.length == 0) {
            return 0;
        }
        ;
        if (table[0].length == 0) {
            return 0;
        }
        ;
        return dac_TableSearch(table, 0, 0, table.length, table[0].length, x);
    }


    /**
     * @param table a 2d table of integers with no repeats, where every row is increasing and every column is increasing
     * @param i     (i,j) is the top left corner of the section to be searched
     * @param j
     * @param k     (k,l) is the bottom right corner of the section to be searched
     * @param l
     * @param x     value to be searched
     * @return 1 if x is in the table between (i,j) (inclusive) and (k,l) (exclusive), otherwise return 0
     */

    private static int dac_TableSearch(int[][] table, int i, int j, int k, int l, int x) {
        if (true) { // BASE CASE
            // TODO
        } else {
            //DIVIDE
            // TODO
            //
            //CONQUER
            // TODO
            //
            //COMBINE
            // TODO
        }
        return 0;
    }

    /*
     *
     * Utilities for testing
     *
     */


    public static void testSearch(String name, int[][] table, int x, int expected) {
        int n = tableSearch(table, x);
        if (n == expected) {
            System.out.print(name + "  Pass");
        }
        if (n != expected) {
            System.out.println("*********************");
            System.out.print(name + "  Fail:  ");
        }
        if (n != expected) {
            System.out.print("correct output is " + expected + "\n");
            System.out.print("*********************");
        }
        System.out.print("\n");
    }


    public static void main(String[] args) {
        int[][] table1 = {
                {1, 5, 9},
                {2, 6, 10},
                {3, 7, 11}
        };
        testSearch("test1", table1, 11, 1);
        testSearch("test2", table1, 1, 1);
        testSearch("test3", table1, 2, 1);
        testSearch("test4", table1, 3, 1);
        testSearch("test5", table1, 6, 1);
        testSearch("test6", table1, 9, 1);
        testSearch("test7", table1, 0, 0);
        testSearch("test8", table1, 66, 0);
        testSearch("test9", table1, 8, 0);
        int[][] table2 = {
                {1, 5, 9, 15},
                {2, 6, 10, 18},
                {3, 7, 11, 21},
                {4, 8, 22, 24}};
        testSearch("test10", table2, 18, 1);
        testSearch("test11", table2, 22, 1);
        testSearch("test12", table2, 17, 0);
        testSearch("test13", table2, 0, 0);
        int[][] table3 = {{}};
        testSearch("test14", table3, 3, 0);
        int[][] table4 = {};
        testSearch("test15", table4, 3, 0);


    }


}



