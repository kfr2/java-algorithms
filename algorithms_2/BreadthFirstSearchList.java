/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    9/13/11
 * Time:    10:34 PM
 *
 * An implementation of a Breadth First Search algorithm utilizing adjacency lists as a graph storage structure.
 */
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Queue;

public class BreadthFirstSearchList
{
    public static void main(String[] args) throws IOException
    {
        Scanner keyboardScanner = new Scanner(System.in);

        // Get the user's desired graph file and scan it in to a ListGraph.
        System.out.print("Enter desired graph input file: ");
        ListGraph graph = new ListGraph(keyboardScanner.nextLine());

        // Run BFS on the graph starting at node 0.
        breadthFirstSearch(graph, 0);
    } // end main


    // Queue-based Breadth First Search starting at vertex on graph.
    public static void breadthFirstSearch(ListGraph graph, int vertex)
    {
        // Store the node in a queue.
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(vertex);

        // Mark the node as visited.
        graph.visited[vertex] = 1;

        // Examine all nodes in the queue.
        while(!queue.isEmpty())
        {
            vertex = queue.remove();
            System.out.println(vertex + " has been visited.");

            // Get this vertex's list of previousConnections (starting at the beginning).
            LinkedList list = graph.connections.get(vertex);
            ListIterator listItr = list.listIterator();

            // Loop through the list and add unvisited previousConnections to the queue.
            while(listItr.hasNext())
            {
                int destination = (Integer) listItr.next();

                if(graph.visited[destination] == 0)
                {
                    graph.visited[destination] = 1;
                    queue.add(destination);
                }
            }

        }
    } // end breadthFirstSearch
} // end class
