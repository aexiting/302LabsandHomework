/*  Lab 8: Dijkstra's Algorithm

    Name:

    Collaborators:

*/

// data structures
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

// file reading
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Exception;

class VertexAndWeight
{

	public int vertex;
	public int weight;

	public VertexAndWeight(int vertex, int edgeWeight) {
		this.vertex = vertex;
		this.weight = edgeWeight;
	}
}

class WeightedGraph
{

    ArrayList<ArrayList<VertexAndWeight>> adjlist;

    public WeightedGraph()
    {
        adjlist = new ArrayList<ArrayList<VertexAndWeight>>();
    }

    public void SetNumVertices(int n)
    {
        adjlist.ensureCapacity(n);
        for (int i = 0; i < n; ++i) {
            adjlist.add(new ArrayList<VertexAndWeight>());
        }
    }

	public int GetNumVertices() {
		return adjlist.size();
	}

    public void AddEdge(int u, int v, int w)
    {
        adjlist.get(u).add(new VertexAndWeight(v, w));
    }

	public ArrayList<VertexAndWeight> Neighbors(int vertex) {
		if (vertex < 0 || vertex > adjlist.size())
			throw new RuntimeException("Attempt to get neighbors of vertex, " + vertex + ", not in graph.");
		return adjlist.get(vertex);
	}

	public void Read(String filename)
	{
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;
			System.out.println("Reading file: " + filename);

			// First read the number of vertices in the graph
			if ((line = bufferedReader.readLine()) != null) {
				this.SetNumVertices(Integer.parseInt(line));
			}

			// Skip over the number of lines
			line = bufferedReader.readLine();

			// Then read each edge (one per line) until reaching the end of the file
			while ((line = bufferedReader.readLine()) != null) {
				String[] parts = line.split(",");
				int u = Integer.parseInt(parts[0]);
				int v = Integer.parseInt(parts[1]);
				int w = Integer.parseInt(parts[2]);
				this.AddEdge(u,v,w);
			}

		} catch(Exception name) {
			System.out.println("Exception (" + name.toString() + ") caught while reading file: " + filename);
		}
	}
}
