/*  Lab 7: Recognizing unicyclic graphs

    Name:

    Collaborators:

*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Exception;

class Lab7 {

public static void ReadGraph(String filename, Graph graph)
{
    try {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        System.out.println("Reading file: " + filename);

        // First read the number of vertices in the graph
        if ((line = bufferedReader.readLine()) != null) {
            graph.SetNumVertices(Integer.parseInt(line));
        }

        // Skip over the number of lines
        line = bufferedReader.readLine();

        // Then read each edge (one per line) until reaching the end of the file
        while ((line = bufferedReader.readLine()) != null) {
            String[] parts = line.split(",");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            graph.AddEdge(u,v);
        }

    } catch(Exception name) {
        System.out.println("Exception (" + name.toString() + ") caught while reading file: " + filename);
    }
}

public static void main(String args[])
{
    String filename = "";
    if (args.length > 0) {
        if (args[0].startsWith("--file=")) {
            filename = args[0].replace("--file=","");
        } else {
            System.out.print("Unrecognized command: ");
            System.out.println(args[0]);
            System.out.println("Exiting...");
            return;
        }
    }

    Graph g = new Graph();
    ReadGraph(filename, g);

    System.out.println("G is unicyclic: " + g.IsUnicyclic());
} // main

} // Lab8
