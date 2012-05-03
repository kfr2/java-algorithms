/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    2011-Dec-7
 * Time:    9:00 AM
 *
 * Contains generic methods for operating upon a graph's table.
 * This class utilizes an array list of BridgeNodes to store its connections
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BridgeNodeGraph
{
    // This array list will contain an easy way to reference individual nodes as we build the graph.
    ArrayList<BridgeNode> connections;

    // Used by the constructor to read in the graph's information file.
    Scanner fileScanner;

    // Used to store an examined file line and its components.
    String line;
    String[] pieces;


    // Constructs a graph out of the information in inputFile.
    BridgeNodeGraph(String inputFile)
    {
        try
        {
            fileScanner = new Scanner(new File(inputFile));
        }

        catch(FileNotFoundException e)
        {
            System.out.println("The specified file was not found.");
            System.exit(-1);
        }

        // Get the number of nodes and edges from the first line.
        line = fileScanner.nextLine();
        pieces = line.split(" ");
        int numNodes = Integer.parseInt(pieces[0]);
        int numEdges = Integer.parseInt(pieces[1]);

        // Create an array list to store nodes.
        connections = new ArrayList<BridgeNode>();

        // Create an empty node in the list for each node in the graph.
        for(int i = 0; i < numNodes; i++)
        {
            connections.add(i, new BridgeNode(i));
        }

        // Run through the connections and connect nodes where appropriate.
        for(int i = 0; i < numEdges; i++)
        {
            line = fileScanner.nextLine();
            pieces = line.split(" ");

            int origin = Integer.parseInt(pieces[0]);
            int destination = Integer.parseInt(pieces[1]);

            // Get the origin and destination nodes.
            BridgeNode originNode = connections.get(origin);
            BridgeNode destinationNode = connections.get(destination);

            // Add the destination node to origin's connection and the origin node to the destination node's
            // connections if these connections do not already exist.
            if(!originNode.connections.contains(destinationNode))
            {
                originNode.connections.add(destinationNode);
            }

            if(!destinationNode.connections.contains(originNode))
            {
                destinationNode.connections.add(originNode);
            }
        }
    }


    public String toString()
    {
        String toReturn = "Connections\n----------\n";

        for(BridgeNode node : connections)
        {
            toReturn += node.getValue() + " [" + node.depth + "]: ";

            for(BridgeNode child : node.connections)
            {
                toReturn += child.getValue() + " ";
            }

            toReturn += "\n";
        }

        return toReturn;
    }
}
