/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    9/13/11
 * Time:    7:53 AM
 *
 * Contains generic methods for operating upon a graph's table.
 * This class utilizes linked lists to store its connections..
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class ListGraph
{
    // Contains the direct connections in the graph.
    // This adjacency list stores a list of LinkedLists that will contain connections from any nodes.
    LinkedList<LinkedList> connections;

    // Tracks which nodes of this graph have been visited.
    int[] visited;

    // Used by the constructor to read in the graph's information file.
    Scanner fileScanner;

    // Used to store an examined file line and its components.
    String line;
    String[] pieces;


    // Constructs a graph out of the information in inputFile.
    public ListGraph(String inputFile)
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

        // Create an adjacency list to store origin nodes.
        connections = new LinkedList<LinkedList>();

        // Create a list for each of these connections (numNodes).
        for(int i = 0; i < numNodes; i++)
        {
            connections.add(i, new LinkedList<Integer>());
        }

        // Run through the edges and add them to the graph of connections..
        for(int i = 0; i < numEdges; i++)
        {
            line = fileScanner.nextLine();
            pieces = line.split(" ");

            int origin = Integer.parseInt(pieces[0]);
            int destination = Integer.parseInt(pieces[1]);

            // Add the origin's destination to the appropriate LinkedList.
            LinkedList<Integer> list = connections.get(origin);
            list.add(destination);
            connections.remove(origin);
            connections.add(origin, list);
        }

        // Create an array to track which nodes have been visited by the algorithm operating upon this graph.
        visited = new int[numNodes];
    }

    // Prints out the graph's table.
    public String toString()
    {
        String toReturn = "";

        // Look at each list of origin --> previousConnections in the main list.
        for(int origin = 0; origin < connections.size(); origin++)
        {
            // Get the list of previousConnections from this origin and print them out.
            LinkedList list = connections.get(origin);
            ListIterator listItr = list.listIterator();

            while(listItr.hasNext())
            {
                toReturn += origin + " --> " + listItr.next() + "\n";
            }
        }
        return toReturn;
    }
}
