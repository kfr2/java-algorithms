/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    3/12/12
 * Time:    2:30 PM
 *
 * Determines whether or not provided strings (terminated with a '$') are defined
 * within the following CFG:
 * <s> -> dc<t>
 * <t> -> ab<t> | c<t> | e
 */

import java.io.*;

/**
 * Driver class.  Reads in the CFG strings to check from the file(s) passed as command-line arguments.
 * Places each string into a CfgString and runs validate() to determine whether or not they abide
 * by the CFG's logic.
 *
 */
public class RecursiveDescentParser
{		
	// Read the CFG string(s) from the file(s) passed in as arguments.
	public static void main(String[] args)
	{
		if(args.length == 0)
		{
			System.out.println("Usage: java RecursiveDescentParser cfg_file.txt (cfg_file2.txt)...");
			System.exit(-1);
		}
		
		// Parse each line of each input file.
		for(String filename : args)
		{
			try
			{
				BufferedReader input = new BufferedReader(new VigenereReader(filename));
				
				String line = null;
				
				while((line = input.readLine()) != null)
				{
					CfgString cfg = new CfgString(line);
					cfg.validate();
				}
			}
			
			catch (IOException ex)
			{
				System.out.println("IO Error: " + ex.getMessage());
			}
			
			System.out.println("==========");
		}
	}

}


/**
 * Contains the string to check as well as the logic through which to check it.
 */
class CfgString
{
	String content;
	int stringPosition;
	
	CfgString(String input)
	{
		this.content = input;
		this.stringPosition = 0;
		
		System.out.println("Examining '" + content + "'");
	}
	
	// Examine this CfgString to determine whether or not it could be produced
	// by the defined grammar.
	public void validate()
	{
		this.s();
	}
	
	// Ascertains string begins with "dc."
	void s()
	{
		if(content.charAt(stringPosition) == 'd' && content.charAt(stringPosition + 1) == 'c')
		{
			this.stringPosition += 2;
			this.sPrime();
		}
		
		else
			System.out.println("Invalid string.");		
	}
	
	void sPrime()
	{
		// If we've reached the end of the string (epsilon), it is valid.
		if(content.charAt(stringPosition) == '$')
		{
			System.out.println("Valid string.");
			return;
		}
		
		// abS'
		if(content.charAt(stringPosition) == 'a' && content.charAt(stringPosition + 1) == 'b')
		{
			stringPosition += 2;
			this.sPrime();
			return;
		}
		
		// cS'
		if(content.charAt(stringPosition) == 'c')
		{
			stringPosition++;
			this.sPrime();
			return;
		}
		
		// Otherwise, the string is invalid.
		System.out.println("Invalid string.");
	}
}
