package abc;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ManagementCompanyTestSTUDENT {
	
	Property p1, p2, p3, p4, p5, p6;
	ManagementCompany m;
	
	@Before
	public void setUp() throws Exception {
		p1 = new Property("Blackfield", "Rockville", 2700.0, "Shawn Spence", 1, 0, 2, 2);
		p2 = new Property("Lake View", "Germantown", 3500.0, "Margie Foster", 4, 1, 3, 4);
		p3 = new Property("Green Lodge", "Takoma Park", 2950.0, "Robert Mills", 2, 3, 2, 3);
		
		//student create a management company
		m = new ManagementCompany();
		
		//student add three properties, with plots, to mgmt co
		m.addProperty(p1);
		m.addProperty(p2);
		m.addProperty(p3);
	}

	@After
	public void tearDown() {
		//student set mgmt co to null  
		m = null;
	}

	@Test
	public void testAddPropertyDefaultPlot() {
		p4 = new Property("Ivylands", "Laurel", 1200.0, "Nicol Harrison");
		p5 = new Property("Breezy End", "Baltimore", 3150.0, "June Reed", 6, 6, 4, 3);
		p6 = new Property("Fir House", "Bowie", 3150.0, "Jennifer Barnhill", 2, 7, 4, 3);
		
		//student should add property with 4 args & default plot (0,0,1,1)
		assertEquals(m.addProperty(p4), 3, 0);
		//student should add property with 8 args
		assertEquals(m.addProperty(p5), 4, 0);
		//student should add property that exceeds the size of the mgmt co array and can not be added, add property should return -1	
		assertEquals(m.addProperty(p6), -1, 0);
	}
 
	@Test
	public void testMaxRentProp() {
		//student should test if maxRentProp contains the maximum rent of properties
		assertEquals(m.maxRentProp(), 3500.0, 0);
	}

	@Test
	public void testTotalRent() {
		//student should test if totalRent returns the total rent of properties
		assertEquals(m.totalRent(), 9150.0, 0);
	}

 }
