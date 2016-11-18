package implementation;

import java.io.FileNotFoundException;

import org.jgrapht.graph.Pseudograph;

import interfaces.GraphBuilder;

public class UndirectedGraphBuilder extends GraphBuilder {
	
	/**
	 * Konstruktor
	 * @param filename
	 * @throws FileNotFoundException
	 */
	public UndirectedGraphBuilder(String filename) throws FileNotFoundException {
		super(filename);
		g = new Pseudograph<Vertex, LabeledEdge>(LabeledEdge.class);
	}


}
