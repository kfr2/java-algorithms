/* --------------------------------------------------------------------------
 * file:		AllPairsShortestPath.java
 * description:	Calculates the shortest paths between two points on a graph.
 * author:		Kevin Richardson <kevin@magically.us>
 * date:		11-Feb-2011
 * --------------------------------------------------------------------------
 */

public class AllPairsShortestPath {
	
	public static void main(String[] args) {
		
		final double INF = Double.POSITIVE_INFINITY;
		
		// establish distances between points on the graph. This is G0
		double[][] g = {
						{0, 5, 1, INF, INF, INF, INF, INF},			// a
						{INF, 0, INF, INF, INF, INF, INF, INF},		// b
						{INF, INF, 0, 3, 6, 12, INF, INF},			// c
						{INF, INF, INF, 0, INF, INF, INF, 4},		// d
						{INF, INF, INF, INF, 0, 2, INF, INF},		// e
						{INF, INF, INF, INF, INF, 0, 4, INF},		// f
						{INF, INF, INF, INF, INF, INF, 0, INF},		// g
						{10, 20, INF, INF, INF, INF, INF, 0}		// h
					};
				
		
		// k -- number of generations to calculate. (G0...Gk)
		for(int k = 0; k < g.length; k++){
			for(int i = 0; i < g.length; i++){
				for(int j = 0; j < g[i].length; j++){
					g[i][j] = Math.min(g[i][j], (g[i][k] + g[k][j]));
				}
			}
		}
		
		
		// print out the table of generation G*
		for(int i = 0; i < g.length; i++){
			for(int j = 0; j < g[i].length; j++){
				int cost = (int)g[i][j];
				String toPrint = (cost == Integer.MAX_VALUE) ? "X" : Integer.toString(cost);
				
				// simple padding to make the resulting table look nicer.
				if(toPrint.length() == 1){ toPrint = " " + toPrint; }
				
				System.out.print(toPrint + " ");
			}
			
			System.out.println();
		}
						
	}
}
