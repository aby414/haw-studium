package implementation;

import java.io.FileNotFoundException;

import org.jgrapht.graph.DefaultDirectedGraph;

import interfaces.GraphBuilder;

public class DirectedGraphBuilder extends GraphBuilder {
	
	/**
	 * Konstruktor
	 * @param filename
	 * @throws FileNotFoundException
	 */
	public DirectedGraphBuilder(String filename) throws FileNotFoundException {
		super(filename);
		g = new DefaultDirectedGraph<Vertex,LabeledEdge>(LabeledEdge.class);
	}


}
