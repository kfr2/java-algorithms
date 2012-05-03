/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    9/19/11
 * Time:    8:40 PM
 *
 * An implementation of the single source shortest path (Dijkstra's) algorithm.
 */

import java.util.Scanner;

public class SingleSourceShortestPath {

    static MatrixGraph graph;
    static Scanner keyboardScanner;

    public static void main(String[] args)
    {
        // Scan the requested graph into its storage structure.
        keyboardScanner = new Scanner(System.in);
        System.out.print("Enter desired graph input file: ");
        graph = new MatrixGraph(keyboardScanner.nextLine());

        // Run Dijkstra's algorithm on this graph starting at node 0.
        singleSourceShortestPath(graph, 0);
    }


    public static void singleSourceShortestPath(MatrixGraph graph, int startNode)
    {
        // Get the table of node previousConnections.
        int[][] table = graph.table;

        // Get the list of which nodes have been visited.
        boolean[] visited = graph.visited;

        // Store the best distance between the startNode and each other node.
        // An unknown distance (infinity) is represented as Integer.MAX_VALUE.
        int[] distances = new int[visited.length];

        // Prefill our distances table with known values.
        for(int i = 0; i < distances.length; i++)
        {
            if(i == startNode)
            {
                distances[i] = 0;
            }

            // Fill in the known values in our distances table (direct previousConnections in the graph itself).
            // Because the previousConnections are stored as infinity in the table, we can pull direct values.
            else
            {
                distances[i] = table[startNode][i];
            }
        }

        // Mark the start known as visited.
        visited[startNode] = true;


        // Set the node to use as a reference point to calculate the distance to all the other nodes from startNode.
        for(int referenceNode = 0; referenceNode < table.length; referenceNode++)
        {
            // No reason to check the startNode as we've already prefilled its distances.
            if(referenceNode != startNode)
            {
                // Based off this reference node, calculate the distance from the startNode to all other nodes in the
                // graph.
                for(int i = 0; i < table.length; i++)
                {
                    // We don't need to examine from already-visited nodes because of greedy approach.
                    if(!visited[i])
                    {
                        // Contains the newDistance between the startNode and this node by going through
                        // referenceNode.  Assumes the route is unreachable by default.
                        int newDistance = Integer.MAX_VALUE;

                        // If a route is possible, calculate the new distance between startNode and this node when
                        // going through the reference node.
                        if(table[referenceNode][i] < Integer.MAX_VALUE)
                        {
                            newDistance = distances[referenceNode] + table[referenceNode][i];
                        }


                        // If this new distance is smaller than the previous, add it to the table of shortest paths
                        // to node i from startNode.
                        if(newDistance < distances[i])
                        {
                            distances[i] = newDistance;
                        }
                    }
                }

                // Mark the reference node as visited.
                visited[referenceNode] = true;
            }
        }

        // Print out the list of distances from startNode to each other node.
        System.out.println("Shortest path from " + startNode + " to...");
        for(int i = 0; i < distances.length; i++)
        {
            String distance = distances[i] == Integer.MAX_VALUE ? "unreachable" : Integer.toString(distances[i]);
            System.out.println(i + ": " + distance);
        }
    }
}
