/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    10/26/11
 * Time:    8:47 PM
 *
 * Contains generic methods for operating upon an unweighted, directed graph's table.
 * This class utilizes matrices to store its connections.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UnweightedMatrixGraph {
    // Contains the connections in the graph.  If a direct connection exists between 0->1,
    // table[0][1] = 1;
    // If a direct connection between the two does not exist, table[0][1] = -1.
    int[][] table;

    // Used by the constructor to read in the graph's information file.
    Scanner fileScanner;

    // Used to store an examined file line and its components.
    String line;
    String[] pieces;


    // Constructs a graph out of the information in inputFile.
    public UnweightedMatrixGraph(String inputFile)
    {
        try
        {
            fileScanner = new Scanner(new File(inputFile));
        }

        catch(FileNotFoundException e)
        {
            System.out.println("The specified file was not found.");
        }

        // Get the number of nodes and edges from the first line.
        line = fileScanner.nextLine();
        pieces = line.split(" ");
        int numNodes = Integer.parseInt(pieces[0]);
        int numEdges = Integer.parseInt(pieces[1]);

        // Create an adjacency matrix of the appropriate size (numNodes x numNodes).
        table = new int[numNodes][numNodes];

        // We will store a (currently) nonexistent connection as -1 and connections between the same
        // node as 0.
        for(int i = 0; i < table.length; i++)
        {
            for(int j = 0; j < table[i].length; j++)
            {
                if(i == j)
                {
                    table[i][j] = 0;
                }

                else
                {
                    table[i][j] = -1;
                }
            }
        }

        // Run through the connections and add them to the graph of connections.
        for(int i = 0; i < numEdges; i++)
        {
            line = fileScanner.nextLine();
            pieces = line.split(" ");

            int origin = Integer.parseInt(pieces[0]);
            int destination = Integer.parseInt(pieces[1]);

            table[origin][destination] = 1;
        }
    }

    // Prints out the graph's table.
    public String toString()
    {
        String toReturn = "";

        for(int i = 0; i < table.length; i++)
        {
            for(int j = 0; j < table[i].length; j++)
            {
                if(table[i][j] != Integer.MAX_VALUE)
                {
                    toReturn += i + " --> " + j + ": " + table[i][j] + "\n";
                }
            }
        }

        return toReturn;
    }
}
