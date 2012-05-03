/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    1/11/12
 * Time:    4:54 PM
 *
 * The driver class for ShorterStringsInLongString (2012-01-11).
 *
 * Usage:  java FindStringInString longStringFile.txt shortStringFile.txt
 */

public class FindStringInString
{
    public static void main(String[] args)
    {
        if(args.length < 2)
        {
            System.out.println("Usage: java FindStringInString longStringFile.txt shortStringFile.txt");
            System.exit(-1);
        }

        ShorterStringInsideLongString finder = new ShorterStringInsideLongString(args[0], args[1]);

        if(finder.isContained())
        {
            System.out.println("The short string is contained within the long string.");
        }

        else
        {
            System.out.println("The short string is NOT contained within the long string.");
        }
    }
}
