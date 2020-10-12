package dev.maxc.com2031.lab1_divconquer;

/**
 * Sum the values in an array
 * 
 * COM2031: Divide and Conquer Labs 
 * Autumn Semester 2020
 * Steve Schneider
 */

public class Sum {
	
/**
 * Sum the values of an array
 * @param arr array of integers
 * @return the sum of the values in arr
 */	
	public static int sum(int[] arr) {
		return dac_sum(arr, 0, arr.length);
	}
	
/**
 * Recursive method for Divide and Conquer approach to find sum of values in a section of an array
 * 	
 * @param arr array of integers
 * @param i an index of the array
 * @param j such that j-1 is an index of the array >= i
 * @return the sum of values in arr between i (inclusive) and j (exclusive)
 */
	private static int dac_sum(int[] arr, int i, int j) {
		if (i==j) {return 0;}
		else if // BASE CASE 
			(i==j-1){
			// TODO return the correct result
			return arr[i];  // this is the wrong result
		}
		else {
			// DIVIDE
			int m = (i + j)/2;
			
			// CONQUER
			int sum1 = dac_sum(arr, i, m);
			int sum2 = dac_sum(arr, m, j);
			
			// COMBINE
			// TODO return the correct result
			return sum1 + sum2; //this is the wrong result
		}
	}
	

// *************************************	
	
// Utilities to assist in testing	
	
	public static void printArray(int[] a) {
		System.out.print("{");
		for(int i = 0; i< a.length-1; i++) {
			System.out.print(a[i] + ", ");			
		}
			if(a.length>0) {
				System.out.print(a[a.length - 1]);
		
			}
		System.out.print("}");
	}
		
	public static void testSum(int[] a, int expected){
	    int n = sum(a);
	    if(n==expected) {
	    	System.out.print("  Pass:   ");
	    }
	    if(n!=expected) {
	    	System.out.println("*********************");	    	
	    	System.out.print("  Fail:   ");
	    }
	    System.out.print("sum(");
	    printArray(a);
	    System.out.print(") is "+ n);
	    if(n!=expected) {
	    	System.out.print(", correct value is "+expected+"\n");
	    	System.out.print("*********************");
	    }
	    System.out.print("\n");
		}
		
		
	public static void main(String[] args) {
		System.out.print("Tests for sum:\n\n");
		testSum(new int[] {3}, 3);
		testSum(new int[] {1,2,3,4}, 10);
		testSum(new int[] {3, -3, 2, -2, 1, -1, 0}, 0);
		testSum(new int[] {31,2,3,4,5,10,9,8,7,6,20}, 105);	
		testSum(new int[] {1,3,5,7,9}, 25 );
	}

}
