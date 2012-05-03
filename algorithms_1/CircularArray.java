
public class CircularArray {
	
	static int[] values;
	static int size;
	static int nextAvailable;
	
	public static void main(String[] args){
		
		values = new int[15];
		size = values.length;
		nextAvailable = 0;
		
		// fill circular array
		for(int i = 4; i <= 60; i++){
			values[nextAvailable++] = i;
			
			if(nextAvailable == size) {
				nextAvailable = 0;
			}
		}
		
		
		// print out values
		for(int value : values){
			System.out.println(value);
		}
		
	}

}
