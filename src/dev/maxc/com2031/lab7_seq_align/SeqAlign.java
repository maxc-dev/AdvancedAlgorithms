package dev.maxc.com2031.lab7_seq_align;

/**
 * Implementation of the Dynamic programming Sequence Alignment algorithm for COM2031
 *
 * @author Manal Helal (2019), augmented by Steve Schneider (2020)
 */

import java.util.Arrays;

public class SeqAlign {
    // function to find out
    // the minimum penalty
    static void Needleman_Wunsch(String X, String Y, int M[][], int misMatchPenalty, int gapPenalty) {
        // TODO
        // Fill the array M with the correct values for the Needleman-Wunsch algorithm
        for (int i = 0; i < X.length() + 1; i++) {
            M[i][0] = i * gapPenalty;
        }

        for (int i = 0; i < Y.length() + 1; i++) {
            M[0][i] = i * gapPenalty;
        }

        for (int i = 1; i < X.length() + 1; i++) {
            for (int j = 1; j < Y.length() + 1; j++) {
                M[i][j] = Math.min((X.charAt(i - 1) == Y.charAt(j - 1) ? 0 : misMatchPenalty) + M[i - 1][j - 1], gapPenalty + Math.min(M[i - 1][j], M[i][j - 1]));
            }
        }
    }


    static String traceBack(String X, String Y, int M[][], int misMatchPenalty, int gapPenalty) {
        final String GAP = "-";
        StringBuilder AlignmentX = new StringBuilder();
        StringBuilder AlignmentY = new StringBuilder();
        // TODO
        // Given the words X and Y and the Needleman-Wunsch array M for X and Y
        // reconstruct the match between X and Y that gives rise to the minimum penalty
        // as shown in the array M

        //printM(M, X, Y);

        int i = 0;
        int j = 0;

        //todo change from top left to bottom right traversal
        while (i < X.length() && j < Y.length()) {
            int left = gapPenalty + getM(M, i - 1, j);
            int topLeft = getM(M, i - 1, j - 1);
            int top = getM(M, i, j - 1);
            System.out.println("Lef: " + left);
            System.out.println("ToL: " + topLeft);
            System.out.println("Top: " + top);
            if (left <= topLeft && left <= top) {
                System.out.println("Gap on left word");
                AlignmentX.append(X.charAt(i));
                AlignmentY.append(GAP);
                i++;
            } else if (topLeft <= top) {
                System.out.println("match");
                AlignmentX.append(X.charAt(i));
                AlignmentY.append(Y.charAt(j));
                i++;
                j++;
            } else {
                System.out.println("Gap on top word");
                AlignmentY.append(Y.charAt(j));
                AlignmentX.append(GAP);
                j++;
            }
        }

        return "The alignment is: \n" + AlignmentX.toString() + "\n" + AlignmentY.toString() + "\n";
    }

    static int getM(int[][] M, int x, int y) {
        if (x < 0 || y < 0) {
            return 9999;
        } else {
            return M[x][y];
        }
    }

    /**
     * Utility:  pretty-print The scoring Matrix
     */

    public static String printM(int M[][], String X, String Y) {
        System.out.println("X x Y");
        StringBuffer s = new StringBuffer();

        // Table Header:
        s.append("\t-\t");
        for (int j = 0; j < Y.length(); j++) {
            s.append(Y.charAt(j) + "\t");
        }
        s.append("\n\n-\t");
        for (int j = 0; j <= Y.length(); j++) {
            s.append(M[0][j] + "\t");
        }
        s.append("\n");
        // Table Content:
        for (int i = 1; i <= X.length(); i++) {
            s.append(X.charAt(i - 1) + " \t");
            for (int j = 0; j <= Y.length(); j++) {
                s.append(M[i][j] + "\t");
            }
            s.append("\n");
        }

        return s.toString();
    }

    // Test code

    public static void testNW(String name, String word1, String word2,
                              int misMatchPenalty, int gapPenalty, int expected) {
        final int M[][] = new int[word1.length() + 1][word2.length() + 1];
        Needleman_Wunsch(word1, word2, M, misMatchPenalty, gapPenalty);
        int value = M[word1.length()][word2.length()];
        if (value == expected) {
            System.out.print(name + ":  Pass ");
        }
        if (value != expected) {
            System.out.println("*********************");
            System.out.print(name + ":  Fail:  ");
            System.out.print("your value for " + word1 + " and " + word2 + " is " + value
                    + "  (bottom right value of your array M)\n");
            System.out.print("Correct value is " + expected + "\n\n");
            System.out.println("Your array M is:");
            System.out.println(printM(M, word1, word2));
            System.out.print("*********************");
        }
        System.out.print("\n");
    }


    // Driver code
    public static void main(String[] args) {
//		System.out.println(traceBack(word1, word2, M, misMatchPenalty, gapPenalty));		

        testNW("test1 ", "BEER", "EBBE", 3, 2, 7);
        testNW("test2 ", "ABBA", "BAAB", 3, 5, 12);
        testNW("test3 ", "ABBA", "BAAB", 3, 2, 7);
        testNW("test4 ", "SURREY", "GUILDFORD", 3, 2, 19);
        testNW("test5 ", "ABCD", "EFGHIJ", 2, 5, 18);
        testNW("test6 ", "ABCD", "EFGHIJ", 5, 2, 20);
        testNW("test7 ", "", "AAAA", 1, 2, 8);
        testNW("test6 ", "ABCD", "ABCD", 2, 2, 0);
        testNW("test7 ", "ABACAB", "ABCABC", 3, 2, 4);
        testNW("test8 ", "SUSSU", "USSUS", 2, 5, 8);
        testNW("test9 ", "A", "B", 3, 2, 3);
        testNW("test10", "A", "B", 2, 2, 2);
        testNW("test11", "A", "B", 5, 2, 4);
        testNW("test12", "", "", 5, 2, 0);
        String word1 = "DEED";
        String word2 = "DECIDE";

        int misMatchPenalty = 3;
        int gapPenalty = 2;
        final int M[][] = new int[word1.length() + 1][word2.length() + 1];
        Needleman_Wunsch(word1, word2, M, misMatchPenalty, gapPenalty);
        System.out.println("Testing the construction of the array M:");
        System.out.println(printM(M, word1, word2));
        System.out.println(traceBack(word1, word2, M, misMatchPenalty, gapPenalty));


    }

}



