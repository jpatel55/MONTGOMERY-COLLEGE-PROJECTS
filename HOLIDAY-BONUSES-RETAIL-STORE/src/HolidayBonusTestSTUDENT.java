// Created by Jemil Patel

package abc;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HolidayBonusTestSTUDENT {
	
	private double[][] dataSetSTUDENT1 = {{15, -25},
			                              {17, 14, -22, 25},
			                              {11, -24, 27},
			                              {10, 20}};
	
	private double[][] dataSetSTUDENT2 = {{38},
			                              {19, 41, 44, 25},
			                              {18, 33},
			                              {40, 4, 39}};
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Test calculateHolidayBonus method with a high of 5000, low of 1000 and 2000 as other
	 */
	@Test
	public void testCalculateHolidayBonusA() {
		try{
			double[] result = HolidayBonus.calculateHolidayBonus(dataSetSTUDENT1, 5000, 1000, 2000);
			assertEquals(2000.0, result[0], .001);
			assertEquals(12000.0, result[1], .001);
			assertEquals(7000.0, result[2], .001);
			assertEquals(6000.0, result[3], .001);
			
			result = HolidayBonus.calculateHolidayBonus(dataSetSTUDENT2, 5000, 1000, 2000);
			assertEquals(2000.0, result[0], .001);
			assertEquals(17000.0, result[1], .001);
			assertEquals(3000.0, result[2], .001);
			assertEquals(7000.0, result[3], .001);
			}
			catch (Exception e) {
				fail("This should not have caused an Exception");
			} 
	}
	
	
	/**
	 * Test calculateHolidayBonus method with a high of 1000, low of 250 and 500 as other
	 */
	@Test
	public void testCalculateHolidayBonusB() {
		try{
		double[] result = HolidayBonus.calculateHolidayBonus(dataSetSTUDENT1, 1000, 250, 500);
		assertEquals(500.0, result[0], .001);
		assertEquals(2500.0, result[1], .001);
		assertEquals(1500.0, result[2], .001);
		assertEquals(1250.0, result[3], .001);
		
		result = HolidayBonus.calculateHolidayBonus(dataSetSTUDENT2, 1000, 250, 500);
		assertEquals(500.0, result[0], .001);
		assertEquals(3500.0, result[1], .001);
		assertEquals(750.0, result[2], .001);
		assertEquals(1500.0, result[3], .001);
		}
		catch (Exception e) {
			fail("This should not have caused an Exception");
		} 
		
	}
	
	/**
	 * Test calculateTotalHolidayBonus method with a high of 5000, low of 1000 and 2000 as other
	 */
	@Test
	public void testCalculateTotalHolidayBonusA() {
		assertEquals(27000.0, HolidayBonus.calculateTotalHolidayBonus(dataSetSTUDENT1, 5000, 1000, 2000), .001);
		assertEquals(29000.0, HolidayBonus.calculateTotalHolidayBonus(dataSetSTUDENT2, 5000, 1000, 2000), .001);
	}
	
	/** 
	 * Test calculateTotalHolidayBonus method with a high of 1000, low of 250 and 500 as other
	 */
	@Test
	public void testCalculateTotalHolidayBonusB() {
		assertEquals(5750.0, HolidayBonus.calculateTotalHolidayBonus(dataSetSTUDENT1, 1000, 250, 500), .001);
		assertEquals(6250.0, HolidayBonus.calculateTotalHolidayBonus(dataSetSTUDENT2, 1000, 250, 500), .001);
	}

}
