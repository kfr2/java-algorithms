/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    9/13/11
 * Time:    2:26 PM
 *
 * Recursive DepthFirstSearch algorithm utilizing adjacency lists as a graph storage structure.
 */

import java.io.IOException;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.LinkedList;

public class DepthFirstSearchRecursionList
{

    public static void main(String[] args) throws IOException
    {
        Scanner keyboardScanner = new Scanner(System.in);

        // Get the user's desired graph file and scan it into a ListGraph.
        System.out.print("Enter desired graph input file: ");
        ListGraph graph = new ListGraph(keyboardScanner.nextLine());

        // Run DFS on the graph starting at node 0.
        depthFirstSearch(graph, 0);
    } // end main


    // Recursive depthFirstSearch of graph starting at node vertex.
    public static void depthFirstSearch(ListGraph graph, int vertex)
    {
        // Mark the vertex as visited.
        graph.visited[vertex] = 1;
        System.out.println(vertex + " has been visited.");

        // Get this vertex's list of previousConnections.
        LinkedList list = graph.connections.get(vertex);
        ListIterator listItr = list.listIterator();

        // Call DFS on each destination node that has not already been visited.
        while(listItr.hasNext())
        {
            int destination = (Integer) listItr.next();

            if(graph.visited[destination] == 0)
            {
                depthFirstSearch(graph, destination);
            }
        }
    } // end depthFirstSearch
} // end class
