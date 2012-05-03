/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    2/16/12
 * Time:    12:28 PM
 *
 * Determines whether or not given strings are defined in provided DFAs.
 */

import java.io.*;
import java.util.Scanner;

public class DFA_State
{
    int[][] states;
    int numCharacters = 3;
    int defaultState = 0;
    int finalState;
    public String[] stringsToCheck;
    
    // Creates a new DFA state for string checking.
    DFA_State()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("DFA definition file:  ");
        
        try
        {
            Scanner file = new Scanner(new File(input.nextLine()));
            
            // Create an array to hold state data.  There are 3 characters to consider per state (a..c)
            int numStates = Integer.parseInt(file.nextLine());
            states = new int[numStates][numCharacters];
            
            for(int i = 0; i < numStates; i++)
            {
                String stateLine = file.nextLine();
                String[] bits = stateLine.split(" ");

                for(int j = 1; j < numCharacters + 1; j++)
                {
                    states[i][j - 1] = Integer.parseInt(bits[j]);
                }
            }

            // Get the final state.
            finalState = Integer.parseInt(file.nextLine());


            // Determine the strings to check number of strings.
            stringsToCheck = new String[Integer.parseInt(file.nextLine())];
            for(int i = 0; i < stringsToCheck.length; i++)
            {
                stringsToCheck[i] = file.nextLine();
            }
        }
        
        catch(java.io.FileNotFoundException e)
        {
            System.out.println("The specified file was not found or could not be read.");
            System.exit(-1);
        }
    }
    
    // Returns whether or not the string is contained within this DFA state.
    public boolean isContainedInLanguage(String string)
    {
        // Start each word in the default state.
        int currentState = defaultState;

        for(char c : string.toCharArray())
        {
            // Figure out where to look to find our destination.
            int index = c - 'a';

            // Go to the new state.
            currentState = states[currentState][index];
        }

        return currentState == finalState;
    }
}
