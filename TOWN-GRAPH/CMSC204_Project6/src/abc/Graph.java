/**
 * @author Jemil Patel
 */

package abc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Graph implements GraphInterface<Town, Road> {

	private Set<Town> vertices;
	private Set<Road> edges;
	private Map<Town, Integer> shortestPaths; // stores the shortest distance of all towns from a given town
	private Map<Town, Town> connections; // stores the previous town of all towns for a given shortest path: (currentTown, previousTown)
	
	public Graph() { // default constructor
		vertices = new HashSet<Town>();
		edges = new HashSet<Road>();
		shortestPaths = new HashMap<Town, Integer>();
		connections = new HashMap<Town, Town>();
	}
	
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		Road edge = null;
		if (sourceVertex == null || destinationVertex == null)
			return null;
		else if (!vertices.contains(sourceVertex) || !vertices.contains(destinationVertex))
			return null;
		else {
			for (Road e : edges) {
				if (e.contains(sourceVertex) && e.contains(destinationVertex)) {
					edge = e;
					break;
				}
			}
		}
		return edge;
	}
	
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road edge = null;
		if (sourceVertex == null || destinationVertex == null)
			throw new NullPointerException();
		else if (!vertices.contains(sourceVertex) || !vertices.contains(destinationVertex))
			throw new IllegalArgumentException();
		else {
			edge = new Road(sourceVertex, destinationVertex, weight, description);
			for (Town v : vertices) {
				if (v.equals(sourceVertex))
					v.addAdjacentTown(destinationVertex);
				else if (v.equals(destinationVertex))
					v.addAdjacentTown(sourceVertex);
			}
			edges.add(edge);
		}
		return edge;
	}
	
	public boolean addVertex(Town town) {
		if (town == null)
			throw new NullPointerException();
		else if (vertices.contains(town))
			return false;
		else {
			vertices.add(town);
			return true;
		}
	}
	
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		if (sourceVertex == null || destinationVertex == null)
			return false;
		else if (!vertices.contains(sourceVertex) || !vertices.contains(destinationVertex))
			return false;
		else {
			for (Road e : edges) {
				if (e.contains(sourceVertex) && e.contains(destinationVertex))
					return true;
			}
			return false;
		}
	}
	
	public boolean containsVertex(Town town) {
		if (town == null)
			return false;
		else if (vertices.contains(town))
			return true;
		else
			return false;
	}
	
	public Set<Road> edgeSet() {
		return edges;
	}
	
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> s = new HashSet<Road>();
		if (vertex == null)
			throw new NullPointerException();
		else if (!vertices.contains(vertex))
			throw new IllegalArgumentException();
		else {
			for (Road e : edges) {
				if (e.contains(vertex))
					s.add(e);
			}
		}
		return s;
	}
	
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road removedEdge = null;
		for (Road e : edges) { // removing the given edge
			if (e.contains(sourceVertex) && e.contains(destinationVertex) && e.getWeight() == weight && e.getName().equals(description)) {
				removedEdge = e;
				edges.remove(e);
			}
		}
		for (Town v : vertices) { // removing the adjacent town relation between the two towns of the given edge
			if (v.equals(sourceVertex))
				v.removeAdjacentTown(destinationVertex);
			else if (v.equals(destinationVertex))
				v.removeAdjacentTown(sourceVertex);
		}
		return removedEdge;
	}
	
	public boolean removeVertex(Town town) {
		if (town == null)
			return false;
		else if (!vertices.contains(town))
			return false;
		else {
			for (Road e : edges) { // first removing the edges connected to the town
				if (e.contains(town))
					edges.remove(e);
			}
			vertices.remove(town); // then removing the town itself
			return true;
		}
	}
	
	public Set<Town> vertexSet() {
		return vertices;
	}
	
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex); // calling dijkstraShortestPath method to initialize the hash maps
		ArrayList<String> routes = new ArrayList<String>();
		Town t = destinationVertex;
		while (!t.equals(sourceVertex)) {
			for (Road e : edges) {
				if (e.contains(t) && e.contains(connections.get(t)))
					routes.add(connections.get(t) + " via " + e + " to " + t + " " + e.getWeight() + " mi");
			}
			t = connections.get(t); // at this moment we are initializing t to its previous town in the shortest path
									// this way t will go backwards from destination to source
		}
		Collections.reverse(routes); // since the list has routes stored in a backward fashion, we reverse it
		return routes;
	}
	
	public void dijkstraShortestPath(Town sourceVertex) {
		Set<Town> explored = new HashSet<Town>(); // set to keep track of the towns visited
		for (Town t : vertices) { // initializing the distance to all towns to infinity except the sourceVertex itself
			if (t.equals(sourceVertex))
				shortestPaths.put(t, 0);
			else
				shortestPaths.put(t, Integer.MAX_VALUE);
		}
		while (!explored.equals(vertices)) { // loop runs until all towns are visited
			Town town = getNearestTown(shortestPaths, explored); // extracting the town with the smallest weight inside the map, excluding explored towns
			explored.add(town);
			if (town.equals(sourceVertex)) // if the town is sourceVertex then we do not have a previous town for it, hence set it to null
				connections.put(town, null);
			for (Road e : edges) {
				if (e.getSource().equals(town)) { // if the town is a part of the current edge as a source
					if ((e.getWeight() + shortestPaths.get(town)) < shortestPaths.get(e.getDestination())) { // if the new weight is less than the previous weight then replace it
						shortestPaths.replace(e.getDestination(), e.getWeight() + shortestPaths.get(town));
						connections.put(e.getDestination(), e.getSource()); // store the value of previous town in order to get the final path
					}
				}
				else if (e.getDestination().equals(town)) { // if the town is a part of the current edge as a destination
					if ((e.getWeight() + shortestPaths.get(town)) < shortestPaths.get(e.getSource())) { // if the new weight is less than the previous weight then replace it
						shortestPaths.replace(e.getSource(), e.getWeight() + shortestPaths.get(town));
						connections.put(e.getSource(), e.getDestination()); // store the value of previous town in order to get the final path
					}
				}
			}
		}
	}
	
	private Town getNearestTown(Map<Town, Integer> m, Set<Town> s) {
		Map<Town, Integer> map = new HashMap<Town, Integer>();
		for (Town t : m.keySet()) { // deep copy of provided map in order to avoid changing the original data
			map.put(t, m.get(t));
		}
		Iterator<Town> iter = s.iterator();
		while (iter.hasNext()) { // removing the explored towns from the map
			map.remove(iter.next());
		}
		int smallest = (int) map.values().toArray()[0];
		Town nearest = null;
		for (Town t : map.keySet()) { // finding the smallest weight among the unexplored towns
			if (map.get(t) < smallest)
				smallest = map.get(t);
		}
		for (Town t : map.keySet()) { // finding the town associated with the smallest weight
			if (map.get(t) == smallest)
				nearest = t;
		}
		return nearest;
	}
	
}
