/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    11/16/11
 * Time:    8:37 PM
 *
 * Processes a graph to determine ancestry between two specified nodes.
 */

import java.util.Scanner;

public class TreeAncestry
{
    static NodeGraph graph;
    static Scanner scanner;

    /**
     *     Get the user's desired graph file.  Allow him/her to run ancestry lookups until s/he decides to exit.
     */
    public static void main(String[] args)
    {
        scanner = new Scanner(System.in);

        System.out.print("Desired graph file: ");
        graph = new NodeGraph(scanner.nextLine());

        // Process the graph's ancestries starting at the root.
        processAncestry(graph.connections.get(0));

        // Allow the user to query the graph's ancestry until s/he decides to exit.
        String query = "";
        while(!query.toUpperCase().equals("E"))
        {
            System.out.println("Enter two nodes - a b - to query or E to exit: ");
            query = scanner.nextLine();

            if(!query.toUpperCase().equals("E"))
            {
                String[] nodes = query.split(" ");
                try
                {
                    int aIndex = Integer.parseInt(nodes[0]);
                    int bIndex = Integer.parseInt(nodes[1]);

                    Node a = graph.connections.get(aIndex);
                    Node b = graph.connections.get(bIndex);

                    if(a != null && b != null)
                    {
                        System.out.println("Result: " + ancestry(a, b));
                    }

                    else
                    {
                        System.out.println("Invalid nodes -- please try again.");
                    }
                }

                catch(NumberFormatException e)
                {
                    System.out.println("Nodes must be numeric and may include: ");

                    for(Node node : graph.connections)
                    {
                        System.out.print(node.getValue() + " ");
                    }

                    System.out.println();
                }
            }
        }

        System.out.println("Goodbye -- have a nice day!");
    }

    /**
     * Runs through the tree starting at node to determine ancestry relationships.
     */
    public static void processAncestry(Node node)
    {
        // Create the node's ancestry path.
        if(node.parent != null)
        {
            node.path = node.parent.path + " " + node.parent.getValue();
        }

        // Continue processing ancestry on each of this node's children.
        for(Node child : node.children)
        {
            processAncestry(child);
        }
    }

    /**
     * Allow the user to query the ancestry relationship of the two specified nodes.
     * Returns 0 if the nodes are not related, 1 if n is the ancestor of c, and
     * -1 if c is the ancestor of n.
     */
    public static int ancestry(Node a, Node b)
    {
        // If the node to examine is the same, no need to process any further.
        if(a == b) return 0;

        // If a or b is the root, no need to process any further.
        if(a.getValue() == 0) return 1;
        else if(b.getValue() == 0) return -1;


        // Split each node's ancestry path into "chunks" of nodes by splitting at spaces.
        String[] aChunks = a.path.split(" ");
        String[] bChunks = b.path.split(" ");


        // Track the difference in tree level between the two nodes.
        int treeDifference = bChunks.length - aChunks.length;


        // If the nodes reside on the same level of the tree, they cannot be related.
        if(treeDifference == 0) return 0;

        // If node b resides further down the tree, determine whether node a can be its ancestor.
        else if(treeDifference > 0)
        {
            // If a is in the appropriate place in b's ancestry, it is the ancestor.
            if(bChunks[aChunks.length].equals(String.valueOf(a.getValue())))
            {
                return 1;
            }

            // Otherwise, the nodes are not related.
            return 0;
        }

        // Otherwise, node b must be higher in the tree than node a.  Determine if node b is node a's ancestor.
        else
        {
            // If b is in the appropriate place in a's ancestry, it is the ancestor.
            if(aChunks[bChunks.length].equals(String.valueOf(b.getValue())))
            {
                return -1;
            }

            // Otherwise, the nodes are not related.
            return 0;
        }
    }
}
