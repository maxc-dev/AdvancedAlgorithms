package dev.maxc.com2031.lab1_divconquer;

/**
 * Alternately add and subtract values in a list, using Divide and Conquer
 * <p>
 * COM2031: Divide and Conquer Labs
 * Autumn Semester 2020
 * Steve Schneider
 */

public class PlusMinus {

    /**
     * Alternately add and subtract the values of an array
     *
     * @param arr array of integers
     * @return the result of adding and subtracting alternate values of arr
     */
    public static int plusMinus(int[] arr) {
        return dac_plusMinus(arr, 0, arr.length);
    }

    /**
     * Recursive method for Divide and Conquer approach to find
     * alternating adding and subtracting of values in a section of an array
     *
     * @param arr array of integers
     * @param i   an index of the array
     * @param j   such that j-1 is an index of the array >= i
     * @return the result of adding and subtracting alternate values in arr between i (inclusive) and j (exclusive)
     */
    private static int dac_plusMinus(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        } else if // BASE CASE
        (i == j - 1) {
            return arr[i];
        } else {
            // DIVIDE
            int m = (i + j) / 2;

            // CONQUER
            int sum1 = dac_plusMinus(arr, i, m);
            int sum2 = dac_plusMinus(arr, m, j);

            // COMBINE
            return sum1 + ((m - i) % 2 == 0 ? sum2 : -sum2);
        }
    }


//Utilities to assist in testing

    public static void printArray(int[] a) {
        System.out.print("{");
        for (int i = 0; i < a.length - 1; i++) {
            System.out.print(a[i] + ", ");
        }
        if (a.length > 0) {
            System.out.print(a[a.length - 1]);

        }
        System.out.print("}");
    }

    public static void testPlusMinus(int[] a, int expected) {
        int n = plusMinus(a);
        if (n == expected) {
            System.out.print("  Pass:   ");
        }
        if (n != expected) {
            System.out.println("*********************");
            System.out.print("  Fail:   ");
        }
        System.out.print("sum(");
        printArray(a);
        System.out.print(") is " + n);
        if (n != expected) {
            System.out.print(", correct value is " + expected + "\n");
            System.out.print("*********************");
        }
        System.out.print("\n");
    }


    public static void main(String[] args) {
        System.out.print("Tests for plusminus:\n\n");
        testPlusMinus(new int[]{3}, 3);
        testPlusMinus(new int[]{1, 2, 3, 4, 3, 2, 1}, 0);
        testPlusMinus(new int[]{3, -3, 2, -2, 1, -1, 0}, 12);
        testPlusMinus(new int[]{31, 2, 3, 4, 5, 10, 9, 8, 7, 6, 20}, 45);
        testPlusMinus(new int[]{}, 0);
    }

}
