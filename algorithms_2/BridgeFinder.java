/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    2011-Dec-8
 * Time:    5:51 PM
 *
 * Given a graph file, this class detects and outputs any bridges in the structure (should a
 * bridge or bridges exist) in O(|V| + |E|).
 */

import java.util.Scanner;

public class BridgeFinder
{
    static BridgeNodeGraph graph;
    static int depthCount = 0;
    static int bridgeCount = 0;

    public static void main(String[] args)
    {
        // Acquire the user's graph file.
        Scanner scan = new Scanner(System.in);
        System.out.println("Graph bridge finder\n====================");
        System.out.print("Name of graph file: ");
        graph = new BridgeNodeGraph(scan.nextLine());

        // Detect any bridges in the graph starting at node 0.
        detectEdges(graph, 0);
    }

    // Given a BridgeNodeGraph, this function prints out any bridges that exist in the structure.
    // If no bridges are found, the method will tell the user.
    // The method will start examining at the specified node.
    public static void detectEdges(BridgeNodeGraph graph, int startNode)
    {
        depthFirstSearch(graph.connections.get(startNode), null);

        System.out.println("Number of bridges found: " + bridgeCount);
    }

    // Run DFS on the graph from the specified vertex.
    // Parent tracks the node from which this vertex is being visited.
    public static void depthFirstSearch(BridgeNode vertex, BridgeNode parent)
    {
        vertex.visited = true;
        vertex.depth = depthCount++;

        // Examine the connections to this node.
        for(BridgeNode connection : vertex.connections)
        {
            // Don't examine the parent again.
            if(connection != parent)
            {
                // If unvisited, run DFS from this connection.
                if(!connection.visited)
                {
                    // vertex - connection is a forward edge.
                    depthFirstSearch(connection, vertex);

                    // A bridge exists when the depth values of two connecting nodes differs after DFS.
                    if(connection.depth > vertex.depth)
                    {
                        System.out.println("A bridge was detected: " + vertex.getValue() + "-" + connection.getValue());
                        bridgeCount++;
                    }

                    // Update depth values to correspond to the DFS.
                    vertex.depth = Math.min(vertex.depth, connection.depth);
                }

                // Otherwise, vertex - connection is a back edge.  Update vertex's depth value.
                else
                {
                    vertex.depth = Math.min(vertex.depth, connection.depth);
                }
            }
        }

    }
}
