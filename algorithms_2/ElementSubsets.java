/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    11/7/11
 * Time:    8:34 PM
 *
 * Given a set of n elements, this program finds the subset of elements whose sum
 * comes closest to sum(all elements)/2.
 */

import java.util.Scanner;

public class ElementSubsets
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int elemCount;
        int[] elements;
        String[] sets;


        /**
         * Read in the user's desire number of elements.
         */
        System.out.println("Number of elements: ");
        elemCount = scanner.nextInt();
        elements = new int[elemCount];


        /**
         * Read in the list of elements into an array.
         */
        System.out.println("List of elements: ");
        for (int i = 0; i < elemCount; i++)
        {
            elements[i] = scanner.nextInt();
        }


        /**
         * Find our target value ( sum(elements) / 2)
         */
        double targetValue = 0;

        for(int number : elements)
        {
            targetValue += number;
        }

        targetValue /= 2;


        /**
         * Build the list of possible sets based on elements.
         * While building, calculate how closely the set's sum
         * will come to the targetValue.
         */
        String bestSet = "";
        double bestValue = Double.MAX_VALUE;

        // will store a binary string to determine whether or not a given
        // element resides in a set. 1 if elems[index] does, 0 if not.
        sets = new String[(int)Math.pow(2, elemCount)];

        for(int i = 0; i < sets.length; i++)
        {
            sets[i] = Integer.toBinaryString(i);
            // Run from right to left to calculate the sum of the set.
           // Stop summing when the beginning of the string is encountered.
           // <----
           // ex:  0 would represent the set NOT having elements[0]        .
           // ex:  10 would represent the set having elements[1] but not elements[0].

            double currentValue = 0;

            for(int j = sets[i].length() - 1; j >= 0; j--)
            {
                if(sets[i].charAt(j) == '1')
                {
                    currentValue += elements[j];
                }
            }

            // If the sum is closer to the targetValue than bestSum, save this set as bestSet.
            if(Math.abs(targetValue - currentValue) < Math.abs(targetValue - bestValue))
            {
                bestSet  = sets[i];
                bestValue = currentValue;
            }
        }


        /**
         * Print out the bestSum as well as the set comprising it.
         */
        System.out.println("Target sum: " + targetValue);
        System.out.println("Closest value: " + bestValue);

        System.out.print("Set: ");
        for(int i = bestSet.length() - 1; i >= 0; i--)
        {
            if(bestSet.charAt(i) == '1')
            {
                System.out.print(elements[i] + " ");
            }
        }
    }
}