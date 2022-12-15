/**
 * @author Jemil Patel
 */

package abc;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Graph_STUDENT_Test {

	private GraphInterface<Town, Road> graph;
	private Town[] town;
	
	@Before
	public void setUp() throws Exception {
		graph = new Graph();
		town = new Town[3];
		
		for (int i = 0; i < 3; i++) {
			town[i] = new Town("Town_" + i);
			graph.addVertex(town[i]);
		}
		
		graph.addEdge(town[0], town[2], 5, "Road_1");
	}
	
	@After
	public void tearDown() throws Exception {
		graph = null;
	}
	
	@Test
	public void testGetEdge() {
		assertEquals(graph.getEdge(town[0], town[2]), new Road(town[0], town[2], 5, "Road_1"));
		assertEquals(graph.getEdge(town[0], town[1]), null);
	}
	
	@Test
	public void testContainsEdge() {
		assertEquals(graph.containsEdge(town[0], town[2]), true);
		assertEquals(graph.containsEdge(town[0], town[1]), false);
	}
	
	@Test
	public void testContainsVertex() {
		assertEquals(graph.containsVertex(town[0]), true);
		assertEquals(graph.containsVertex(town[1]), true);
		assertEquals(graph.containsVertex(town[2]), true);
		assertEquals(graph.containsVertex(new Town("Unknown")), false);
	}
	
	@Test
	public void testShortestPath() {
		assertEquals(graph.shortestPath(town[0], town[2]).toString(), "[Town_0 via Road_1 to Town_2 5 mi]");
	}
	
}
