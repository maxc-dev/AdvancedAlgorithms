package dev.maxc.com2031.lab1_divconquer;

/**
 * Sum the values in an ArrayList uswing Divide and Conquer
 * 
 * COM2031: Divide and Conquer Labs 
 * Autumn Semester 2020
 * Steve Schneider
 */


import java.util.ArrayList;

public class ArrayListSum {
	
/**
* Sum the values of a list
* @param list is a list of integers
* @return the sum of the values in list
*/	
	public static int listSum(ArrayList<Integer> list) {
		// create local copy of the list so that the input list does not change
		ArrayList<Integer> localList = (ArrayList<Integer>)list.clone();
		return dac_listSum(localList);
	}
	
/**
* Recursive method for Divide and Conquer approach to find sum of values in a list
*
* @return the sum of values in arr between i (inclusive) and j (exclusive)
*/
	private static int dac_listSum(ArrayList<Integer> list) {
		if (list.isEmpty()) {return 0;}
		else if // BASE CASE 
			(list.size()==1){
			return list.remove(0);
		}
		else {
			// DIVIDE
			ArrayList<Integer> list1 = new ArrayList<>();
			Integer next;
			for(int i = 0;i<(list.size()/2);i++) {
				next = list.remove(0);
				list1.add(next);
			}
			// CONQUER
			int sum1 = dac_listSum(list1);
			int sum2 = dac_listSum(list);
			
			// COMBINE
			return sum1 + sum2;
		}
	}
	
	
//Utilities to assist in testing	
	
	public static void testListSum(ArrayList<Integer> l, int expected){
		ArrayList<Integer> l_test = (ArrayList<Integer>)l.clone();
		int n = listSum(l);
	    if(n==expected) {
	    	System.out.print("  Pass:   ");
	    }
	    if(n!=expected) {
	    	System.out.println("*********************");	    	
	    	System.out.print("  Fail:   ");
	    }
	    System.out.print("listSum(");
	    System.out.print(l_test);
	    System.out.print(") is "+ n);
	    if(n!=expected) {
	    	System.out.print(", correct value is "+expected+"\n");
	    	System.out.print("*********************");
	    }
	    System.out.print("\n");
		}
		
		
	public static void main(String[] args) {
		System.out.print("Tests for listSum:\n\n");
		ArrayList<Integer> testList = new ArrayList<> ();
		testListSum(testList, 0);
		testList.add(3);
		testList.add(4);
		testList.add(5);
		testListSum(testList, 12);
		testList.add(1, 7);
		testListSum(testList, 19);
		testList.add(2, -8);
		testListSum(testList, 11);
	}

}
