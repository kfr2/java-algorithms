/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    9/19/11
 * Time:    9:05 PM
 *
 * Written for the 1st Math Challenge of the Year at Saint Francis University.
 * Due: 30-Sep-2011
 */
import java.util.ArrayList;

public class NumberOfPaths {

    // Create an arraylist to hold the output of the numberOfPermutations function.
    static ArrayList<String> possiblePaths = new ArrayList<String>();

    public static void main(String[] args)
    {
        // No matter which route is taken, the car must go North 10 times and East 10 times.
        String directions = "NNNNNNNNNNEEEEEEEEEE";

        // Find all possible paths of these moves.
        numberOfPermutations(directions, "");

        // Determine how many possible paths the car could take to reach its destination.
        System.out.println(possiblePaths.size());

    }

    // Given a String, recursively finds and returns all combinations of the letters
    // in this string.
    public static void numberOfPermutations(String directions, String outputString)
    {
        System.out.println("Examining " + directions);

        // If we have no more directions to add to this route, add to the list of possible paths.
        if(directions.length() == 0)
        {
            possiblePaths.add(outputString);
        }

        else
        {
            for(int i = 0; i < directions.length(); i++)
            {
                outputString += directions.charAt(i);

                String newDirections = "";

                // If not at the beginning of the String.
                if(i != 0)
                {
                    newDirections += directions.substring(0, i);
                }

                // If not at the end of the String.
                if(i != directions.length() - 1)
                {
                    newDirections += directions.substring(i + 1, directions.length());
                }

                // Find the remaining permutations.
                numberOfPermutations(newDirections, outputString);
            }
        }
    }
}
