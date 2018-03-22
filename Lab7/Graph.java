import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;
class Graph
{
  LinkedList<Integer> Q = new LinkedList<>();
  ArrayList<ArrayList<Integer>> adjlist;
  String[] nodeColors;
  boolean[] visited;
  public Graph()
  {
    adjlist = new ArrayList<ArrayList<Integer>>();
  }

  public void SetNumVertices(int n)
  {
    adjlist.ensureCapacity(n);
    nodeColors = new String[n];
    visited = new boolean[n];
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
    int count = 0;
    for(int i = 0; i< adjlist.size(); i++){
      if(visited[i] == false){
        for(int z = 0; z < adjlist.size();z++){
          nodeColors[z] = "white";
        }
        if(FindCycle(i))
        count++;
      }
    }
    return count == 1;
  }
  private boolean FindCycle(int s){
    nodeColors[s] = "grey";
    Q.add(s);
    while(!Q.isEmpty()){
      int u = Q.removeFirst();
      Iterator<Integer> itr = adjlist.get(u).iterator();
      while(itr.hasNext()){
        int v = itr.next();
        if(nodeColors[v].equals("white")){
          nodeColors[v] = "grey";
          visited[v] = true;
          Q.add(v);
        }
        else if(nodeColors[v].equals("grey")){
          adjlist.get(u).remove(new Integer(v));
          adjlist.get(v).remove(new Integer(u));
          Q.clear();
          return true;
        }
      }
      nodeColors[u] = "Black";
    }

    return false;
  }
}
