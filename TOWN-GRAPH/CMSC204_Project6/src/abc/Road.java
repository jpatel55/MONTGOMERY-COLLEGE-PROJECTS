/**
 * @author Jemil Patel
 */

package abc;

public class Road implements Comparable<Road> {

	private Town source;
	private Town destination;
	private int distance;
	private String name;
	
	public Road(Town source, Town destination, int degrees, String name) { // parameterized constructor
		this.source = source;
		this.destination = destination;
		this.distance = degrees;
		this.name = name;
	}
	
	public Road(Town source, Town destination, String name) { // parameterized constructor
		this.source = source;
		this.destination = destination;
		this.distance = 1;
		this.name = name;
	}
	
	public boolean contains(Town town) { // checks if a town is part of the given road
		if (source.equals(town) || destination.equals(town))
			return true;
		else
			return false;
	}
	
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}
	
	public Town getDestination() {
		return destination;
	}
	
	public Town getSource() {
		return source;
	}
	
	public int compareTo(Road o) {
		if (this.name.compareTo(o.name) < 0)
			return -1;
		else if (this.name.compareTo(o.name) == 0)
			return 0;
		else
			return 1;
	}
	
	public int getWeight() {
		return distance;
	}
	
	public boolean equals(Object r) {
		Road o = (Road) r;
		if (this.source.equals(o.source) && this.destination.equals(o.destination))
			return true;
		else if (this.source.equals(o.destination) && this.destination.equals(o.source))
			return true;
		else
			return false;
	}
	
}
