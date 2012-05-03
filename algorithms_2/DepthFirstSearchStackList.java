/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    9/13/11
 * Time:    10:05 PM
 *
 * A stack-based Depth First Search algorithm utilizing Linked Lists to store graph structure.
 */

import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Stack;

public class DepthFirstSearchStackList
{
    public static void main(String[] args) throws IOException
    {
        Scanner keyboardScanner = new Scanner(System.in);

        // Get the user's desired graph file and scan it in to a ListGraph.
        System.out.print("Enter desired graph input file: ");
        ListGraph graph = new ListGraph(keyboardScanner.nextLine());

        // Run DFS on the graph starting at node 0.
        depthFirstSearch(graph, 0);
    } // end main


    // Stack-based DepthFirstSearch starting at vertex on graph.
    public static void depthFirstSearch(ListGraph graph, int vertex)
    {
        // Store the node in a stack.
        Stack stack = new Stack();
        stack.push(vertex);

        // Mark the node as visited.
        graph.visited[vertex] = 1;

        // Examine all nodes on the stack.
        while(!stack.empty())
        {
            vertex = (Integer) stack.pop();
            System.out.println(vertex + " has been visited.");

            // Get this vertex's list of previousConnections (starting at the end of the list).
            LinkedList list = graph.connections.get(vertex);
            ListIterator listItr = list.listIterator(list.size());

            // Loop through the list backwards.  This is done so destinations will come out in numerical order.
            while(listItr.hasPrevious())
            {
                int destination = (Integer) listItr.previous();

                // If the node has not already been visited, add it to the stack for processing.
                if(graph.visited[destination] == 0)
                {
                    graph.visited[destination] = 1;
                    stack.push(destination);
                }
            }

        }
    } // end depthFirstSearch
} // end class
