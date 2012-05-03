/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    9/13/11
 * Time:    12:30 PM
 *
 * A rendition of the first informal Depth First Search assignment.
 */

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class DepthFirstSearchStackMatrix
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


    // Stack-based DepthFirstSearch starting at vertex on graph.
    public static void depthFirstSearch(MatrixGraph graph, int vertex)
    {
        // Store the node in a stack.
        Stack stack = new Stack();
        stack.push(vertex);

        // Mark the node as visited.
        graph.visited[vertex] = true;

        // Examine all nodes on the stack.
        while(!stack.empty())
        {
            int node = (Integer) stack.pop();
            System.out.println("Visited " + node);

            // Examine the graph table to determine which node to examine next.
            // Decrementing instead of incrementing so nodes are displayed in ascending, numerical order.
            for(int i = graph.table[node].length - 1; i >= 0; i--)
            {
                // If the node is adjacent to the current (and has not been visited), add it to the stack.
                if((graph.table[node][i] == 1) && (!graph.visited[i]))
                {
                    graph.visited[i] = true;
                    stack.push(i);
                }
            }
        }
    } // end depthFirstSearch
} // end class
