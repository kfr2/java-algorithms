/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    2011-Sep-13
 * Time:    7:53 AM
 *
 * Contains generic methods for operating upon a graph's table.
 * This class utilizes an array list of nodes to store its connections
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NodeGraph
{
    // This array list will contain an easy way to reference individual nodes as we build the graph.
    ArrayList<Node> connections;

    // Used by the constructor to read in the graph's information file.
    Scanner fileScanner;

    // Used to store an examined file line and its components.
    String line;
    String[] pieces;


    // Constructs a graph out of the information in inputFile.
    NodeGraph(String inputFile)
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
        connections = new ArrayList<Node>();

        // Create an empty node in the list for each node in the graph.
        for(int i = 0; i < numNodes; i++)
        {
            connections.add(i, new Node(i));
        }

        // Run through the connections and connect nodes where appropriate.
        for(int i = 0; i < numEdges; i++)
        {
            line = fileScanner.nextLine();
            pieces = line.split(" ");

            int origin = Integer.parseInt(pieces[0]);
            int destination = Integer.parseInt(pieces[1]);

            // Get the origin and destination nodes.
            Node originNode = connections.get(origin);
            Node destinationNode = connections.get(destination);;

            // Add the destination node to origin's children and the origin node to the destination node's parent.
            originNode.children.add(destinationNode);
            destinationNode.parent = originNode;
        }
    }


    public String toString()
    {
        String toReturn = "Children\n----------\n";

        for(Node node : connections)
        {
            toReturn += node.getValue() + ": ";

            for(Node child : node.children)
            {
                toReturn += child.getValue() + " ";
            }

            toReturn += "\n";
        }

        toReturn += "Parents\n----------\n";

        for(Node node : connections)
        {
            toReturn += node.getValue() + ": ";
            if(node.parent != null)
            {
                toReturn += node.parent.getValue();
            }
            toReturn += "\n";
        }

        return toReturn;
    }
}
