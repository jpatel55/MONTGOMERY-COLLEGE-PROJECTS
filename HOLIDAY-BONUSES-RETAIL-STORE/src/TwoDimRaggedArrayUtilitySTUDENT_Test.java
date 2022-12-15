// Created by Jemil Patel

package abc;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TwoDimRaggedArrayUtilitySTUDENT_Test {
	//STUDENT fill in dataSetSTUDENT with values, it must be a ragged array
	private double[][] dataSetSTUDENT = {{4.97, 25.71, -4.22, 35.65},
			                             {27.91, -30.92, 0.68, 9.05, 26.31},
			                             {-32.18, 27.87, 0.49},
			                             {5.37, -3.06, -49.26, 42.10},
			                             {-34.93, 15.98}};
	
	private File inputFile,outputFile;

	@Before
	public void setUp() throws Exception {
		outputFile = new File("TestOutStudent.txt");
	}

	@After
	public void tearDown() throws Exception {
		dataSetSTUDENT = null;
		inputFile = outputFile = null;
	}

	/**
	 * Student Test getTotal method
	 * Return the total of all the elements in the two dimensional array
	 */
	@Test
	public void testGetTotal() {
		assertEquals(67.52, TwoDimRaggedArrayUtility.getTotal(dataSetSTUDENT), .001);
	}

	/**
	 * Student Test getAverage method
	 * Return the average of all the elements in the two dimensional array
	 */
	@Test
	public void testGetAverage() {
		assertEquals(3.751, TwoDimRaggedArrayUtility.getAverage(dataSetSTUDENT), .001);	
	}

	/**
	 * Student Test getRowTotal method
	 * Return the total of all the elements of the row.
	 * Row 0 refers to the first row in the two dimensional array
	 */
	@Test
	public void testGetRowTotal() {
		assertEquals(62.11, TwoDimRaggedArrayUtility.getRowTotal(dataSetSTUDENT, 0), .001);
		assertEquals(33.03, TwoDimRaggedArrayUtility.getRowTotal(dataSetSTUDENT, 1), .001);
		assertEquals(-3.82, TwoDimRaggedArrayUtility.getRowTotal(dataSetSTUDENT, 2), .001);
		assertEquals(-4.85, TwoDimRaggedArrayUtility.getRowTotal(dataSetSTUDENT, 3), .001);
		assertEquals(-18.95, TwoDimRaggedArrayUtility.getRowTotal(dataSetSTUDENT, 4), .001);
	}


	/**
	 * Student Test getColumnTotal method
	 * Return the total of all the elements in the column. If a row in the two dimensional array
	 * doesn't have this column index, it is not an error, it doesn't participate in this method.
	 * Column 0 refers to the first column in the two dimensional array
	 */
	@Test
	public void testGetColumnTotal() {
		assertEquals(-28.86, TwoDimRaggedArrayUtility.getColumnTotal(dataSetSTUDENT, 0), .001);
		assertEquals(35.58, TwoDimRaggedArrayUtility.getColumnTotal(dataSetSTUDENT, 1), .001);
		assertEquals(-52.31, TwoDimRaggedArrayUtility.getColumnTotal(dataSetSTUDENT, 2), .001);
		assertEquals(86.8, TwoDimRaggedArrayUtility.getColumnTotal(dataSetSTUDENT, 3), .001);
		assertEquals(26.31, TwoDimRaggedArrayUtility.getColumnTotal(dataSetSTUDENT, 4), .001);
	}


	/**
	 * Student Test getHighestInArray method
	 * Return the largest of all the elements in the two dimensional array.
	 */
	@Test
	public void testGetHighestInArray() {
		assertEquals(42.10, TwoDimRaggedArrayUtility.getHighestInArray(dataSetSTUDENT), .001);
	}
	

	/**
	 * Test the writeToFile method
	 * write the array to the outputFile File
	 * then read it back to make sure formatted correctly to read
	 * 
	 */
	@Test
	public void testWriteToFile() throws FileNotFoundException {
		double[][] array=null;
		try {
			TwoDimRaggedArrayUtility.writeToFile(dataSetSTUDENT, outputFile);
		} catch (Exception e) {
			fail("This should not have caused an Exception");
		}
		array = TwoDimRaggedArrayUtility.readFile(outputFile);
		assertEquals(4.97, array[0][0], .001);
		assertEquals(25.71, array[0][1], .001);
		assertEquals(-4.22, array[0][2], .001);
		assertEquals(35.65, array[0][3], .001);
		assertEquals(27.91, array[1][0], .001);
		assertEquals(-30.92, array[1][1], .001);
		assertEquals(0.68, array[1][2], .001);
		assertEquals(9.05, array[1][3], .001);
		assertEquals(26.31, array[1][4], .001);
		assertEquals(-32.18, array[2][0], .001);
		assertEquals(27.87, array[2][1], .001);
		assertEquals(0.49, array[2][2], .001);
		assertEquals(5.37, array[3][0], .001);
		assertEquals(-3.06, array[3][1], .001);
		assertEquals(-49.26, array[3][2], .001);
		assertEquals(42.10, array[3][3], .001);
		assertEquals(-34.93, array[4][0], .001);
		assertEquals(15.98, array[4][1], .001);
	}

}
