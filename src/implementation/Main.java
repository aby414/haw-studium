package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.ext.DOTExporter;
import org.jgrapht.ext.EdgeNameProvider;
import org.jgrapht.ext.VertexNameProvider;

import algorithm.BreadthFirstSearch;
import algorithm.FloydWarshallAlgorithm;
import exporter.Exporter;

public class Main {
	private static final String PATHNAME = "src\\graph\\";
	private static final String FILEENDING = ".gka";

	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new
		// InputStreamReader(System.in));
		// System.out.println("Bitte geben Sie einen Graphennamen ein:");
		// String graphName = br.readLine();
//		String graphName = "graph03";
//		Graph graph = checkGraph(graphName);
//		Exporter.exportDOT(PATHNAME + graphName, graph);
//
//		 Vertex start = new Vertex("Bremen");
//		 Vertex end = new Vertex("Kiel");
		//FloydWarshallAlgorithm floy = new FloydWarshallAlgorithm(graph);
		//floy.findShortestPath(start, end);
		
		//bfs.findPath(start);

		// br.close();
//		 Process p = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Graphviz2.38\\bin\\gvedit.exe");
	}

	/**
	 * Die Methode bestimmt welcher Graph übergeben wurde und ruft den Builder des jeweiligen Graphen auf.
	 * @param graphName
	 * @return graph
	 * @throws IOException
	 */

	public static Graph<Vertex, LabeledEdge> checkGraph(String graphName) throws IOException {
		Graph<Vertex, LabeledEdge> graph = null;
		BufferedReader br = new BufferedReader(new FileReader(PATHNAME + graphName + FILEENDING));
		String line = br.readLine();
		if (line.contains("--") && line.contains(":")) {
			graph = new UndirectedWeightedGraphBuilder(PATHNAME + graphName + FILEENDING).buildGraph();
		} else if (line.contains("->") && line.contains(":")) {
			graph = new DirectedWeightedGraphBuilder(PATHNAME + graphName + FILEENDING).buildGraph();
		} else if (line.contains("--")) {
			graph = new UndirectedGraphBuilder(PATHNAME + graphName + FILEENDING).buildGraph();
		} else if (line.contains("->")) {
			graph = new DirectedGraphBuilder(PATHNAME + graphName + FILEENDING).buildGraph();
		}

		br.close();
		return graph;
	}

}
