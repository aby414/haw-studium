package implementation;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import interfaces.GraphBuilder;

public class DirectedWeightedGraphBuilder extends GraphBuilder {
	private DirectedWeightedPseudograph<Vertex, LabeledEdge> g;
	
	/**
	 * Konstruktor
	 * @param filename
	 * @throws FileNotFoundException
	 */
	public DirectedWeightedGraphBuilder(String filename) throws FileNotFoundException {
		super(filename);
		g = new DirectedWeightedPseudograph<Vertex, LabeledEdge>(LabeledEdge.class);
	}

	/**
	 * Einlesen der Datei und das Konstruieren des Graphen.
	 * return g
	 */
	@Override
	public Graph<Vertex, LabeledEdge> buildGraph() {
		String currentLine;

		try {
			while ((currentLine = this.bReader.readLine()) != null) {
				if (!currentLine.equals("")) {
					currentLine = currentLine.replaceAll(" ", "");
					currentLine = currentLine.replaceAll(";", "");
					String[] vertices = currentLine.split("(->)|(:)");

					if (vertices.length == 2) {
						Vertex s = new Vertex(vertices[0]);
						Vertex t = new Vertex(vertices[1]);

						g.addVertex(s);
						g.addVertex(t);
						g.addEdge(s, t, new LabeledEdge(s, t, null, 0.0));
						
					} else if (vertices.length == 1) {
						Vertex s = new Vertex(vertices[0]);
						g.addVertex(s);
					} else {
						Vertex s = new Vertex(vertices[0]);
						Vertex t = new Vertex(vertices[1]);

						g.addVertex(s);
						g.addVertex(t);
						g.addEdge(s, t, new LabeledEdge(s, t, null, Double.parseDouble(vertices[2])));
					}
				}
			}
			this.bReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return g;
	}

}
