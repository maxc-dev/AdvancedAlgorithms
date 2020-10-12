package dev.maxc.com2031.lab1_divconquer;

/**
 * Find the maximum and minimum values in an array using Divide and Conquer
 * 
 * COM2031: Divide and Conquer Labs
 * Autumn Semester 2020
 * Steve Schneider
 */


public class MaxMin {

	public static int[] maxMin(int[] arr){
		return dac_maxMin(arr, 0, arr.length);
	}
	
	/**
	 * 
	 * @param arr  a non-empty array of integers
	 * @param start   an index of arr
	 * @param end     end - 1 is an index of arr and start <= end-1
	 * @return        the minimum and maximum values in arr between start (inclusive) and end (exclusive)
	 */
	
	
	public static int[] dac_maxMin(int[] arr, int start, int end) {
		if (start == end-1) {
			return new int[]{arr[start],arr[start]};
		}
		else {
			int mid = (start + end) / 2;
			int[] mm = new int[2];
			int[] mm1 = new int[2];
			int[] mm2 = new int[2];
			mm1 = dac_maxMin(arr, start, mid);
			mm2 = dac_maxMin(arr, mid, end);
			mm[0] = Math.min(mm1[0], mm2[0]);
			mm[1] = Math.max(mm1[1], mm2[1]);
			return mm;
		}
	}
	
	
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
			
		public static void testSum(int[] a, int minexpected, int maxexpected){
		    int[] mm = maxMin(a);
		    if(mm[0]==minexpected && mm[1] == maxexpected) {
		    	System.out.print("  Pass:   ");
		    }
		    else {
		    	System.out.println("*********************");	    	
		    	System.out.print("  Fail:   ");
		    }
		    System.out.print("maxMin(");
		    printArray(a);
		    System.out.print(") is {"+ mm[0] +","+ mm[1] +"}");
		    if(mm[0]!=minexpected || mm[1] != maxexpected) {
		    	System.out.print(", correct value is {"+minexpected+","+maxexpected+"}\n");
		    	System.out.print("*********************");
		    }
		    System.out.print("\n");
			}
			
			
		public static void main(String[] args) {
			System.out.print("Tests for maxmin:\n\n");
			testSum(new int[] {3}, 3, 3);
			testSum(new int[] {1,2,3,4}, 1, 4);
			testSum(new int[] {3, -3, 2, -2, 1, -1, 0}, -3, 3);
			testSum(new int[] {31,2,3,4,5,10,9,8,7,6,20}, 2, 31);	
		}

	}
