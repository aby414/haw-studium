package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import implementation.DirectedGraphBuilder;
import implementation.DirectedWeightedGraphBuilder;
import implementation.LabeledEdge;
import implementation.UndirectedWeightedGraphBuilder;
import implementation.Vertex;

import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.Test;

public class GraphTest {

	/**
	 * Testet, ob die Methode buildGraph() richtig funktioniert und somit in Graph 6 die Zeile 16 "10 (l);" nicht als einen Konten erkennt.
	 * @throws FileNotFoundException
	 */
	@Test
	public void testGraph() throws FileNotFoundException {
		Graph testgraph = new DirectedGraphBuilder("src\\graph\\graph06.gka").buildGraph();
		Vertex v = new Vertex("10");
		Vertex v1 = new Vertex("1");
		assertFalse(testgraph.containsVertex(v));
		assertTrue(testgraph.containsVertex(v1));
	}
	
	/**
	 * Testet, ob die Methodes builGraph() die Kanten richtig erstellt. 
	 * Auch wird getestet, ob Kanten die den selben start und target haben, richtig erstellt werden.
	 * @throws FileNotFoundException
	 */
	@Test
	public void testEdge() throws FileNotFoundException{
		Graph testgraph = new DirectedGraphBuilder("src\\graph\\graph09.gka").buildGraph();
		Vertex s = new Vertex("b");
		Vertex t = new Vertex("j");
		Vertex s1 = new Vertex("c");
		Vertex t1 = new Vertex("g");
		assertFalse(testgraph.containsEdge(s, t));
		assertTrue(testgraph.containsEdge(s1, t1));
		assertTrue(testgraph.containsEdge(t1, t1));
	}
	
	/**
	 * Testet, ob die Methode buildGraph() die Labels der Kanten richtig erstellt.
	 * @throws FileNotFoundException
	 */
	@Test
	public void testLabel() throws FileNotFoundException{
		Graph testgraph = new DirectedGraphBuilder("src\\graph\\graph06.gka").buildGraph();
		Vertex s = new Vertex("1");
		Vertex t = new Vertex("7");
		LabeledEdge e = new LabeledEdge(s, t, "c");
		LabeledEdge ef = new LabeledEdge(s, t, "5");
		assertTrue(testgraph.containsEdge(e));
		assertFalse(testgraph.containsEdge(ef));
	}
	
	/**
	 * Testet, ob die Methode buildGraph() die Gewichte von Kanten richtig erstellt.
	 * @throws FileNotFoundException
	 */
	@Test
	public void testWeight() throws FileNotFoundException{
		Graph testgraph = new UndirectedWeightedGraphBuilder("src\\graph\\graph04.gka").buildGraph();
		Vertex s = new Vertex("q");
		Vertex t = new Vertex("v5");
		LabeledEdge e = new LabeledEdge(s, t, null, 6.0);
		LabeledEdge ef = new LabeledEdge(s, t, null, 1.0);
		assertTrue(testgraph.containsEdge(e));
		assertFalse(testgraph.containsEdge(ef));
	}

}
