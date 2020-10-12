package dev.maxc.com2031.lab2_quicksort_kthsmall;

/**
 * QuickSort
 * 
 * COM2031: Divide and Conquer Labs
 * Autumn Semester 2020
 * Steve Schneider
 */


import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;

public class QuickSort {

	/**
	 * 
	 * @param arr an arraylist of integers
	 * @return  array of the integers in arr in sorted order
	 */
	
	public static ArrayList<Integer> quickSort(ArrayList<Integer> arr) {
		return dac_quickSort(arr);
	}
	
	/**
	 * 
	 * @param arr    arraylist of integers
	 * @return       sorted arraylist of the integers in arr 
	 */
	
	public static ArrayList<Integer> dac_quickSort(ArrayList<Integer> arr){
		// BASE CASE
		if (arr.size() <= 1) {
			return arr;
		}
		else {
			// DIVIDE
			int pivot = arr.get(0);
			ArrayList<Integer> leftarr = new ArrayList<>();
			ArrayList<Integer> rightarr = new ArrayList<>();
			for (int i=1;i<arr.size();i++) {
				if (arr.get(i) <= pivot) {
					leftarr.add(arr.get(i));
				} else {
					rightarr.add(arr.get(i));
				}
				
			}
			// CONQUER
			ArrayList<Integer> sortedleft = dac_quickSort(leftarr);
			ArrayList<Integer> sortedright = dac_quickSort(rightarr);	
			// COMBINE
			ArrayList<Integer> sortedcombine = new ArrayList<>();
			sortedcombine.addAll(sortedleft);
			sortedcombine.add(pivot);
			sortedcombine.addAll(sortedright);

			return sortedcombine;

		}
	}
	

	public static void testQuickSort(String name, ArrayList<Integer> test, ArrayList<Integer> expected){
	    test = quickSort(test);
	    if (test.equals(expected)){
	    	System.out.println("  Pass:   "+name);
	    }
	    else {
	    	System.out.println("*********************");	    	
	    	System.out.println("  Fail:   "+name);
	    	System.out.println("*********************");
	    }
//	    System.out.print("\n");
	}
	
	
	public static void main(String[] args) {

		// allocate two arrays
		ArrayList<Integer> s = new ArrayList<>(Arrays.asList(2, 1, 3, 4, 5, 6));
		ArrayList<Integer> t = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
		testQuickSort("test1", s, t);
		ArrayList<Integer> s2 = new ArrayList<>(Arrays.asList(9,7,5,3,1));
		ArrayList<Integer> t2 = new ArrayList<>(Arrays.asList(1,3,5,7,9));
		testQuickSort("test2", s2, t2);
		ArrayList<Integer> s3 = new ArrayList<>(Arrays.asList(8,6,8,6,4,4,2));
		ArrayList<Integer> t3 = new ArrayList<>(Arrays.asList(2,4,4,6,6,8,8));
		testQuickSort("test3", s3, t3);
		ArrayList<Integer> s4 = new ArrayList<>(Arrays.asList(1));
		ArrayList<Integer> t4 = new ArrayList<>(Arrays.asList(1));
		testQuickSort("test4", s4, t4);
		ArrayList<Integer> s5 = new ArrayList<>(Arrays.asList(3,3,4,3,3,3));
		ArrayList<Integer> t5 = new ArrayList<>(Arrays.asList(3,3,3,3,3,4));
		testQuickSort("test5", s5, t5);
		int N = 1000;
		ArrayList<Integer> s6 = new ArrayList<>();
		ArrayList<Integer> t6 = new ArrayList<>();
		int[] ts6 = new int[N];
		int next;
		Random r = new Random();
			// create new random sequence of 1000 numbers and fill both arrays
			// identically:
			for (int j = 0; j < N; j++) {
				next = r.nextInt(10000000);
				s6.add(next);
				ts6[j] = next;
			}

			// sort s using our implementation:
			s6 = quickSort(s6);

			// sort t6 using Java's implementation:
			Arrays.sort(ts6);
			for (int j = 0; j < N; j++) {
				t6.add(ts6[j]);
			}
			testQuickSort("test6", s6, t6);
	}

}
