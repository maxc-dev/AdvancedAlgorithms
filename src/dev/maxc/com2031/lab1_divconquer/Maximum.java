package dev.maxc.com2031.lab1_divconquer;

/**
 * Maximum value in an array, using Divide and Conquer
 * 
 * COM2031: Divide and Conquer Labs 
 * Autumn Semester 2020
 * Steve Schneider
 */

public class Maximum {

/**
 * Find the maximum value in an array
 * @param arr non-empty array of integers
 * @return the largest integer in the array
 */
	
	public static int max(int[] arr) {
		return dac_max(arr, 0, arr.length);
	}

/**
 * Recursive method for Divide and Conquer approach to find maximum value in a section of an array
 * 	
 * @param arr non-empty array of integers
 * @param i an index of the array
 * @param j a value such that j-1 is an index of the array, and i<j
 * @return the maximum value in arr between i (inclusive) and j (exclusive)
 */
	
	// Recursive method for Divide and Conquer approach
	// to find maximum value in an array 
	//
	private static int dac_max(int[] arr, int i, int j) {
		// Returns maximum value in a between indexes i and j
		if //BASE CASE
		   (i==j-1){
			return arr[i];
		}
		else {
			// DIVIDE
			int m = (i + j)/2;
			
			// CONQUER
			int r1 = dac_max(arr, i, m);
			int r2 = dac_max(arr, m, j);
			
			// COMBINE
			return Math.max(r1, r2);
			}
	}
	
	
	
// Utilities to assist in testing	
	
	public static void printArray(int[] a) {
		System.out.print("{");
		for(int i = 0; i< a.length-1; i++) {
			System.out.print(a[i] + ", ");			
		}
		System.out.print(a[a.length - 1] + "}");		
	}
	
	public static void testMax(int[] a, int expected){
	    int n = max(a);
	    if(n==expected) {
	    	System.out.print("  Pass:   ");
	    }
	    if(n!=expected) {
	    	System.out.println("*********************");	    	
	    	System.out.print("  Fail:   ");
	    }
	    System.out.print("max(");
	    printArray(a);
	    System.out.print(") is "+ n);
	    if(n!=expected) {
	    	System.out.print(", correct value is "+expected+"\n");
	    	System.out.print("*********************");
	    }
	    System.out.print("\n");
		}
		
	
	
	public static void main(String[] args) {
	System.out.print("Tests for max:\n\n");
	testMax(new int[] {3}, 3);
	testMax(new int[] {31,2,3,4,5,10,9,8,7,6,20}, 31);
	testMax(new int[] {-3, -6, -9, -2, -15}, -2);
	testMax(new int[] {-2, -5, 0}, 0);
	}

}
