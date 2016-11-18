package algorithm;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;

import implementation.LabeledEdge;
import implementation.Vertex;

public class BreadthFirstSearch {
	
	private Graph<Vertex, LabeledEdge> g;
	private List<Vertex> path = new LinkedList<Vertex>();
	
	/**
	 * Konstruktor
	 * @param g
	 */
	public BreadthFirstSearch(Graph<Vertex, LabeledEdge> g) {
		this.g = g;
	}
	
	/**
	 * Findet den k�rzesten Pfad von einem Startknoten zu einem Endknoten.
	 * @param startVertex
	 * @param endVertex
	 * @throws Exception
	 */
	public void findShortestPath(Vertex startVertex, Vertex endVertex) throws Exception {
		//Vorbedingung
		if(!this.g.containsVertex(startVertex) || !(this.g.containsVertex(endVertex))){
			throw new Exception("Bitte geben Sie g�tige Werte an!");
		}
		path.clear();
		Queue<Vertex> queue = new LinkedList<>();
		List<Vertex> visited = new LinkedList<>();
		Map<Vertex, Vertex> prev = new HashMap<Vertex, Vertex>();
		Vertex currentVertex = startVertex;
		List<Vertex> neighborList;

		queue.add(currentVertex);

		while (!queue.isEmpty()) {
			currentVertex = queue.poll();
			visited.add(currentVertex);
			if (currentVertex.equals(endVertex)) {
				break;
			} else {
				if (this.g instanceof DirectedGraph) {  //gerichteter Graph
					neighborList = Graphs.successorListOf((DirectedGraph<Vertex, LabeledEdge>) g, currentVertex);
				} else {						       //ungerichteter Graph
					neighborList = Graphs.neighborListOf(g, currentVertex);
				}
				for (Vertex v : neighborList) {
					if (!visited.contains(v)) {
						visited.add(v);
						queue.add(v);
						prev.put(v, currentVertex);
					}
				}
			}
		}

		if (!currentVertex.equals(endVertex)) {
			throw new Exception("Der Knoten " + endVertex.getName() + " kann nicht erreicht werden.");
		}
		for (Vertex v = endVertex; v != null; v = prev.get(v)) {
			this.path.add(v);
		}
		Collections.reverse(this.path);
	}

	/**
	 * �berpr�ft ob alle Knoten von einem Startknoten erreichbar sind.
	 * @param startVertex
	 * @return
	 * @throws Exception
	 */
	public boolean findPath(Vertex startVertex) throws Exception{
		//Vorbedingung
		if(!this.g.containsVertex(startVertex)){
			throw new Exception("Bitte geben Sie g�tige Werte an!");
		}
		Queue<Vertex> queue = new LinkedList<>();
		List<Vertex> visited = new LinkedList<>();
		Set<Vertex> allVertices = this.g.vertexSet();
		Vertex currentVertex = startVertex;
		List<Vertex> neighborList;

		queue.add(currentVertex);

		while (!queue.isEmpty()) {
			currentVertex = queue.poll();
			if (this.g instanceof DirectedGraph) {
				neighborList = Graphs.successorListOf((DirectedGraph<Vertex, LabeledEdge>) g, currentVertex);
			} else {
				neighborList = Graphs.neighborListOf(g, currentVertex);
			}
			for (Vertex v : neighborList) {
				if (!visited.contains(v)) {
					visited.add(v);
					queue.add(v);
				}
			}
		}

		if (!(allVertices.size() == visited.size())) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Gibt die Pfadl�nge zur�ck.
	 * @return length
	 */
	public int getWayLength(){
		return (this.path.size()-1);
	}
	
	/**
	 * Gibt den Pfad zur�ck.
	 * @return path
	 */
	public List<Vertex> getPath(){
		return this.path;
	}
}