import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Given a list of integers, determine the length of the longest list of ascending subsequences.
 * @author Kevin Richardson <kevin@magically.us>
 *
 */

public class AscendingSubsequence {

	// Read in the sequence strings from the file(s) passed as arguments.
	// format ex:  1 2 3 4 5
	public static void main(String[] args)
	{
		if(args.length == 0)
		{
			System.out.println("Usage: java AscendingSubsequence sequence.txt (sequence2.txt)...");
			System.exit(-1);
		}
		
		// Parse each line of each input file.
		for(String filename : args)
		{
			try
			{
				BufferedReader input = new BufferedReader(new FileReader(filename));
				
				String line = null;
						
				// Read each sequence and parse it.
				while((line = input.readLine()) != null)
				{
					String[] numbers = line.split(" ");
					int[] sequence = new int[numbers.length];
					
					for(int i = 0; i < numbers.length; i++)
					{
						sequence[i] = Integer.parseInt(numbers[i]);
					}
					
					parseSequence(sequence);
				}
			}
					
			catch (IOException ex)
			{
				System.out.println("IO Error: " + ex.getMessage());	
					
			    System.out.println("==========");
			}
		}
	}
	
	// Determine the longest sequence and print its length.
	static void parseSequence(int[] sequence)
	{
		// Store count of the sequence lengths.
		int[] sequenceCount = new int[sequence.length];
		
		// Store the last seen values for each sequence.
		int[] lastValues = new int[sequence.length];
		
		// Examine the sequence back to front.
		for(int i = sequence.length - 1; i >= 0; i--)
		{
			int currentElem = sequence[i];
			lastValues[i] = currentElem;
			sequenceCount[i] = 1;
			
			// Examine all the previous elements in the sequence.
			// If this element fits within the list of the other subsequences, increment its count.
			for(int j = sequence.length - 1; j > i; j--)
			{
				if(currentElem < lastValues[j])
				{
					sequenceCount[j] += 1;
					lastValues[j] = currentElem;
				}
			}			
		}
		
		// Convert array to a Collection and print largest value.
		int maxLength = 0;
		for(int i = 0; i < sequenceCount.length; i++)
		{
			if(sequenceCount[i] > maxLength)
			{
				maxLength = sequenceCount[i];
			}
		}
		
		System.out.println("Length of longest ascending subsequence: " + maxLength);
	}
}
