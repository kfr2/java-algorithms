/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    2011-Nov-16
 * Time:    8:02 PM
 *
 * Maintains a collection of nodes (including related nodes).
 */

import java.util.ArrayList;

public class Node
{
    private int value;
    public Node parent;
    public ArrayList<Node> children;
    public String path;

    public Node(int value)
    {
        this.value    = value;
        this.parent   = null;
        this.children = new ArrayList<Node>();
        this.path     = "";
    }

    public int getValue()
    {
        return this.value;
    }
}
