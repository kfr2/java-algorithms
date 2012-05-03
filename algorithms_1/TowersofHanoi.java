/**
 * Solving towers of Hanoi using divide and conquer techniques.
 * Kevin Richardson <kevin@magically.us>
 * 17-Mar-2011
 **/
public class TowersofHanoi {
	
	public static void main(String[] args) {
		move(2, 'A', 'B', 'C');
	}
	
	// move n discs from source to target using spare
	public static void move(int n, char source, char target, char spare) {
		if(n == 1){
			System.out.println("Move top disc from " + source + " to " + target + ".");
		}
		
		else{
			move(n-1, source, spare, target);
			System.out.println("Move top disc from " + source + " to " + target + ".");
			move(n-1, spare, target, source);
		}
	}
}
