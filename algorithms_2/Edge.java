/**
 * Stores an edge's information.
 * Based off of http://www.ibluemojo.com/school/ArticPointDFS.java
 *
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    10/11/11
 * Time:    7:54 PM
 */

package us.magically.kevin.java.biconnectedcomponents;

class Edge
{
	// Two vertices comprising this edge.
	Vertex first, second;	

	
	// Create a new edge with the given vertices.
	public Edge(Vertex first, Vertex second)
	{
		this.first = first;
		this.second = second;
	}

	// Return the string representation of the edge in the form of a pair of vertices.
	public String toString()
	{
		return "(" + first.id + ", " + second.id + ")";
	}

	// Check if two edges are equal by comparing the vertices.
	boolean equal(Vertex a, Vertex b)
	{
		return (a.id == first.id) && (b.id == second.id);
	}
}
