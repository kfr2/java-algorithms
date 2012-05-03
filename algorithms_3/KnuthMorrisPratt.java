/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    02/02/12
 * Time:    8:31 AM
 *
 * An implementation of Knuth-Morris-Pratt's linear-time string-matching algorithm.
 */

public class KnuthMorrisPratt
{
    // Returns the position within searchText that word begins.
    // If word is not found, returns searchText.size.
    static int search(char[] word, char[] searchText)
    {
        // m is the beginning of the current match in searchText.
        int m = 0;
        // i is the current character in word.
        int i = 0;

        // Holds the partial match table (see buildTable())
        int[] T = table(word);
        
        for(int value : T)
        {
            System.out.print(value + " ");
        }
        
        int k = searchText.length;
        while(m + i < k)
        {
            if(word[i] == searchText[m + i])
            {
                if(i == word.length - 1)
                {
                    return m;
                }
                
                i++;
            }
            
            else
            {
                m = m + i - T[i];
                
                if(T[i] > -1)
                {
                    i = T[i];
                }

                else
                {
                    i = 0;
                }
            }
        }

        // S has been searched unsuccessfully.
        return k;
    }


    // Builds the partial match table for use within search().
    // This table holds "fall back positions" for characters within the string.
    private static int[] table(char[] word)
    {
        // The current position to compute in the output table.
        int pos = 2;
        // The current index of word we are examining.
        int cnd = 0;

        // Prepare the default values of the output table.
        int len = (word.length > 2) ? word.length : 2;
        int[] partials = new int[len];
        partials[0] = -1;
        partials[1] = 0;
        
        int k = word.length;
        while(pos < k)
        {
            // The substring continues (ex:  "000" => 0 -> 0 -> 0)
            if(word[pos - 1] == word[cnd])
            {
                partials[pos] = ++cnd;
                pos++;
            }

            // Substring ends but we can fall back to a previous value.
            else if(cnd > 0)
            {
                cnd = partials[cnd];
            }

            // No further candidates exist for the substring.
            else
            {
                partials[pos] = 0;
                pos++;
                cnd = 0;
            }
        }
        
        return partials;
    }
}
