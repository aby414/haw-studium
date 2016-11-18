package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import implementation.DirectedGraphBuilder;
import implementation.DirectedWeightedGraphBuilder;
import implementation.UndirectedGraphBuilder;
import implementation.UndirectedWeightedGraphBuilder;
import implementation.Vertex;

import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.Test;

import algorithm.BreadthFirstSearch;

public class BFSTest {

	/**
	 * Testet die Methode getWayLength() und findPath() an dem Graphen 01.
	 * @throws Exception
	 */
	@Test
	public void testGetShortestPathLengthAndFindPath() throws Exception {
		Graph testgraph = new DirectedGraphBuilder("src\\graph\\graph01.gka").buildGraph();
		BreadthFirstSearch bfs = new BreadthFirstSearch(testgraph);
		Vertex start = new Vertex("a");
		Vertex end = new Vertex("k");
		Vertex end2 = new Vertex("g");
		Vertex end3 = new Vertex("h");
		Vertex i = new Vertex("i");
		bfs.findShortestPath(start, end);
		assertEquals(1, bfs.getWayLength());
		bfs.findShortestPath(start, end2);
		assertEquals(2, bfs.getWayLength());
		bfs.findShortestPath(start, end3);
		assertEquals(3, bfs.getWayLength());
		assertFalse(bfs.findPath(start));
		assertFalse(bfs.findPath(end));
		assertFalse(bfs.findPath(end3));
		assertTrue(bfs.findPath(i));
		
	}
	
	/**
	 * Testet die Methode getWayLength() an dem Graphen 02.
	 * @throws Exception
	 */
	@Test
	public void testGetShortestPathLengthGraph2() throws Exception{
		Graph testgraph2 = new UndirectedGraphBuilder("src\\graph\\graph02.gka").buildGraph();
		BreadthFirstSearch bfs2 = new BreadthFirstSearch(testgraph2);
		Vertex start = new Vertex("a");
		Vertex end = new Vertex("k");
		bfs2.findShortestPath(start, end);
		assertEquals(2, bfs2.getWayLength());
		Vertex start1 = new Vertex("i");
		Vertex end1 = new Vertex("e");
		bfs2.findShortestPath(start1, end1);
		assertEquals(2, bfs2.getWayLength());
	}
	
	/**
	 * Testet die Methode getWayLength() an dem Graphen 03.
	 * @throws Exception
	 */
	@Test
	public void testGetShortestPathLengthGraph3() throws Exception{
		Graph testgraph2 = new UndirectedWeightedGraphBuilder("src\\graph\\graph03.gka").buildGraph();
		BreadthFirstSearch bfs2 = new BreadthFirstSearch(testgraph2);
		Vertex start = new Vertex("Rotenburg");
		Vertex end = new Vertex("Bremen");
		bfs2.findShortestPath(start, end);
		assertEquals(2, bfs2.getWayLength());
		Vertex start1 = new Vertex("Kiel");
		Vertex end1 = new Vertex("Bremerhaven");
		bfs2.findShortestPath(start1, end1);
		assertEquals(3, bfs2.getWayLength());
	}
	
	/**
	 * Testet die Methode getWayLength() an dem Graphen 04.
	 * @throws Exception
	 */
	@Test
	public void testGetShortestPathLengthGraph4() throws Exception{
		Graph testgraph = new DirectedGraphBuilder("src\\graph\\graph04.gka").buildGraph();
		BreadthFirstSearch bfs = new BreadthFirstSearch(testgraph);
		Vertex start = new Vertex("q");
		Vertex end = new Vertex("v2");
		Vertex end1 = new Vertex("s");
		bfs.findShortestPath(start, end);
		assertEquals(2, bfs.getWayLength());
		bfs.findShortestPath(start, end1);
		assertEquals(1, bfs.getWayLength());
	}

	/**
	 * Testet die Methode getWayLength() an dem Graphen 06.
	 * @throws Exception
	 */
	@Test
	public void testGetShortestPathLengthGraph6() throws Exception{
		Graph testgraph = new DirectedGraphBuilder("src\\graph\\graph06.gka").buildGraph();
		BreadthFirstSearch bfs = new BreadthFirstSearch(testgraph);
		Vertex start = new Vertex("1");
		Vertex end = new Vertex("4");
		Vertex end1 = new Vertex("6");
		bfs.findShortestPath(start, end);
		assertEquals(2, bfs.getWayLength());
		bfs.findShortestPath(start, end1);
		assertEquals(3, bfs.getWayLength());
	}

	/**
	 * Testet die Methode getWayLength() an dem Graphen 09.
	 * @throws Exception
	 */
	@Test
	public void testGetShortestPathLengthGraph9() throws Exception{
		Graph testgraph = new DirectedGraphBuilder("src\\graph\\graph09.gka").buildGraph();
		BreadthFirstSearch bfs = new BreadthFirstSearch(testgraph);
		Vertex start = new Vertex("h");
		Vertex end = new Vertex("d");
		Vertex end1 = new Vertex("f");
		bfs.findShortestPath(start, end);
		assertEquals(3, bfs.getWayLength());
		bfs.findShortestPath(start, end1);
		assertEquals(1, bfs.getWayLength());
	}

	/**
	 * Testet die Methode getWayLength() an dem Graphen 10.
	 * @throws Exception
	 */
	@Test
	public void testGetShortestPathLengthGraph10() throws Exception{
		Graph testgraph = new DirectedGraphBuilder("src\\graph\\graph10.gka").buildGraph();
		BreadthFirstSearch bfs = new BreadthFirstSearch(testgraph);
		Vertex start = new Vertex("q");
		Vertex end = new Vertex("v3");
		Vertex end1 = new Vertex("v2");
		bfs.findShortestPath(start, end);
		assertEquals(2, bfs.getWayLength());
		bfs.findShortestPath(start, end1);
		assertEquals(2, bfs.getWayLength());
	}

}
