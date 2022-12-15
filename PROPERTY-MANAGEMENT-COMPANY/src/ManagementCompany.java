/*
 * CMSC203 (CRN: 35630) Project-4
 * Author: Jemil Patel
 * Due date: 4/8/2022
 * Platform: Eclipse
 * Class to create a ManagementCompany object
 */

package abc;

public class ManagementCompany {
	
	// instance variables
	private final int MAX_PROPERTY = 5; // to store the number of maximum properties the company can hold
	private double mgmFeePer; // to store the rate of management fee charged by the company
	private String name; // to store the company name
	private Property[] properties = new Property[MAX_PROPERTY]; // array to store the maximum number of properties the company can hold
	private String taxID; // to store the tax ID
	private final int MGMT_WIDTH = 10; // to store the default width of the company's plot
	private final int MGMT_DEPTH = 10; // to store the default depth of the company's plot
	private Plot plot; // to store the company's plot
	
	public ManagementCompany() { // default constructor
		this.name = "";
		this.taxID = "";
		this.mgmFeePer = 0;
		for(int i = 0; i < this.properties.length; i++) {
			this.properties[i] = new Property();
		}
		this.plot = new Plot(0, 0, MGMT_WIDTH, MGMT_DEPTH);
	}
	
	public ManagementCompany(String name, String taxID, double mgmFee) { // parameterized constructor
		this.name = name;
		this.taxID = taxID;
		this.mgmFeePer = mgmFee;
		for(int i = 0; i < this.properties.length; i++) {
			this.properties[i] = new Property();
		}
		this.plot = new Plot(0, 0, MGMT_WIDTH, MGMT_DEPTH);
	}
	
	public ManagementCompany(String name, String taxID, double mgmFee, int x, int y, int width, int depth) { // parameterized constructor
		this.name = name;
		this.taxID = taxID;
		this.mgmFeePer = mgmFee;
		for(int i = 0; i < this.properties.length; i++) {
			this.properties[i] = new Property();
		}
		this.plot = new Plot(x, y, width, depth);
	}
	
	public ManagementCompany(ManagementCompany otherCompany) { // copy constructor
		this.name = otherCompany.name;
		this.taxID = otherCompany.taxID;
		this.mgmFeePer = otherCompany.mgmFeePer;
		for(int i = 0; i < this.properties.length; i++) {
			this.properties[i] = new Property(otherCompany.properties[i]);
		}
		this.plot = new Plot(otherCompany.plot);
	}

	public int getMAX_PROPERTY() { // getter for maximum number of properties the company can hold
		return this.MAX_PROPERTY;
	}
	
	public int addProperty(Property property) { // method to add a property to the company
		
		if(property != null) { // if the given property is not null

			if(!this.plot.encompasses(property.getPlot())) // if the company's plot does not contains the given property
				return -3;
			
			for(int i = 0; i < this.properties.length; i++) { // for loop to check if the given property overlaps with any of the company's property except default properties
				if(this.properties[i].getPlot().overlaps(property.getPlot()) && this.properties[i].getPropertyName() != "")
					return -4;
			}
			
			int numberOfProperties = 0;
			for(int i = 0; i < this.properties.length; i++) { // for loop to calculate the number of occupied properties by the company			
				if(this.properties[i].getPropertyName() != "")
					numberOfProperties++;
			}	
			
			if(numberOfProperties == MAX_PROPERTY) { // if the company holds the maximum number of properties
				return -1;
			}
			
			for(int i = 0; i < this.properties.length; i++) { // for loop to add the given property in the company's plot where there is an empty slot
				if(this.properties[i].getPropertyName() == "") {
					this.properties[i] = new Property(property);
					return i;
				}
			}
			
		}
		
		return -2; // -2 is returned if the given property is null
		
	}
	
	public int addProperty(String name, String city, double rent, String owner) { // overloaded addProperty method
		Property property = new Property(name, city, rent, owner); // creating a new property from the information provided
		return addProperty(property); // passing the newly created property to the overloaded method and returning its value
	}
	
	public int addProperty(String name, String city, double rent, String owner, int x, int y, int width, int depth) { // overloaded addProperty method	
		Property property = new Property(name, city, rent, owner, x, y, width, depth); // creating a new property from the information provided
		return addProperty(property); // passing the newly created property to the overloaded method and returning its value	
	}
	
	public double totalRent() { // method to calculate and return the total rent of all the company's properties 
		double rent = 0;
		for(int i = 0; i < this.properties.length; i++) {
			rent += this.properties[i].getRentAmount();
		}
		return rent;
	}
	
	public double maxRentProp() { // method to return the highest rent amount
		int maxRentIndex = maxRentPropertyIndex();
		return this.properties[maxRentIndex].getRentAmount();
	}
	
	private int maxRentPropertyIndex() { // private method to return the index of the property with the highest rent amount
		int maxRentIndex = 0;
		for(int i = 1; i < this.properties.length; i++) {
			if(this.properties[i].getRentAmount() > this.properties[maxRentIndex].getRentAmount())
				maxRentIndex = i;
		}
		return maxRentIndex;
	}
	
	private String displayPropertyAtIndex(int i) { // private method to display the details of a property at the given index
		return this.properties[i].toString();
	}
	
	public String maxRentPropDetails() { // method to display the details of the property with the highest rent amount
		return displayPropertyAtIndex(maxRentPropertyIndex());
	}
	
	public Plot getPlot() { // getter for company's plot
		return this.plot;
	}
	
	public String getName() { // getter for company name
		return this.name;
	}
	
	public String toString() { // returns the details of all the company's properties along with the total management fee
		
		String s = "List of all the properties for " + this.name + ", taxID: " + this.taxID + "\n" +
				   "______________________________________________________" + "\n";		
		
		for(int i = 0; i < this.properties.length; i++) {
			s += this.properties[i].toString() + "\n";
		}
		
		s += "______________________________________________________" + "\n\n" +
		     " total management Fee: " + (this.mgmFeePer / 100) * totalRent();
		
		return s;
		
	}
	
}
