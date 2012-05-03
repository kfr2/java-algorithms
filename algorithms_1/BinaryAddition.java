/**
 * BinaryAddition.java -- Adds two binary strings and prints result to the user.
 * Author:  Kevin Richardson
 * Date:    26-Apr-2011: 3:14pm
 */

import java.util.Scanner;

public class BinaryAddition {
	
	static Scanner scan;
	static String binary1, binary2;
	
	public static void main(String[] args) {
		
		for(int i = 0; i < 10; i++) {
			scan = new Scanner(System.in);
			
			System.out.print("Binary 1: ");
			binary1 = scan.nextLine();
			System.out.print("Binary 2: ");
			binary2 = scan.nextLine();
			
			System.out.println(addBinaryStrings(binary1, binary2));
		}
		
	}
	
	
	public static String addBinaryStrings(String binary1, String binary2) {
		String result = "";
		int overflow = 0;
		
		int largestIndex = Math.max(binary1.length(), binary2.length());

        // pad strings on the left with 0's so they're the same length
        binary1 = String.format("%" + largestIndex + "s", binary1).replace(' ', '0');
        binary2 = String.format("%" + largestIndex + "s", binary2).replace(' ', '0');
		
		for(int i = largestIndex - 1; i >= 0; i--) {
			int value1, value2, sum;

            if(binary1.charAt(i) == '0') { value1 = 0; }
            else { value1 = 1; }

            if(binary2.charAt(i) == '0') { value2 = 0; }
            else { value2 = 1; }

            // account for any "carry one"s
            sum = value1 + value2 + overflow;

            switch(sum) {
                case 0:
                    result = "0" + result;
                    overflow = 0;
                    break;
                case 1:
                    result = "1" + result;
                    overflow = 0;
                    break;
                case 2:
                    result = "0" + result;
                    overflow = 1;
                    break;
                case 3:
                    result = "1" + result;
                    overflow = 1;
                    break;
            }
		}

        if(overflow == 1){ return overflow + result; }
        else{ return result; }
	}

}
