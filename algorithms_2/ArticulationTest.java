/**
 * Allows the user to test the ArticulationPointDFS algorithm by letting him or her choose the graph file to run the
 * algorithm on.
 *
 * Graph file format:
 * (numNodes) (numEdges)
 * (node1 origin) (node1 destination)
 * ...
 * (nodeN origin) (nodeN destination)
 *
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    10/11/11
 * Time:    7:54 PM
 */

package us.magically.kevin.java.biconnectedcomponents;

public class ArticulationTest
{
    public static void main(String[] args)
    {
        // Setup the graph file for depth-first search.  The APDFS constructor asks the user for his/her graph file
        // and imports the graph into its internal data structure.
        ArticulationPointDFS graph = new ArticulationPointDFS();

        // Run DFS to find any articulation points (and biconnected components).
        graph.doArticulationPointDFS();

        // Display the results of the DFS.
        graph.showResults();
    }
}
