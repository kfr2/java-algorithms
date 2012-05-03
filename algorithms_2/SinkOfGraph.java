/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    11/17/11
 * Time:    7:21 AM
 *
 * Methods for determining the sink of a graph.  This class assumes each graph has exactly one sync and that
 * all other nodes point to the sink.
 */

import java.util.Scanner;

public class SinkOfGraph
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        // Create a new unweighted, directed graph based off specified input file.
        System.out.print("Name of unweighted, directed graph file: ");
        UnweightedMatrixGraph graph = new UnweightedMatrixGraph(scanner.nextLine());

        // Find the sink of the graph.
        System.out.println("Sink of this graph: " + findSink(graph));
    }

    // Returns the sink of the specified graph.
    public static int findSink(UnweightedMatrixGraph graph)
    {
        // If we only have one node, it must be the sink.
        if(graph.table.length == 1){ return 0; }

        // Store the last node in the table (so we know when to stop examining).
        int lastNode = graph.table.length - 1;

        // The nodes to initially examine (first and second).
        int origin = 0;
        int destination = 1;

        // Examine nodes until the sink is found.
        while(true)
        {
            // Final possibilities for sink:
            // originNode == lastNode (lastNode is the sink)
            // destination > lastNode (origin is the sink)
            if(origin == lastNode){ return lastNode; }
            else if(destination > lastNode){ return origin; }

            // Does origin connect to destination? If so, it is not the sink.
            if(graph.table[origin][destination] == 1)
            {
                origin++;
            }

            // If it does not, destination is not the sink.
            else
            {
                destination++;
            }
        }
    }
}
