/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    9/12/11
 * Time:    1:30 PM
 *
 * A recursive implementation of a Depth First Search algorithm utilizing adjacency matrices as a
 * graph storage structure.
 */

import java.io.IOException;
import java.util.Scanner;

public class DepthFirstSearchRecursionMatrix
{
    public static void main(String[] args) throws IOException
    {
        Scanner keyboardScanner = new Scanner(System.in);

        // Get the user's desired graph file and scan it in to the graph table.
        System.out.print("Enter desired graph input file: ");
        MatrixGraph graph = new MatrixGraph(keyboardScanner.nextLine());

        // Run DFS on the graph starting at node 0.
        depthFirstSearch(graph, 0);
    } // end main


    // Recursive DepthFirstSearch starting at vertex on graph.
    public static void depthFirstSearch(MatrixGraph graph, int vertex)
    {
        // Mark the vertex as visited.
        graph.visited[vertex] = true;
        System.out.println(vertex + " has been visited.");

        // Examine the graph table to determine which node to examine next.
        for(int i = 0; i < graph.table[vertex].length; i++)
        {
            // If the node is adjacent to the current (and has not been visited), run DFS on it.
            if((graph.table[vertex][i] == 1) && (!graph.visited[i]))
            {
                depthFirstSearch(graph, i);
            }
        }
    } // end depthFirstSearch
} // end class
