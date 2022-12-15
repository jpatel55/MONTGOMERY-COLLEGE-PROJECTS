/*
 * CMSC203 (CRN: 35630)
 * Project-5
 * Author: Jemil Patel
 * First submission due date: 04/15/22
 * Last submission due date: 04/29/22
 * Platform: Eclipse
 */

package abc;

public class HolidayBonus {
	
	public HolidayBonus() {} // default constructor
	
	public static double[] calculateHolidayBonus(double[][] data, double high, double low, double other) {
		
		double[] bonus = new double[data.length]; // number of elements in the bonus array are equal to the number of rows in the data array
		
		int count = 0;
		
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[i].length; j++) {
				if(data[i][j] == TwoDimRaggedArrayUtility.getHighestInColumn(data, j) && data[i][j] > 0) // if the sales is highest in that category and positive, then add the amount for high to the bonus 
					bonus[count] += high;
				else if(data[i][j] == TwoDimRaggedArrayUtility.getLowestInColumn(data, j) && data[i][j] > 0) // if the sales is lowest in that category and positive, then add the amount for low to the bonus
					bonus[count] += low;
				else if(data[i][j] > 0) // if the sales is neither highest nor lowest in that category and positive, then add the amount for other to the bonus
					bonus[count] += other;
			}
			count++; // increment count to proceed to the bonus calculation for next row
		}
		
		return bonus;
		
	}
	
	public static double calculateTotalHolidayBonus(double[][] data, double high, double low, double other) {
		
		double[] bonus = calculateHolidayBonus(data, high, low, other); // get the bonus array from calling the calculateHolidayBonus method
		
		double totalBonus = 0;
		
		// go through the entire bonus array and add each bonus to totalBonus
		for(int i = 0; i < bonus.length; i++) {
			totalBonus += bonus[i]; 
		}
		
		return totalBonus;
		
	}

}
