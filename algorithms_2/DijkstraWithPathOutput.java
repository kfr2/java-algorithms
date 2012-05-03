/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    10/26/11
 * Time:    7:21 PM
 *
 * An implementation of the single source shortest path (Dijkstra's) algorithm
 * modified to use a "comeFrom" value to print the nodes comprising shortest paths.
 */

import java.util.Scanner;

public class DijkstraWithPathOutput {

    static MatrixGraph graph;
    static int[] previousConnections;
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

        // Maintain a list of "comeFrom" values -- the node with shortest path connection from startNode to node
        // previousConnections[node].
        previousConnections = new int[table.length];

        // Store the best distance between the startNode and each other node.
        // An unknown distance (infinity) is represented as Integer.MAX_VALUE.
        int[] distances = new int[table.length];

        // Prefill our distances table with known values.
        // Also fill the previousConnections out to start with no comeFroms.
        for(int i = 0; i < distances.length; i++)
        {
            previousConnections[i] = -1;

            if(i == startNode)
            {
                distances[i] = 0;
                previousConnections[i] = startNode;
            }

            // Fill in the known values in our distances table (direct previousConnections in the graph itself).
            // Because the previousConnections are stored as infinity in the table, we can pull direct values.
            // We can also have the startNode as the comeFrom for these values since a direct connection exists.
            else
            {
                distances[i] = table[startNode][i];
                previousConnections[i] = startNode;
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
                        // to node i from startNode.  Mark the reference node as the comeFrom.
                        if(newDistance < distances[i])
                        {
                            distances[i] = newDistance;
                            previousConnections[i] = referenceNode;
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
            // Print out the distance from startNode to i.
            String distance = distances[i] == Integer.MAX_VALUE ? "unreachable" : Integer.toString(distances[i]);
            System.out.println(i + ": " + distance);

            // Print the path from startNode to i by building the path backwards.
            String path = "";

            int currentNode = i;
            int comeFrom = previousConnections[currentNode];

            while(comeFrom != -1)
            {
                path += currentNode + " ";

                if(currentNode != startNode)
                {
                    path += ">-- ";
                }

                if(currentNode == startNode)
                {
                    break;
                }

                currentNode = comeFrom;
                comeFrom = previousConnections[currentNode];
            }

            // Print out the path in the correct direction.
            System.out.println("\tPath: " + new StringBuffer(path).reverse().toString());
        }
    }
}
