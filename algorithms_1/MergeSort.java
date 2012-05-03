/*
 * MergeSort.java - Implementing a recursive mergesorting solution.
 * @author: Kevin Richardson <kevin@magically.us>
 * @date:	10-Mar-2011
 */

import java.util.ArrayList;
import java.util.Scanner;

public class MergeSort {
	
	public static void main(String[] args) {
			
		int[] list = getList();
		
		int[] finalList = mergeSort(list);
		
		// print out the new, mergesorted list
		for(int number : finalList){
			System.out.print(number + " ");
		}
		
	}
	
	public static int[] mergeSort(int[] list) {
		
		if(list.length == 1 || list.length == 0){
			return list;
		}
		
		// prepare containers for new lists
		int[] list1 = new int[((list.length / 2) + list.length % 2)];
		int[] list2 = new int[(list.length / 2)];
		
		// split list into two smaller lists
		split(list, list1, list2);
		
		// sort each list
		int[] m1 = mergeSort(list1);
		int[] m2 = mergeSort(list2);
		
		// find a solution by merging the two lists!
		int[] solution = merge(m1, m2);
		
		
		return solution;
	}
	
	// receive the list to sort from the user
	public static int[] getList() {
		ArrayList<String> tempList = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);
		
		String input = "";
		
		System.out.println("Enter integers to add to the list or STOP when you're done entering data:");
		
		while(true){
			input = scan.next();
			if(input.equals("STOP")){ break; }
			tempList.add(input);
		}
		
		// convert to an array of integers
		int i = 0;
		int[] list = new int[tempList.size()];
		for(String number : tempList){
			list[i++] = Integer.parseInt(number);
		}
		
		return list;
	}
	
	
	// split one list into two lists
	public static void split(int[] list, int[] list1, int[] list2) {
		// keep track of position for list1 and list2
		int l1 = 0;
		int l2 = 0;
		
		for(int i = 0; i < list.length; i++){
			// evens to list1, odds to list2
			if(i % 2 == 0){
				list1[l1++] = list[i];
			}
			else{
				list2[l2++] = list[i];
			}
		}
	}
	
	
	// merge two, sorted lists
	public static int[] merge(int[] list1, int[] list2) {
		int i = 0;		// list1 pointer
		int j = 0;		// list2 pointer
		int k = 0;		// newList pointer
		
		int[] newList = new int[list1.length + list2.length];
		
		// compare elements of sorted lists list1 and list2 and place the smallest in the new list.
		while((i < list1.length) || (j < list2.length)){
			// make sure we haven't completed list 1
			if(i < list1.length){
				// make sure we're not at the end of list2
				if(j < list2.length){
					// if list1's element is smaller or equal to list2's...
					if(list1[i] <= list2[j]){ newList[k++] = list1[i++]; }
					// otherwise, list2's element is smaller
					else{ newList[k++] = list2[j++]; }
				}
				
				// we're at the end of list2, so add list1's
				else{
					newList[k++] = list1[i++];
				}
			}
			
			// we haven't completed list 2
			else {
				// if list2's element is smaller or equal to...
				if(j <= list2.length){ newList[k++] = list2[j++]; }
				// otherwise, list1's element is smaller
				else{ newList[k++] = list1[i++]; }
			}
		}
			
		return newList;
	}

}
