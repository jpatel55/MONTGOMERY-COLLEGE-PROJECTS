/*
 * CMSC203 (CRN: 35630) Project-4
 * Author: Jemil Patel
 * Due date: 4/8/2022
 * Platform: Eclipse
 * Class to create a Plot object 
 */

package abc;

public class Plot {

	// instance variables
	private int x; // to store the x coordinate of the upper left corner of the plot
	private int y; // to store the y coordinate of the upper left corner of the plot
	private int width; // to store the width of the plot
	private int depth; // to store the depth of the plot
	
	public Plot() { // default constructor
		this.x = 0;
		this.y = 0;
		this.width = 1;
		this.depth = 1;
	}
	
	public Plot(Plot p) { // copy constructor
		this.x = p.x;
		this.y = p.y;
		this.width = p.width;
		this.depth = p.depth;		
	}
	
	public Plot(int x, int y, int width, int depth) { // parameterized constructor
		this.x = x;
		this.y = y;
		this.width = width;
		this.depth = depth;
	}
	
	public boolean overlaps(Plot plot) { // method to check if the current plot overlaps the given plot
		return this.x < plot.x + plot.width && this.x + this.width > plot.x && this.y < plot.y + plot.depth && this.y + this.depth > plot.y;
	}
	
	public boolean encompasses(Plot plot) { // method to check if the current plot contains the given plot
		return plot.x >= this.x && plot.x + plot.width <= this.x + this.width && plot.y >= this.y && plot.y + plot.depth <= this.y + this.depth; 
	}

	public int getX() { // getter for x
		return this.x;
	}

	public void setX(int x) { // setter for x
		this.x = x;
	}

	public int getY() { // getter for y
		return this.y;
	}

	public void setY(int y) { // setter for y
		this.y = y;
	}

	public int getWidth() { // getter for width
		return this.width;
	}

	public void setWidth(int width) { // setter for width
		this.width = width;
	}

	public int getDepth() { // getter for depth
		return this.depth;
	}

	public void setDepth(int depth) { // setter for depth
		this.depth = depth;
	}
	
	public String toString() { // returns the details of the plot
		return "Upper left: (" + this.x + "," + this.y + "); Width: " + this.width + " Depth: " + this.depth;
	}
	
}
