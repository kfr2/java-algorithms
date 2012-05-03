/**
 * @author Kevin Richardson <kevin@magically.us>
 *
 * An Vigenere engine to encrypt/decrypt text with provided key.
 */

import java.io.*;
import java.util.Scanner;

public class VigenereEngine {
	String text;
	String key;
	
	/**
	 * Takes the passed in simple file (see VigenereReader) and allows the user to 
	 * either encrypt or decrypt it.
	 * @param inputFile
	 */
	VigenereEngine(String inputFile)
	{
		// Read in the input file.
		File file = new File(inputFile);
		text = "";
		
		try {
			Scanner input = new Scanner(file);
			
			while(input.hasNext())
			{
				text += input.next();
			}
		}
		
		catch (IOException e)
		{
			System.out.println("An error occured while reading from the input file.");
			System.exit(-1);
		}
		return;
	}
	
	/**
	 * Asks the user for her choice of key then encryption/decryption and calls the
	 * appropriate methods.
	 */
	public void run()
	{
		// Get the user's key and simplify it if necessary.
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter your key: ");
		key = VigenereReader.stripString(scan.nextLine());
		
		System.out.println("Your key is: " + key);
		
		// Determine the function the user wishes to perform.
		// Encrypt will be the default function.
		System.out.print("[e]ncrypt or [d]ecrypt: ");
		
		try {
			int choice = System.in.read();
			
			// Encrypt.
			if(choice != 'd')
			{
				System.out.println("Encrypting the text with your key...");
				encrypt();
			}
			
			// Decrypt.
			else
			{
				System.out.println("Decrypting the text with your key...");
				decrypt();
			}
		}
		
		catch (IOException e)
		{
			System.out.println("An error occurred while acquiring your key.");
		}
	}
	
	
	/**
	 * Prints the text encrypted with the key.
	 */
	private void encrypt()
	{
		String output = "";
		
		int keyPosition = 0;
		int keyLength = key.length();
		
		// Perform the necessary swaps.
		for(char letter : text.toCharArray())
		{
			char newLetter = (char)(letter + key.charAt(keyPosition++) - 'a');
			
			if(newLetter > 'z')
			{
				newLetter -= 26;
			}
			
			output += newLetter;
			
			// Reset the key when necessary.
			if(keyPosition == keyLength)
			{
				keyPosition = 0;
			}
		}
		
		System.out.println(VigenereReader.addNewLines(output, 60));
	}
	
	/**
	 * Prints the text decrypted with the key.
	 */
	private void decrypt()
	{
		String output = "";
		
		int keyPosition = 0;
		int keyLength = key.length();
		
		// Perform the necessary swaps.
		for(char letter : text.toCharArray())
		{
			char newLetter = (char)(letter + 'z' + 1 - key.charAt(keyPosition++));
			
			if(newLetter > 'z')
			{
				newLetter -= 26;
			}
			
			output += newLetter;
			
			// Reset the key when necessary.
			if(keyPosition == keyLength)
			{
				keyPosition = 0;
			}
		}
		
		System.out.println(VigenereReader.addNewLines(output, 60));
	}


}
