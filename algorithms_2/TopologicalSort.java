/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    11/21/11
 * Time:    1:52 PM
 *
 * Use a topological sort algorithm to return the topological order of the user-chosen
 * graph file.
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TopologicalSort
{
    static ListGraph graph;
    static Scanner scanner = new Scanner(System.in);
    static Queue<Integer> zeroQueue = new LinkedList<Integer>();
    static int[] inDegree;

    // Get user's desired graph file and run topological sort on it.
    public static void main(String[] args)
    {
        System.out.print("Desired graph file: ");
        graph = new ListGraph(scanner.nextLine());
        topologicalSort(graph);
    }

    public static void topologicalSort(ListGraph graph)
    {
        // Preprocess the graph file.
        preprocess(graph);

        // while there is an unlisted vertex
        int counter = 0;
        while(counter < graph.connections.size())
        {
            // If there is a vertex with inDegree 0...
            if(!zeroQueue.isEmpty())
            {
                // List the node and remove all edges stemming from it.
                int node = zeroQueue.remove();
                System.out.print(node + " ");

                LinkedList<Integer> origin = graph.connections.get(node);
                for(Integer destination : origin)
                {
                    inDegree[destination]--;

                    if(inDegree[destination] == 0)
                    {
                        zeroQueue.add(destination);
                    }
                }
            }

            // Otherwise, a cycle exists and the sort should halt.
            else
            {
                System.out.println("==> A cycle has been detected.  Execution has halted.");
                break;
            }

            counter++;
        }
    }

    public static void preprocess(ListGraph graph)
    {
        // Create an array to track the number of times each node has an incoming connection.
        inDegree = new int[graph.connections.size()];

        // Create a zero queue (indegree = 0) containing all elements by default.
        for(int i = 0; i < graph.connections.size(); i++)
        {
            zeroQueue.add(i);
        }

        // Run through the linked lists to count inDegrees.
        // If a node ever exists as a destination, remove it from the zeroQueue.
        for(LinkedList<Integer> origin : graph.connections)
        {
            for(Integer destination : origin)
            {
                inDegree[destination]++;

                // If the destination node exists on the zeroQueue, remove it.
                if(zeroQueue.contains(destination))
                {
                    zeroQueue.remove(destination);
                }
            }
        }
    }
}
