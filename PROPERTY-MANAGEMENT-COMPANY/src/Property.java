/*
 * CMSC203 (CRN: 35630) Project-4
 * Author: Jemil Patel
 * Due date: 4/8/2022
 * Platform: Eclipse
 * Class to create a Property object
 */

package abc;

public class Property {

	// instance variables
	private String city; // to store the city name
	private String owner; // to store the owner name
	private String propertyName; // to store the property name
	private double rentAmount; // to store the rent of the property
	private Plot plot; // to store the property's plot
	
	public Property() { // default constructor
		this.city = "";
		this.owner = "";
		this.propertyName = "";
		this.rentAmount = 0;
		this.plot = new Plot();
	}
	
	public Property(Property p) { // copy constructor
		this.city = p.city;
		this.owner = p.owner;
		this.propertyName = p.propertyName;
		this.rentAmount = p.rentAmount;
		this.plot = new Plot(p.plot);
	}

	public Property(String propertyName, String city, double rentAmount, String owner) { // parameterized constructor
		this.propertyName = propertyName;
		this.city = city;
		this.rentAmount = rentAmount;
		this.owner = owner;
		this.plot = new Plot();
	}
	
    public Property(String propertyName, String city, double rentAmount, String owner, int x, int y, int width, int depth) { // parameterized constructor
    	this.propertyName = propertyName;
		this.city = city;
		this.rentAmount = rentAmount;
		this.owner = owner;
		this.plot = new Plot(x, y, width, depth);
    }

    public String getPropertyName() { // getter for property name
		return this.propertyName;
	}

	public void setPropertyName(String propertyName) { // setter for property name
		this.propertyName = propertyName;
	}
	
	public String getCity() { // getter for city name
		return this.city;
	}
	
	public Plot getPlot() { // getter for plot
		return this.plot;
	}
	
	public Plot setPlot(int x, int y, int width, int depth) { // setter for plot
		this.plot.setX(x);
		this.plot.setY(y);
		this.plot.setWidth(width);
		this.plot.setDepth(depth);
		return this.plot;
	}

	public void setCity(String city) { // setter for city name
		this.city = city;
	}
	
	public double getRentAmount() { // getter for rent
		return this.rentAmount;
	}

	public void setRentAmount(double rentAmount) { // setter for rent
		this.rentAmount = rentAmount;
	}

	public String getOwner() { // getter for owner name
		return this.owner;
	}

	public void setOwner(String owner) { // setter for owner name
		this.owner = owner;
	}
	
    public String toString() { // returns the details of the property
    	return "Property Name: " + this.propertyName + "\n" +
    		   " Located in " + this.city + "\n" +
    		   " Belonging to: " + this.owner + "\n" +
    		   " Rent Amount: " + this.rentAmount;
    }
	
}
