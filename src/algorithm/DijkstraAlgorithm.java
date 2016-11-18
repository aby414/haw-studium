package algorithm;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;

import implementation.LabeledEdge;
import implementation.Vertex;

public class DijkstraAlgorithm {

	private Graph<Vertex, LabeledEdge> g;
	private Set<Vertex> allVertices;
	private List<Vertex> path = new LinkedList<Vertex>();
	private double lowestDistance = 0.0;
	
	public DijkstraAlgorithm(Graph<Vertex, LabeledEdge> g) {
		this.g = g;
		this.allVertices = g.vertexSet();
	}

	public void findShortestPath(Vertex startVertex, Vertex endVertex) throws Exception {
		// Vorbedingung
		if (!this.g.containsVertex(startVertex) || !(this.g.containsVertex(endVertex))) {
			throw new Exception("Bitte geben Sie g�tige Werte an!");
		}
		this.path.clear();
		this.lowestDistance = 0.0;

		double infinity = Double.POSITIVE_INFINITY;
		List<Vertex> neighbours = null;
		Vertex currentVertex = startVertex;
		Map<Vertex, Double> distance = new HashMap<Vertex, Double>();
		Map<Vertex, Boolean> ok = new HashMap<Vertex, Boolean>();
		Map<Vertex, Vertex> predecessor = new HashMap<Vertex, Vertex>();

		for (Vertex v : this.allVertices) {
			ok.put(v, false);
		}
		for (Vertex v : this.allVertices) {
			predecessor.put(v, null);
		}
		for (Vertex v : this.allVertices) {
			distance.put(v, infinity);
		}

		distance.put(currentVertex, 0.0);
		predecessor.put(currentVertex, currentVertex);

		while (ok.containsValue(false)) {
			Vertex nextVertex = findLowestDistance(distance, ok);
			ok.put(nextVertex, true);
			neighbours = getNeighbours(nextVertex);

			for (Vertex v : neighbours) {
				if (ok.get(v) == false) {
					if (distance.get(v) > (distance.get(nextVertex) + this.g.getEdge(nextVertex, v).getEdgeWeight())) {
						distance.put(v, (distance.get(nextVertex) + this.g.getEdge(nextVertex, v).getEdgeWeight()));
						predecessor.put(v, nextVertex);
					}
				}
			}
		}

		if (predecessor.get(endVertex) != null) {
			for (Vertex v = endVertex; v != startVertex; v = predecessor.get(v)) {
				this.path.add(v);
			}

		}
		Collections.reverse(this.path);
		this.lowestDistance = distance.get(endVertex);
	
	}

	public List<Vertex> getNeighbours(Vertex v) {
		List<Vertex> neighbours = null;
		if (this.g instanceof DirectedGraph) {
			neighbours = Graphs.successorListOf((DirectedGraph<Vertex, LabeledEdge>) this.g, v);
		} else {
			neighbours = Graphs.neighborListOf(this.g, v);
		}
		return neighbours;
	}

	public Vertex findLowestDistance(Map<Vertex, Double> distance, Map<Vertex, Boolean> ok) {
		Entry<Vertex, Double> min = null;
		for (Entry<Vertex, Double> entry : distance.entrySet()) {
			if (min == null || min.getValue() > entry.getValue()) {
				if (ok.get(entry.getKey()) == false) {
					min = entry;
				}
			}
		}
		return min.getKey();
	}

	public List<Vertex> getPath() {
		return this.path;
	}

	public double getLowestDistance() {
		return lowestDistance;
	}
	
	
}
