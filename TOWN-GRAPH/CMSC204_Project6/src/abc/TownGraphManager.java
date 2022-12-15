/**
 * @author Jemil Patel
 */

package abc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

public class TownGraphManager implements TownGraphManagerInterface {

	private Graph graph;
	
	public TownGraphManager() { // default constructor
		graph = new Graph();
	}
	
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		if (weight < 0)
			return false;
		Road road = graph.addEdge(new Town(town1), new Town(town2), weight, roadName);
		if (road == null)
			return false;
		else 
			return true;
	}
	
	public String getRoad(String town1, String town2) {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		if (graph.getEdge(t1, t2) != null)
			return graph.getEdge(t1, t2).toString();
		else
			return null;
	}
	
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}
	
	public Town getTown(String name) {
		Town t = new Town(name);
		if (graph.containsVertex(t))
			return t;
		else
			return null;
	}
	
	public boolean containsTown(String v) {
		return graph.containsVertex(new Town(v));
	}
	
	public boolean containsRoadConnection(String town1, String town2) {
		return graph.containsEdge(new Town(town1), new Town(town2));
	}
	
	public ArrayList<String> allRoads() {
		Set<Road> s = graph.edgeSet();
		ArrayList<String> roads = new ArrayList<String>();
		for (Road r : s) {
			roads.add(r.toString());
		}
		Collections.sort(roads);
		return roads;
	}
	
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		if (graph.containsEdge(t1, t2)) {
			Road removedEdge = graph.getEdge(t1, t2);
			if (graph.removeEdge(t1, t2, removedEdge.getWeight(), road) != null)
				return true;
			else 
				return false;
		}
		else
			return false;
	}
	
	public boolean deleteTown(String v) {
		return graph.removeVertex(new Town(v));
	}
	
	public ArrayList<String> allTowns() {
		Set<Town> s = graph.vertexSet();
		ArrayList<String> towns = new ArrayList<String>();
		for (Town t  : s) {
			towns.add(t.toString());
		}
		Collections.sort(towns);
		return towns;
	}
	
	public ArrayList<String> getPath(String town1, String town2) {
		ArrayList<String> path = graph.shortestPath(new Town(town1), new Town(town2));
		if (path.isEmpty())
			return null;
		else
			return path;
	}
	
	public void populateTownGraph(File file) throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String[] data = sc.nextLine().replace(',', ';').split(";"); // splitting and storing the four values
			Town t1 = new Town(data[2]);
			Town t2 = new Town(data[3]);
			graph.addVertex(t1);
			graph.addVertex(t2);
			graph.addEdge(t1, t2, Integer.parseInt(data[1]), data[0]);
		}
		sc.close();
	}
	
}
