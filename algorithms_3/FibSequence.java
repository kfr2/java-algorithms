import java.util.HashMap;

/**
 * Kevin Richardson <kevin@magically.us>
 *
 */
public class FibSequence {
	
	public static HashMap<Integer, Long> map = new HashMap<Integer, Long>();

	public static Long fib(Integer n)
	{
		if(map.containsKey(n)) return map.get(n);

		if(n.intValue() == 0) return new Long(0);
		if(n.intValue() == 1) return new Long(1);
				
		Long newValue = fib(new Integer(n.intValue() - 2)) + fib(new Integer(n.intValue() - 1));
		map.put(n, newValue);
		
		return newValue;
	}
	
	public static void main(String[] args)
	{
		System.out.println(fib(48));
		System.out.println(fib(47));
	}
}
