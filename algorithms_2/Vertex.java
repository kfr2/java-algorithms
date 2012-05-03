/**
 * This class stores a vertex's information.
 * Based off of http://www.ibluemojo.com/school/ArticPointDFS.java
 *
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    10/11/11
 * Time:    7:54 PM
 **/

package us.magically.kevin.java.biconnectedcomponents;

import java.util.LinkedList;

class Vertex
{
	int id;
	
	// the lowest tree level reachable from this verte// the lowest tree level reachable from this vertexx
	int low;		

	int dfsNum;
	// tree level of this vertex in DFS
	int dfsLevel;

	int numChildren;

	// list of edges involving this vertex
	LinkedList eList;	

	// Create a vertex with given ID number.
	public Vertex(int id)
	{
		this.id = id;
		dfsNum = -1;					// Initially not involved in DFS.
		eList = new LinkedList();	// list of adjacency edges is initially empty.
	}
}
