/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    1/22/12
 * Time:    6:03 PM
 * 
 * The driver class for KnuthMorrisPratt's linear time search algorithm.
 */

public class KMP_Test
{
    static String[][] wordPairs = {
            {"word", "searchText"},
            {"", ""},
            {"AAAAA", "perhaps the phrase AAAA will be found somewhere within heAAAAA"},
            {"ABCDABD", "ABCDABCDABD"}
    };

    public static void main(String[] args)
    {
        for(String[] pair : wordPairs)
        {
            String word = pair[0];
            String searchText = pair[1];

            System.out.println(String.format("word: %s", word));
            System.out.println(String.format("text: %s", searchText));
            System.out.println(String.format("position: %s", KnuthMorrisPratt.search(word.toCharArray(),
                    searchText.toCharArray())));

            System.out.println("==========");
        }
    }
}
