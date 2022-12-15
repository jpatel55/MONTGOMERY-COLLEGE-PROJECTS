/**
 * @author Jemil Patel
 */

package abc;

import java.util.ArrayList;

public class Town implements Comparable<Town> {
	
	private String name;
	private ArrayList<Town> adjacentTowns;
	
	public Town(String name) { // parameterized constructor
		this.name = name;
		adjacentTowns = new ArrayList<Town>();
	}
	
	public Town(Town templateTown) { // copy constructor
		this.name = templateTown.name;
		this.adjacentTowns = templateTown.adjacentTowns;
	}
	
	public String getName() {
		return name;
	}
	
	public int compareTo(Town o) {
		if (this.name.compareTo(o.name) < 0)
			return -1;
		else if (this.name.compareTo(o.name) == 0)
			return 0;
		else
			return 1;
	}
	
	public String toString() {
		return name;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean equals(Object obj) {
		Town o = (Town) obj;
		if (this.name.equals(o.name))
			return true;
		else
			return false;
	}
	
	public void addAdjacentTown(Town town) { // adds any town adjacent to the given town
		boolean flag = true;
		for (Town t : adjacentTowns) {
			if (t.equals(town))
				flag = false;
		}
		if (flag)
			adjacentTowns.add(town);
	}
	
	public void removeAdjacentTown(Town town) { // removes an existing adjacent town
		for (Town t : adjacentTowns) {
			if (t.equals(town))
				adjacentTowns.remove(t);
		}
	}
	
	public ArrayList<Town> getAdjacentTowns() { // returns an ArrayList of adjacent towns
		return adjacentTowns;
	}
	
}
