/* --------------------------------------------------------------------------
 * file:		MaximumSubsequenceSum.java
 * description:	Find the maximum subsequence sum, given a sequence of integers
 * 				stored in an array.  Utilizes divide and conquer O(n log n)
 * author:		Kevin Richardson <kevin@magically.us>
 * date:		24-Mar-2011
 * --------------------------------------------------------------------------
 */

import java.util.*;

public class MaximumSubsequenceSum {
	
	static int[] values;
	static ArrayList<Integer> tempValues;
	static Scanner scan;
	
	// get user's input fo rthe array values
	public static void getUserInput(){
			
		scan = new Scanner(System.in);
		tempValues = new ArrayList<Integer>();
		
		System.out.println("Enter values of which to find maximum subsequence value or STOP when done:");

		String value = scan.nextLine();		
		while(!value.equals("STOP")){
			int intValue = Integer.parseInt(value);
			tempValues.add(intValue);
			
			value = scan.nextLine();
		}
		
		
		// user is done entering data. convert arraylist to array.
		int i = 0;
		values = new int[tempValues.size()];
		for(int tempValue : tempValues){
			values[i++] = tempValue;
		}
		
	}
	
	// drives initial values into maxSum()
	public static int totalMaxSum(int[] values){
		return maxSum(values, 0, values.length - 1);
	}
	
	// returns the maximum sum of an array of integers
	public static int maxSum(int[] values, int left, int right) {
		
		if(left == right){ return values[left]; }
		int middle = (left + right) / 2;
		
		
		int sumLeft = maxSum(values, 0, left);
		int sumRight = maxSum(values, right, values.length - 1);
	
		
		// find max value over the middle span
		// left half
		int leftBorderSum = 0, maxLeftBorderSum = 0;
		
		for(int i = middle; i >= left; i--){
			leftBorderSum += values[i];
			if(leftBorderSum > maxLeftBorderSum)
				maxLeftBorderSum = leftBorderSum;
		}
		
		// right half
		int rightBorderSum = 0, maxRightBorderSum = 0;
		
		for(int i = middle + 1; i <= right; i++){
			rightBorderSum += values[i];
			if(rightBorderSum > maxRightBorderSum)
				maxRightBorderSum = rightBorderSum;
		}
		
		
		int sumSpan = maxLeftBorderSum + maxRightBorderSum;

		
		// find maximum values of the three spans
		int max = maxInt(sumLeft, sumRight, sumSpan);
		
		
		return max;
	}
	
	// returns max value of inputted integers
	public static int maxInt(int ... numbers){
		
		int max = Integer.MIN_VALUE;
		
		for(int number : numbers){
			if(number > max) max = number;
		}
		
		return max;
	}
	
	
	public static void main(String[] args) {
		
		getUserInput();
		System.out.println(totalMaxSum(values));

	}
	
}
