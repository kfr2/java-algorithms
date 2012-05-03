/**
 * Methods for finding the Articulation Points of a graph using DFS.
 * Based off of http://www.ibluemojo.com/school/ArticPointDFS.java
 *
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    10/11/11
 * Time:    7:54 PM
 */

package us.magically.kevin.java.biconnectedcomponents;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

class ArticulationPointDFS
{
	// Contains the graph's list of vertexes.
	private Vertex[] graph;

	// Information about the graph (vertices, edges, and biconnected components).
	private int numVertices, numEdges, numBCC;

	// Used to assign DFS numbers to each vertex.
	private int dfsCounter;

	// Holds various lists.
	private ArrayList articulationPointList, bccList;

	// The stack used to hold upcoming vertices for DFS.
	private Stack visited;

	// Used to receive graph file information.
	private Scanner keyboardScanner, fileScanner;
	private String line;
	private String[] pieces;


	/**
	 * Create a new graph for articulation point search.
	 */
	public ArticulationPointDFS()
	{
		// Get the graph input file from the user.
        System.out.print("Graph input file: ");
		keyboardScanner = new Scanner(System.in);

		try
		{
			fileScanner = new Scanner(new File(keyboardScanner.nextLine()));
		}

		catch(FileNotFoundException e)
		{
			System.out.println("The graph input file was not found.");
		}

		// Get the number of vertices and edges from the first line.
		line = fileScanner.nextLine();
		pieces = line.split(" ");
	 	numVertices = Integer.parseInt(pieces[0]);
	 	numEdges = Integer.parseInt(pieces[1]);

		// Set up the basic graph.
		graph = new Vertex[numVertices];
		for(int i = 0; i < numVertices; i++)
		{
			graph[i] = new Vertex(i);
		}

		// Add each edge to the graph.
		for(int i = 0; i < numEdges; i++)
		{
			line = fileScanner.nextLine();
			pieces = line.split(" ");

			int origin = Integer.parseInt(pieces[0]);
			int destination = Integer.parseInt(pieces[1]);

			addEdge(origin, destination);
		}

		// Setup the other various lists and structures used to traverse
		// this graph.
		articulationPointList = new ArrayList();
		bccList = new ArrayList();
		visited = new Stack();
		dfsCounter = 1;
		numBCC = 1;
	}


	/**
	 * Add an edge to the list of edges in the graph.
	 */
	public void addEdge(int origin, int destination)
	{
		Vertex startNode = graph[origin];
		Vertex endNode = graph[destination];

		// Add the respective vertice to the edge list of the opposite vertice.
		startNode.eList.add(destination);
		endNode.eList.add(origin);
	}


	/**
	 * Perform recursive DFS starting at vertex.
	 * All information needed to find articulation points and BCCs will
	 * be stored and updated.
	 */
	public void doArticulationPointDFS(Vertex vertex)
	{
        if(vertex == null)
        {
            return;
        }

		// Set DFS info. of vertex.
		vertex.dfsNum = dfsCounter++;
		vertex.low = vertex.dfsNum;

		// Run through all unvisited, connected nodes.
		Iterator iter = vertex.eList.iterator();

		while(iter.hasNext())
		{
            Integer nextVertexId = (Integer)iter.next();
			Vertex nextVertex = graph[nextVertexId];

            // if the nextVertex is unvisited...
            if(nextVertex.dfsNum == -1)
            {
                // The next vertex is one level higher than the current vertex.
                nextVertex.dfsLevel = vertex.dfsLevel + 1;
                vertex.numChildren += 1;

                // Add this edge to the stack.
                visited.push(new Edge(vertex, nextVertex));

                // Recursively perform DFS on the children nodes of the current vertex.
                doArticulationPointDFS(nextVertex);


                // The lowest DFS level of the current node is either its current value or the value of nextNode.
                vertex.low = Math.min(vertex.low, nextVertex.low);


                // Determine if any articulation points exist based on lowest value/dfs level.
                // We're at the root of the dfs tree...
                if(vertex.dfsNum == 1)
                {
                    if(vertex.numChildren >= 2)
                    {
                        if(!articulationPointList.contains(vertex))
                        {
                            articulationPointList.add(vertex);
                        }
                    }

                    retrieveBCCEdges(vertex, nextVertex);
                }

                // Otherwise, see if vertex is an articulation point separating nextVertex.
                else
                {
                    if(nextVertex.low >= vertex.dfsNum)
                    {
                        if(!articulationPointList.contains(vertex))
                        {
                            articulationPointList.add(vertex);
                        }

                        retrieveBCCEdges(vertex, nextVertex);
                    }
                }
            }

            // otherwise, if its dfs level is lower than the current vertex's...
            // therefore, (vertex, nextVertex) is a back edge and should be examined.
            else if(nextVertex.dfsLevel < vertex.dfsLevel - 1)
            {
                vertex.low = Math.min(vertex.low, nextVertex.dfsNum);
                visited.push(new Edge(vertex, nextVertex));
            }
        }
    }

    /**
     * The overloaded form of doArticulationPointDFS(), allowing the user to start calling DFS at the start node.
     */
    public void doArticulationPointDFS()
    {
        Vertex vertex;

        if(this.graph.length == 0)
        {
            vertex = null;
        }

        else
        {
            vertex = this.graph[0];
        }

        doArticulationPointDFS(vertex);
    }

    /**
     * Retrieve all edges in a biconnected component.  All edges in a BCC canbe traced back whenever an articulation
     * point is found (because DFS is a recursive algorithm using a global stack).
     */
    public void retrieveBCCEdges(Vertex articulationPoint, Vertex child)
    {
        BiconnectedComponent bcc = new BiconnectedComponent(numBCC++);

        Edge top = (Edge)visited.peek();

        // Until the top of the stack is (articulationPoint,child), trace back and build a list of all edges in the
        // bcc.
        while(!top.equal(articulationPoint, child))
        {
            Edge edge = (Edge)visited.pop();
            bcc.edgeList.add(edge);
            top = (Edge)visited.peek();
        }

        Edge edge = (Edge)visited.pop();
        bcc.edgeList.add(edge);

        bccList.add(bcc);
    }


    /**
     * Show all the results of DFS.
     */
    public void showResults()
    {
        System.out.println("Number of vertices: " + numVertices);
        System.out.println("Number of edges: " + numEdges);
        System.out.println("Number of articulation points: " + articulationPointList.size());

        if(articulationPointList.size() > 0)
        {
            System.out.print("==> ");

            Iterator iter = articulationPointList.iterator();
            while(iter.hasNext())
            {
                Vertex vertex = (Vertex)iter.next();
                System.out.print(vertex.id + " ");
            }

            System.out.println();
        }

        System.out.println("Number of biconnected components: " + bccList.size());

        if(bccList.isEmpty())
        {
            System.out.println("The graph is biconnected since no articulation points exist.");
        }

        else
        {
            System.out.print("==> ");

            for(int i = 0; i < bccList.size(); i++)
            {
                BiconnectedComponent bcc = (BiconnectedComponent)bccList.get(i);

                System.out.print("\nComponent " + bcc.id + ": ");
                Iterator iter = bcc.edgeList.iterator();
                while(iter.hasNext())
                {
                    Edge edge = (Edge)iter.next();
                    System.out.print(edge + " ");
                }
            }

            System.out.println();
        }

        System.out.println();
    }
}