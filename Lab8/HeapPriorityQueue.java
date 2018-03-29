
class HeapPriorityQueue
{
	VertexAndWeight[] heapArray;
	int[] vertexLookup;

	int   heapSize;

	public HeapPriorityQueue(int numVertices) {
		heapArray    = new VertexAndWeight[numVertices];
		vertexLookup = new int[numVertices];

		for (int i = 0; i < heapArray.length; ++i) {
			heapArray[i]    = new VertexAndWeight(i, Integer.MAX_VALUE);
			vertexLookup[i] = i;
		}

		heapSize = heapArray.length;
	}

	public int GetIndexOfVertex(int vertex) {
		if (vertex < 0 || vertex >= heapArray.length)
			throw new RuntimeException("Attempt to get index of vertex, " + vertex + ", never in heap.");

		return vertexLookup[vertex];
	}

	public void DecreaseKey(int vertex, int newWeight) {
		int heapIndex = GetIndexOfVertex(vertex);
		heapArray[heapIndex].weight = newWeight;
		if (heapIndex < 0 || heapIndex >= heapSize)
			throw new RuntimeException("Attempt to decrease key of vertex, " + vertex + ", not in heap.");

		while (heapIndex != 0 && newWeight < heapArray[Parent(heapIndex)].weight) {
			Swap(heapIndex, Parent(heapIndex));
			heapIndex = Parent(heapIndex);
		}
	}

	int Left(int heapIndex) {
		return 2*heapIndex + 1;
	}

	int Right(int heapIndex) {
		return 2*heapIndex + 2;
	}

	int Parent(int heapIndex) {
		return (heapIndex - 1)/2;
	}

	public int Size() {
		return heapSize;
	}

	void MinHeapify(int heapIndex) {
		if (heapIndex < 0 || heapIndex >= heapSize) {
			throw new RuntimeException("Attempt to min heapify on index, " + heapIndex + ", outside of heap bounds.");
		}

		int smallest = heapIndex;
		if (Left(heapIndex) < heapSize && heapArray[smallest].weight > heapArray[Left(heapIndex)].weight) {
			smallest = Left(heapIndex);
		}
		if (Right(heapIndex) < heapSize && heapArray[smallest].weight > heapArray[Right(heapIndex)].weight) {
			smallest = Right(heapIndex);
		}

		if (smallest != heapIndex) {
			Swap(smallest, heapIndex);
			MinHeapify(smallest);
		}
	}

	void Swap(int heapIndex1, int heapIndex2) {
		VertexAndWeight temp = heapArray[heapIndex1];
		heapArray[heapIndex1] = heapArray[heapIndex2];
		heapArray[heapIndex2] = temp;

		vertexLookup[heapArray[heapIndex2].vertex] = heapIndex2;
		vertexLookup[heapArray[heapIndex1].vertex] = heapIndex1;
	}

	public int ExtractMin() {
		if (heapSize == 0) {
			throw new RuntimeException("Attempt to extract min on empty heap.");
		}

		Swap(0, heapSize-1);
		heapSize--;
		if (heapSize != 0)
			MinHeapify(0);
		return heapArray[heapSize].vertex;
	}

	public void PrintHeap() {
		for (int i = 0; i < heapSize; ++i) {
			System.out.print(heapArray[i].weight + " ");
		}
		System.out.println();
	}

}
