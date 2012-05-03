/**
 * Stores information about a Biconnected component.
 * Based off of http://www.ibluemojo.com/school/ArticPointDFS.java
 *
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    10/11/11
 * Time:    7:54 PM
 */

package us.magically.kevin.java.biconnectedcomponents;

import java.util.LinkedList;

class BiconnectedComponent
{
	int id;
	
	// Stores the list of edges in this BCC.
	LinkedList edgeList;


	public BiconnectedComponent(int id)
	{
		this.id = id;
		edgeList = new LinkedList();
	}
}

