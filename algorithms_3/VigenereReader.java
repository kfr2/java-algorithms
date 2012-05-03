/**
 * @author Kevin Richardson <kevin@magically.us>
 * 
 * Reads in files and converts them to a simple format (all lowercase letters).
 * It will then call the VigenereEngine to allow the user to encrypt or decrypt
 * the file.
 */

import java.io.*;
import java.util.Scanner;

public class VigenereReader {
	
	public static void read(String[] args)
	{
		// Read in files passed as arguments.
		if(args.length != 2)
		{
			System.out.println("Usage: java FileReader input_file.txt output_file.txt");
			System.exit(-1);
		}
		
		String inputFile = args[0];
		String outputFile = args[1];
		
		// Read the input file.
		File file = new File(inputFile);
		
		try {
			Scanner input = new Scanner(file);
			
			// Convert the input file to the "simple" format.
			String output = "";
			
			while(input.hasNext())
			{				
				output += stripString(input.next());
			}
			
			output = addNewLines(output, 60);
			
			// Write the new string to disk.
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
				out.write(output);
				out.close();
			}
			
			catch(IOException e)
			{
				System.out.println("An error occurred while writing to the output file.");
				System.exit(-1);
			}
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("The specified input file was not found or was unreadable.");
			System.exit(-1);
		}

		
		// Call the decryption/encryption engine.
		System.out.println("Done converting input file.");
		VigenereEngine engine = new VigenereEngine(outputFile);
		engine.run();
	}
	
	/**
	 * Returns the "simple" text --all lowercase letters with punctuation stripped.
	 * @param input
	 * @return
	 */
	public static String stripString(String input)
	{
		String output = "";
		
		for(char letter : input.toCharArray())
		{
			// Keep any letters (a-Z or A-Z)
			if(Character.isLetter(letter))
			{
				output += letter;
			}
		}
		
		return output.toLowerCase();
	}
	
	/**
	 * Returns the specified string with a new line character every n characters.
	 */
	public static String addNewLines(String input, int n)
	{
		String output = "";
		int count = 0;
		
		for(char letter : input.toCharArray())
		{
			output += letter;
			
			if(++count == n)
			{
				output += "\n";
				count = 0;
			}
		}
		
		return output;
	}
	
	public static void main(String[] args)
	{
		read(args);
	}
}
