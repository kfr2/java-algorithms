/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    1/11/12
 * Time:    12:13 PM
 *
 * Array of characters (do not use strings)
 * Have a long string (array of chars) - 10000
 * Have a shorter string (array of up to 5000 chars)
 * Is the short string in the long one?
 *
 * Analyze the running time of the algorithm: O(n^2)
 */

import java.io.FileReader;
import java.io.IOException;

public class ShorterStringInsideLongString
{
    char[] shortString, longString;

    // Reads in short and long strings from the specified file.
    ShorterStringInsideLongString(String longStringPath, String shortStringPath)
    {
        // read in the long/short strings from the specified files.
        longString  = readString(longStringPath, 10000);
        shortString = readString(shortStringPath, 5000);
    }

    
    // Reads the specified file and returns a char[] of specified, max length
    private char[] readString(String filePath, int maxLength)
    {
        try
        {
            FileReader input = new FileReader(filePath);
            String fileContents = "";

            // Read until EOF.
            char currentChar = '_';
            while(currentChar != (char)-1)
            {
                currentChar = (char)input.read();
                fileContents += currentChar;
            }

            // Strip the EOF character from the string.
            fileContents = fileContents.substring(0, fileContents.length() - 1);

            // Limit the string to maxLength chars then return an array.
            return fileContents.substring(0, Math.min(maxLength, fileContents.length())).toCharArray();
        }

        catch (IOException e)
        {
            System.out.println("The long string input file was not found or could not be read.");
            System.exit(-1);
        }

        return new char[0];
    }
    
    
    // Returns true if the shortString is contained within the longString.
    boolean isContained()
    {
        // If shortString is longer than longString, it can't be contained within it.
        if(shortString.length > longString.length) return false;

        // Create strings of shortString.length length starting at the beginning of longString and running to its end.
        for(int start = 0; start < longString.length - shortString.length; start++)
        {
            char[] test = new char[shortString.length];

            for(int i = 0; i < shortString.length; i++)
            {
                test[i] = longString[start + i];
            }

            if(doArraysMatch(shortString, test)) return true;
        }

        return false;
    }


    // Returns true if given character arrays are identical.
    private boolean doArraysMatch(char[] x, char[] y)
    {
        if(x.length != y.length) return false;

        for(int i = 0; i < x.length; i++)
        {
            if(x[i] != y[i]) return false;
        }
        
        return true;
    }
}
