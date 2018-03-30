/*  Lab 8: Dijkstra's Algorithm

    Name:

    Collaborators:

*/

class Lab8 {


public static void main(String args[])
{
    String filename = "";
	boolean verifyOnly = false;
    if (args.length > 0) {
		for (int i = 0; i < args.length; ++i) {
			if (args[i].startsWith("--file=")) {
				filename = args[i].replace("--file=","");
			} else if (args[i].equals("--verify-reflexive")) {
				verifyOnly = true;
			} else {
				System.out.print("Unrecognized command: ");
				System.out.println(args[i]);
				System.out.println("Exiting...");
				return;
			}
		}
	}

	WeightedGraph g = new WeightedGraph();
	g.Read(filename);
	DijkstrasAlgorithm dijkstra = new DijkstrasAlgorithm(g);
	dijkstra.Run(0, verifyOnly);
} // main

} // Lab8
