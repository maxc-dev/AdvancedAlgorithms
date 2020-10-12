package dev.maxc.com2031.lab1_divconquer;

/**
 * Maximum Sum Subarray of a 1-dimensional array
 * 
 * COM2031: Divide and Conquer Labs
 * Autumn Semester 2020
 * Steve Schneider
 */

public class MaxSumSubarray {
	  
	  static int maxSumSubarray(int arr[]) {
		  return dac_maxSumSubarray(arr, 0, arr.length);
	  }
	
/**
 * 
 * @param arr an array of integers of length > 0
 * @param l index of arr
 * @param h such that l<= h-1 and h-1 is an index of arr
 * @return  the max sum of a contiguous subsequence in arr between l (inclusive) and h (exclusive)
 */
	    static int dac_maxSumSubarray(int arr[], int l, int h) 
	    { 
	    // BASE CASE: Only one element 
	    if (l == h-1) {
	        return Math.max(arr[l],0); 
	    }
	    // DIVIDE:  Find middle point 
	    int m = (l + h)/2; 
	    // CONQUER: solve for smaller problems ending at the midpoint
	    int leftsum = dac_maxSumSubarray(arr, l, m);
	    int rightsum = dac_maxSumSubarray(arr, m, h);	    
	    // COMBINE: also need to consider subsequences that crosses the midpoint
	    int crossingsum = maxCrossingSum(arr, l, m, h);
	    return Math.max(Math.max(leftsum, rightsum), crossingsum);
	    } 	  

/**
 * 	    
 * @param arr  array of integers
 * @param l    index into arr
 * @param m    index into arr, l <= m <= h
 * @param h    h-1 is index into arr.  
 * @return	   the maximum subarray sum that includes both arr[m-1] and arr[m]
 */
	    static int maxCrossingSum(int arr[], int l, int m, int h) 
	    { 
	        // Include elements on left of m, i.e. m-1 and less
	        int sum = 0; 
	        int left_sum = arr[m-1]; 
	        for (int i = m-1; i >= l; i--) 
	        { 
	            sum = sum + arr[i]; 
	            if (sum > left_sum) 
	            left_sum = sum; 
	        } 
	  
	        // Include elements on right of m, i.e. m and greater
	        sum = 0; 
	        int right_sum = arr[m]; 
	        for (int i = m; i < h; i++) 
	        { 
	            sum = sum + arr[i]; 
	            if (sum > right_sum) 
	            right_sum = sum; 
	        } 
	  
	        // Return sum of elements on left of m-1 and sum of elements on right of m 
	        return left_sum + right_sum;
	    } 

		// Utilities to assist in testing	
		
	public static void testMSS(String name, int[] a, int expected){
		    int n = maxSumSubarray(a);
		    if(n==expected) {
		    	System.out.print("  Pass:   "+ name);
		    }
		    if(n!=expected) {
		    	System.out.println("*********************");	    	
		    	System.out.println("  Fail:   "+ name);
		    System.out.print("expected value for array: ");
		    printArray(a);
		    System.out.print(":  "+expected+";\n  actual value from current implementation: "+n+"\n");
		    	System.out.print("*********************");
		    }
		    System.out.print("\n");
			}
			
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
			

	    
	    
	    /* Test program to test maxSubArraySum */
	    public static void main(String[] args) 
	    { 
	    int arr[] = {-2, 5, 3, -4, 5, 7}; 
	    testMSS("test1", arr, 16);
	    int arr2[] = {-2, -1, 0, -1, -2};
	    testMSS("test2", arr2, 0);
	    int arr3[] = {-2};
	    testMSS("test3", arr3, 0);
	    int arr4[] = {2};
	    testMSS("test4", arr4, 2);
	    int arr5[] = {-2,-3, -6, -1, -4};
	    testMSS("test5", arr5, 0);
	    int arr6[] = {2,4,6,8,10,12};
	    testMSS("test6", arr6, 42);	    
	    } 
	    
	}
