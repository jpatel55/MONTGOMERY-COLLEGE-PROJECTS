/**
 * @author Jemil Patel
 */

package abc;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {

	private Town town1;
	private Town town2;
	private Road road;
	private Road anotherRoad;
	
	@Before
	public void setUp() throws Exception {
		town1 = new Town("Rockville");
		town2 = new Town("Germantown");
		road = new Road(town1, town2, 20, "Highway");
		anotherRoad = new Road(town2, town1, 10, "Expressway");
	}
	
	@After
	public void tearDown() throws Exception {
		town1 = null;
		town2 = null;
		road = null;
	}
	
	@Test
	public void testContains() {
		assertEquals(road.contains(town1), true);
		assertEquals(road.contains(town2), true);
		assertEquals(road.contains(new Town("Unknown")), false);
		assertEquals(anotherRoad.contains(town1), true);
		assertEquals(anotherRoad.contains(town2), true);
	}
	
	@Test
	public void testGetSource() {
		assertEquals(road.getSource(), town1);
		assertEquals(anotherRoad.getSource(), town2);
	}
	
	@Test
	public void testGetDestination() {
		assertEquals(road.getDestination(), town2);
		assertEquals(anotherRoad.getDestination(), town1);
	}
	
	@Test
	public void testGetWeight() {
		assertEquals(road.getWeight(), 20);
		assertEquals(anotherRoad.getWeight(), 10);
	}
	
	@Test
	public void testEquals() {
		assertEquals(road.equals(anotherRoad), true);
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(road.compareTo(anotherRoad), 1);
	}
	
}
