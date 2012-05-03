/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    2011-Nov-28
 * Time:    6:00 PM EST
 *
 * Determines whether or not it is possible to color the adjacent vertices of a specified graph using two colors
 * such that no two adjacent vertices have the same color.
 *
 * An implementation of a Breadth First Search algorithm utilizing adjacency matrices as a graph storage structure
 * is used in this algorithm.
 */

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ColorAdjacentVertices
{
    // Definitions to make it easier to access color values.
    static final int COLOR_ONE = -1;
    static final int COLOR_TWO = 1;
    static final int NO_COLOR  = 0;
    static int CURRENT_COLOR = NO_COLOR;

    // Have a table hold the color status of nodes.
    static int[] graphColors;

    public static void main(String[] args) throws IOException
    {
        Scanner keyboardScanner = new Scanner(System.in);

        // Get the user's desired graph file and scan it in to the graph table.
        System.out.print("Enter desired graph input file: ");
        MatrixGraph graph = new MatrixGraph(keyboardScanner.nextLine());

        // Create a table to hold the color status of nodes as they are examined.
        graphColors = new int[graph.table.length];

        // Determine whether or not this graph can be colored as per the problem statement.
        // Start examining from node 0.
        colorGraph(graph, 0);
    } // end main


    // Determine whether or not we can correctly color the graph starting at vertex on graph.
    // This method will print to standard output the color possibilities (possible or not possible).
    public static void colorGraph(MatrixGraph graph, int vertex)
    {
        // Store the starting vertex in a queue.
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(vertex);

        // Give it a color.
        CURRENT_COLOR = COLOR_ONE;
        graphColors[vertex] = CURRENT_COLOR;

        // Mark the node as visited.
        graph.visited[vertex] = true;

        // Examine all nodes in the queue.
        while(!queue.isEmpty())
        {
            vertex = queue.remove();

            // Switch the current color so adjacent nodes can be colored.
            if(CURRENT_COLOR == COLOR_ONE)
            {
                CURRENT_COLOR = COLOR_TWO;
            }

            else
            {
                CURRENT_COLOR = COLOR_ONE;
            }

            // Examine the graph table to determine which node to examine next.
            for(int i = 0; i < graph.table[vertex].length; i++)
            {
                // If this node is adjacent, check to see if it has the same color.
                // If it does, the graph cannot be correctly colored.
                if(graph.table[vertex][i] == 1)
                {
                    if(graphColors[vertex] == graphColors[i])
                    {
                        System.out.println("This graph CANNOT be colored correctly.");
                        System.exit(0);
                    }
                }

                // If the node is adjacent to the current (and has not been visited), add it to the queue.
                // Also, color the node appropriately.
                if((graph.table[vertex][i] == 1) && (!graph.visited[i]))
                {
                    queue.add(i);
                    graph.visited[i] = true;
                    graphColors[i] = CURRENT_COLOR;
                }
            }
        }

        // At this point, the graph has been colored without any problems.
        System.out.println("This graph can be colored correctly.");
    } // end colorGraph
} // end class
