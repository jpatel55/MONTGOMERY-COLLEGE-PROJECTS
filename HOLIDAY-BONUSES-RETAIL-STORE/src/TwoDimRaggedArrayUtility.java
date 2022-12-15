/*
 * CMSC203 (CRN: 35630)
 * Project-5
 * Author: Jemil Patel
 * First submission due date: 04/15/22
 * Last submission due date: 04/29/22
 * Platform: Eclipse
 */

package abc;

import java.io.*;
import java.util.Scanner;

public class TwoDimRaggedArrayUtility {

	public TwoDimRaggedArrayUtility() {} // default constructor
	
	public static double[][] readFile(File file) throws FileNotFoundException {
		
		if(file.length() == 0) // if file is empty return null
			return null;
		
		Scanner inputFile = new Scanner(file); // create a scanner object to read the file
		
		String[][] tempArray = new String[10][10]; // temporary string array to store data read from the file
		String[] line; // string array to store individual characters of a line
		
		// initializing all elements of the tempArray to null
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				tempArray[i][j] = null;
			}
		}
		
		int count = 0; // to keep track of the count of rows
		
		while(inputFile.hasNextLine()) {			
			line = inputFile.nextLine().split(" "); // breaks each line into elements separated by a space and stores it into a string array
			for(int i = 0; i < line.length; i++) { // copies the elements of line array to tempArray row-wise
				tempArray[count][i] = line[i];
			}
			count++;			
		}
		
		inputFile.close(); // closing the scanner object
		
		int rows = 0; // to count the number of rows in tempArray
		
		for(int i = 0; i < 10; i++) {
			if(tempArray[i][0] != null) // if the first element of a row is not null, then increment rows
				rows++;
		}
		
		double[][] array = new double[rows][]; // create a two-dimensional double array with above calculated rows 
		
		int columns; // to keep track of the count of columns in tempArray
		
		for(int i = 0; i < rows; i++) {
			columns = 0;
			for(int j = 0; j < 10; j++) {
				if(tempArray[i][j] != null)
					columns++;
			}
			array[i] = new double[columns]; // initializing each row of double array
			for(int j = 0; j < columns; j++) { // copying elements of tempArray to double array by converting string elements to double
				array[i][j] = Double.parseDouble(tempArray[i][j]);
			}
		}
		
		return array;
		
	}
	
	public static void writeToFile(double[][] data, File outputFile) throws FileNotFoundException {
		
		PrintWriter pw = new PrintWriter(outputFile); // create a PrintWriter object using outputFile
		
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[i].length; j++) {
				if(j == data[i].length - 1) // if it is the last element of a row, then do not include a space after writing the number
					pw.print(data[i][j]);
				else // else, write the number followed by a space
					pw.print(data[i][j] + " ");
			}
			pw.println(); // move the cursor to the next line after writing all the elements of a row
		}
		
		pw.close(); // close the PrintWriter object
		
	}
	
	public static double getTotal(double[][] data) {
		
		double total = 0;
		
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[i].length; j++) {
				total += data[i][j]; // add each number of data array to total
			}
		}
		
		return total;
		
	}
	
	public static double getAverage(double[][] data) {
		
		double total = getTotal(data); // get the total of data array by calling the getTotal method
		int numberOfElements = 0;
		
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[i].length; j++) {
				numberOfElements++; // increment the numberOfElements after each iteration
			}
		}
		
		return total / numberOfElements;
		
	}
	
	public static double getRowTotal(double[][] data, int row) {
		
		double rowTotal = 0;
		
		for(int i = 0; i < data[row].length; i++) { // loop to run through the given row
			rowTotal += data[row][i]; // add each number of the row to rowTotal
		}
		
		return rowTotal;
		
	}
	
	public static double getColumnTotal(double[][] data, int col) {
		
		double colTotal = 0;
		
		for(int i = 0; i < data.length; i++) { // loop to run through the given column
			if(col <= data[i].length - 1) // to check if each row has an element at that given column
				colTotal += data[i][col]; // add the number at given column for each row to colTotal
		}
		
		return colTotal;
		
	}
	
	public static double getHighestInRow(double[][] data, int row) {
		return data[row][getHighestInRowIndex(data, row)];
	}
	
	public static int getHighestInRowIndex(double[][] data, int row) {
		
		int maxIndex = 0;
		
		for(int i = 0; i < data[row].length; i++) {
			if(data[row][i] > data[row][maxIndex]) // if a number in that row is greater than the number at maxIndex, then set maxIndex to i
				maxIndex = i;
		}
		
		return maxIndex;
		
	}
	
	public static double getLowestInRow(double[][] data, int row) {
		return data[row][getLowestInRowIndex(data, row)];
	}
	
	public static int getLowestInRowIndex(double[][] data, int row) {
		
		int minIndex = 0;
		
		for(int i = 0; i < data[row].length; i++) {
			if(data[row][i] < data[row][minIndex]) // if a number in that row is less than the number at minIndex, then set minIndex to i
				minIndex = i;
		}
		
		return minIndex;
		
	}
	
	public static double getHighestInColumn(double[][] data, int col) {
		return data[getHighestInColumnIndex(data, col)][col];
	}
	
	public static int getHighestInColumnIndex(double[][] data, int col) {
		
		int maxIndex = 0;
		boolean flag = true;
		
		for(int i = 0; i < data.length; i++) {
			if(col <= data[i].length - 1) {
				if(flag) { // to determine the maxIndex for the first time
					maxIndex = i;
					flag = false;
				}
				if(data[i][col] > data[maxIndex][col]) // if a number in that column is greater than the number at maxIndex, then set maxIndex to i
					maxIndex = i;
			}
		}
		
		return maxIndex;
		
	}
	
	public static double getLowestInColumn(double[][] data, int col) {
		return data[getLowestInColumnIndex(data, col)][col];
	}
	
	public static int getLowestInColumnIndex(double[][] data, int col) {
		
		int minIndex = 0;
		boolean flag = true;
		
		for(int i = 0; i < data.length; i++) {
			if(col <= data[i].length - 1) {
				if(flag) { // to determine the minIndex for the first time
					minIndex = i;
					flag = false;
				}
				if(data[i][col] < data[minIndex][col]) // if a number in that column is less than the number at minIndex, then set minIndex to i
					minIndex = i;
			}
		}
		
		return minIndex;
		
	}
	
	public static double getHighestInArray(double[][] data) {
		
		int maxRowIndex = 0;
		int maxColIndex = 0;
		
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[i].length; j++) {
				if(data[i][j] > data[maxRowIndex][maxColIndex]) { // if a number is greater than the number at maxRowIndex and maxColIndex, then set maxRowIndex to i and maxColIndex to j  
					maxRowIndex = i;
					maxColIndex = j;
				}	
			}
		}
		
		return data[maxRowIndex][maxColIndex];
		
	}
	
	public static double getLowestInArray(double[][] data) {
		
		int minRowIndex = 0;
		int minColIndex = 0;
		
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[i].length; j++) {
				if(data[i][j] < data[minRowIndex][minColIndex]) { // if a number is less than the number at minRowIndex and minColIndex, then set minRowIndex to i and minColIndex to j
					minRowIndex = i;
					minColIndex = j;
				}	
			}
		}
		
		return data[minRowIndex][minColIndex];
		
	}
	
}
