package Core;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Level_Utilities
{
	/**
	 * Returns the manhattan distance between two vectors.
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static int Manhattan_Distance(
			Coords p1,
			Coords p2)
	{
		int manhattan_distance = Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
		return manhattan_distance;
	}

	public static int Distance_Between_Cells(
			Level map,
			Coords cell_1,
			Coords cell_2)
	{

		return 0;
	}

	/**
	 * Returns whether it is possible to get from cell_1 to cell_2. It operates by
	 * running a BFS search, starting from cell_1 and stopping when cell_2 is
	 * encountered. If cell_2 is never encountered and there are no more places to
	 * search, it means that cell_2 is not reachable from cell_2 and the method
	 * returns false.
	 * 
	 * @param level
	 * @param cell_1
	 * @param cell_2
	 * @return
	 */
	public static boolean Cells_Reachable(
			Level level,
			Coords root,
			Coords destination)
	{

		if (level.Q__Is_Cell__Floor(root) == false)
		{
			return false;
		}
		if (level.Q__Is_Cell__Floor(destination) == false)
		{
			return false;
		}

		ArrayList<Coords> visited_cells = new ArrayList<Coords>();
		ArrayList<Coords> search_queue = new ArrayList<Coords>();

		search_queue.add(root);
		visited_cells.add(root);

		boolean found_destination = false;

		while (search_queue.size() > 0)
		{
			/**
			 * Remove the first element of the search queue. I.e. the one that was first
			 * inserted to it. And use it as the current cell.
			 */
			Coords current = search_queue.remove(0);
			ArrayList<Coords> neighbors = level.Q__Cell__Neighbors(current);

			// find the unvisited neighbors...
			ArrayList<Coords> unvisited_neighbors = new ArrayList<Coords>();
			for (Coords neighbor : neighbors)
			{
				if (visited_cells.contains(neighbor) == false)
				{
					unvisited_neighbors.add(neighbor);
				}
			}

			for (Coords neighbor : unvisited_neighbors)
			{
				if (neighbor.equals(destination))
				{
					found_destination = true;
					break;
				}
				search_queue.add(neighbor);
				visited_cells.add(neighbor);
			}

		}

		if (found_destination)
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	/**
	 * Finds and returns the shortest path between a root and a destination cell of a map.
	 * Both the root and the destination need to be of type "floor", otherwise an empty list will be returned.
	 * 
	 * @param level
	 * @param root
	 * @param destination
	 * @return
	 */
	public static ArrayList<Coords> Shortest_Path__BFS(
			Level level,
			Coords root,
			Coords destination)
	{

		if (root.equals(destination))
		{
			ArrayList<Coords> short_path = new ArrayList<Coords>();
			short_path.add(root);
			return short_path;
		}
		if (level.Q__Is_Cell__Floor(root) == false)
		{
			return new ArrayList<Coords>();
		}
		if (level.Q__Is_Cell__Floor(destination) == false)
		{
			return new ArrayList<Coords>();
		}

		ArrayList<Coords> visited_cells = new ArrayList<Coords>();
		ArrayList<Coords> search_queue = new ArrayList<Coords>();

		HashMap<Coords, Coords> predecessors = new HashMap<Coords, Coords>();
		ArrayList<Coords> floor_cells = level.Q__Floor_Cells();
		for (Coords floor_cell : floor_cells)
		{
			predecessors.put(floor_cell, floor_cell);
		}
		search_queue.add(root);
		visited_cells.add(root);

		boolean found_destination = false;

		while (search_queue.size() > 0)
		{
			/**
			 * Remove the first element of the search queue. I.e. the one that was first
			 * inserted to it. And use it as the current cell.
			 */
			Coords current = search_queue.remove(0);
			ArrayList<Coords> neighbors = level.Q__Cell__Neighbors(current);

			// find the unvisited neighbors...
			ArrayList<Coords> unvisited_neighbors = new ArrayList<Coords>();
			for (Coords neighbor : neighbors)
			{
				if (visited_cells.contains(neighbor) == false)
				{
					unvisited_neighbors.add(neighbor);
				}
			}

			Collections.shuffle(unvisited_neighbors);

			// process all unvisited neighbors
			for (Coords neighbor : unvisited_neighbors)
			{
				search_queue.add(neighbor);
				visited_cells.add(neighbor);
				predecessors.put(neighbor, current);
				if (neighbor.equals(destination))
				{
					found_destination = true;
					break;
				}
			}
		}

		if (found_destination == false)
		{
			return new ArrayList<Coords>();
		}
		else
		{
			ArrayList<Coords> shortestPath = new ArrayList<Coords>();

			boolean pathFinished = false;
			// start from the destination and gradually move towards the root,
			// by following the predecessors' dictionary
			Coords current = destination;
			shortestPath.add(current);
			while (pathFinished == false)
			{
				Coords predecessor = predecessors.get(current);
				if (predecessor.equals(current) == false)
				{
					shortestPath.add(predecessor);
					current = predecessor;
				}
				else
				{
					pathFinished = true;
				}
			}

			Collections.reverse(shortestPath);

			if (shortestPath.contains(root)
					&&
					shortestPath.contains(destination))
			{
				return shortestPath;
			}
			else
			{
				return new ArrayList<Coords>();
			}

		}
	}

}
