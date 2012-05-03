/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    2/16/12
 * Time:    12:49 PM
 */
public class DFA_Test {
    public static void main(String[] args)
    {
        DFA_State state = new DFA_State();

        for(String string : state.stringsToCheck)
        {
            System.out.println(state.isContainedInLanguage(string));
        }
    }
}
