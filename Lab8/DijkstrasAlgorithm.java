import java.util.ArrayList;
import java.util.Iterator;
class DijkstrasAlgorithm
{
	WeightedGraph g;

	public DijkstrasAlgorithm(WeightedGraph g) {
		this.g = g;
	}

	void PrintDistances(int[] d) {
		for (int i = 0; i < d.length; ++i) {
			System.out.println(i + "," + d[i]);
		}
	}

	boolean HasReflexiveDistances(int[] d) {
		for (int i = 0; i < d.length; ++i) {
			if (d[i] != i) return false;
		}
		return true;
	}
	boolean Relax(VertexAndWeight v, int u,int[] d){
		if(d[v.vertex] > v.weight + d[u]){
			d[v.vertex] = v.weight + d[u];
			return true;
		}
		return false;
	}

	public void Run(int s, boolean verifyOnly) {

		HeapPriorityQueue pq = new HeapPriorityQueue(g.GetNumVertices());
		boolean[] inTree     = new boolean[g.GetNumVertices()];
		int[]     d          = new int[g.GetNumVertices()];

		// Step 1: initialize distances to infinity (in this case, Integer.MAX_VALUE)

		// TODO
		for(int i = 0; i < d.length; i++){
			d[i] = Integer.MAX_VALUE;
		}
		// Step 2: update distance to s to be 0
		d[s] = 0;
		inTree[s] = true;
		// TODO
		pq.DecreaseKey(s,d[s]);
		// Step 3: Repeatedly add vertex of smallest distance to shortest path tree, and
		// relax edges from v to vertices outside of shortest path tree
		while(pq.Size() != 0){

			int v = pq.ExtractMin();
			inTree[v] = true;
			ArrayList<VertexAndWeight> neighbors = g.Neighbors(v);
			for(VertexAndWeight w: neighbors){
				if(Relax(w,v,d)){
				pq.DecreaseKey(w.vertex,d[w.vertex]);
			}
			}
		}
		// TODO

		if (verifyOnly) {
			boolean reflexive = HasReflexiveDistances(d);
			System.out.println("Reflexive?: " + reflexive);
		} else {
			PrintDistances(d);
		}
	}
}
