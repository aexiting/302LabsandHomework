import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class Graph
{
    
    ArrayList<ArrayList<Integer>> adjlist;

    public Graph()
    {
        adjlist = new ArrayList<ArrayList<Integer>>();
    }

    public void SetNumVertices(int n)
    {
        adjlist.ensureCapacity(n);
        for (int i = 0; i < n; ++i) {
            adjlist.add(new ArrayList<Integer>());
        }
    }

    public void AddEdge(int u, int v)
    {
        adjlist.get(u).add(v);
    }

    public boolean IsUnicyclic()
    {
        return false;
    }
}
