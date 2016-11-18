package exporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.ext.DOTExporter;
import org.jgrapht.ext.EdgeNameProvider;
import org.jgrapht.ext.VertexNameProvider;

import implementation.LabeledEdge;
import implementation.Vertex;

public class Exporter {
	/**
	 * Speichert einen Graphen in einer DOT-Datei. Der Methode wird der Speicherort und der Graph übergeben.
	 * @param path
	 * @param graph
	 * @throws IOException
	 */
	public static <V, E> void exportDOT(String path, final Graph<Vertex, LabeledEdge> graph) throws IOException {
		DOTExporter<Vertex, LabeledEdge> export = new DOTExporter<Vertex, LabeledEdge>(
				new VertexNameProvider<Vertex>() {
					@Override
					public String getVertexName(Vertex vertex) {
						String name = vertex.changeChar();
						return name;
					}
				}, new VertexNameProvider<Vertex>() {
					@Override
					public String getVertexName(Vertex vertex) {
						String name = vertex.changeChar();
						return name;
					}
				}, new EdgeNameProvider<LabeledEdge>() {
					@Override
					public String getEdgeName(LabeledEdge edge) {
						if (graph instanceof WeightedGraph) {
							double weight = edge.getEdgeWeight();
							return String.valueOf(weight);
						}else if(graph instanceof DirectedGraph || graph instanceof UndirectedGraph){
							String label = edge.getLabel();
							return label;
						}
						return "";
					}
				}
		);
		export.export(new BufferedWriter(new FileWriter(path + ".dot")), graph);

	}
	
	/**
	 * Speichert einen Graphen in einer GramphML-Datei. Der Methode wird der Speicherort und der Graph übergeben.
	 * @param path
	 * @param graph
	 * @throws IOException
	 */
	public static <V, E> void exportGraphML(String path, final Graph<Vertex, LabeledEdge> graph) throws IOException {
		DOTExporter<Vertex, LabeledEdge> export = new DOTExporter<Vertex, LabeledEdge>(
				new VertexNameProvider<Vertex>() {
					@Override
					public String getVertexName(Vertex vertex) {
						String name = vertex.changeChar();
						return name;
					}
				}, new VertexNameProvider<Vertex>() {
					@Override
					public String getVertexName(Vertex vertex) {
						String name = vertex.changeChar();
						return name;
					}
				}, new EdgeNameProvider<LabeledEdge>() {
					@Override
					public String getEdgeName(LabeledEdge edge) {
						if (graph instanceof WeightedGraph) {
							double weight = edge.getEdgeWeight();
							return String.valueOf(weight);
						}else if(graph instanceof DirectedGraph || graph instanceof UndirectedGraph){
							String label = edge.getLabel();
							return label;
						}
						return "";
					}
				}
		);
		export.export(new BufferedWriter(new FileWriter(path + ".graphml")), graph);

	}
}
