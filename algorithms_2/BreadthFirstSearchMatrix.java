/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    9/13/11
 * Time:    12:23 PM
 *
 * An implementation of a Breadth First Search algorithm utilizing adjacency matrices as a graph storage structure.
 */

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

public class BreadthFirstSearchMatrix
{
    public static void main(String[] args) throws IOException
    {
        Scanner keyboardScanner = new Scanner(System.in);

        // Get the user's desired graph file and scan it in to the graph table.
        System.out.print("Enter desired graph input file: ");
        MatrixGraph graph = new MatrixGraph(keyboardScanner.nextLine());

        // Run BFS on the graph starting at node 0.
        breadthFirstSearch(graph, 0);
    } // end main


    // Breadth First Search starting at vertex on graph.
    public static void breadthFirstSearch(MatrixGraph graph, int vertex)
    {
       // Store the node in a queue.
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(vertex);

        // Mark the node as visited.
        graph.visited[vertex] = true;

        // Examine all nodes in the queue.
        while(!queue.isEmpty())
        {
            vertex = queue.remove();
            System.out.println(vertex + " has been visited.");

            // Examine the graph table to determine which node to examine next.
            for(int i = 0; i < graph.table[vertex].length; i++)
            {
                // If the node is adjacent to the current (and has not been visited), add it to the queue.
                if((graph.table[vertex][i] == 1) && (!graph.visited[i]))
                {
                    graph.visited[i] = true;
                    queue.add(i);
                }
            }
        }
    } // end breadthFirstSearch
} // end class
