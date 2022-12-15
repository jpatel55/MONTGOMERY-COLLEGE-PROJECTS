/**
 * @author Jemil Patel
 */

package abc;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Town_STUDENT_Test {

	private Town town1;
	private Town town2;
	
	@Before
	public void setUp() throws Exception {
		town1 = new Town("Rockville");
		town2 = new Town("Germantown");
		town1.addAdjacentTown(town2);
	}
	
	@After
	public void tearDown() throws Exception {
		town1 = null;
		town2 = null;
	}
	
	@Test
	public void testGetName() {
		assertEquals("Rockville", town1.getName());
		assertEquals("Germantown", town2.getName());
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(town1.compareTo(town2), 1);
		assertEquals(town2.compareTo(town1), -1);
	}
	
	@Test
	public void testEquals() {
		assertEquals(town1.equals(town2), false);
		assertEquals(town1.equals(new Town("Rockville")), true);
		assertEquals(town2.equals(new Town("Germantown")), true);
	}
	
	@Test
	public void testToString() {
		assertEquals(town1.toString(), "Rockville");
		assertEquals(town2.toString(), "Germantown");
	}
	
	@Test
	public void testGetAdjacentTowns() {
		assertEquals(town1.getAdjacentTowns().toString(), "[Germantown]");
	}
	
}
