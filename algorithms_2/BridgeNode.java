/**
 * Author:  Kevin Richardson <kevin@magically.us>
 * Date:    2011-Dec-7
 * Time:    9:02 AM
 *
 * Maintains a collection of nodes (including related nodes).
 */

import java.util.ArrayList;

public class BridgeNode
{
    private int value;
    public ArrayList<BridgeNode> connections;
    public int depth;
    public boolean visited;

    public BridgeNode(int value)
    {
        this.value       = value;
        this.connections = new ArrayList<BridgeNode>();
        this.depth       = 0;
        this.visited     = false;
    }

    public int getValue()
    {
        return this.value;
    }
}
