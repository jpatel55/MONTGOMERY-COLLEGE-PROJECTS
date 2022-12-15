/**
 * @author Jemil Patel
 */

package abc;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TownGraphManager_STUDENT_Test {

	private TownGraphManagerInterface graph;
	
	@Before
	public void setUp() throws Exception {
		graph = new TownGraphManager();
		graph.addTown("A");
		graph.addTown("B");
		graph.addTown("C");
		graph.addTown("D");
		graph.addTown("E");
		graph.addTown("F");
		graph.addTown("G");
		
		graph.addRoad("A", "C", 3, "Road_1");
		graph.addRoad("A", "F", 2, "Road_2");
		graph.addRoad("C", "F", 2, "Road_3");
		graph.addRoad("C", "D", 4, "Road_4");
		graph.addRoad("C", "E", 1, "Road_5");
		graph.addRoad("F", "E", 3, "Road_6");
		graph.addRoad("F", "B", 6, "Road_7");
		graph.addRoad("F", "G", 5, "Road_8");
		graph.addRoad("B", "D", 1, "Road_9");
		graph.addRoad("B", "G", 2, "Road_10");
		graph.addRoad("B", "E", 2, "Road_11");
	}
	
	@After
	public void tearDown() throws Exception {
		graph = null;
	}
	
	@Test
	public void testAddRoad() {
		assertEquals(graph.addRoad("A", "B", 10, "Road_12"), true);
		assertEquals(graph.getRoad("A", "B"), "Road_12");
	}
	
	@Test
	public void testGetRoad() {
		assertEquals(graph.getRoad("C", "D"), "Road_4");
		assertEquals(graph.getRoad("B", "G"), "Road_10");
	}
	
	@Test
	public void testAddTown() {
		assertEquals(graph.addTown("H"), true);
		assertEquals(graph.getTown("H"), new Town("H"));
	}
	
	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals(roads.get(0), "Road_1");
		assertEquals(roads.get(1), "Road_10");
		assertEquals(roads.get(2), "Road_11");
		assertEquals(roads.get(3), "Road_2");
		assertEquals(roads.get(6), "Road_5");
	}
	
	@Test
	public void testAllTown() {
		ArrayList<String> towns = graph.allTowns();
		assertEquals(towns.get(0), "A");
		assertEquals(towns.get(3), "D");
		assertEquals(towns.get(6), "G");
		assertEquals(towns.get(1), "B");
		assertEquals(towns.get(4), "E");
	}
	
	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath("A", "B");
		assertEquals(path.get(0), "A via Road_1 to C 3 mi");
		assertEquals(path.get(1), "C via Road_5 to E 1 mi");
		assertEquals(path.get(2), "E via Road_11 to B 2 mi");
	}
	
}
