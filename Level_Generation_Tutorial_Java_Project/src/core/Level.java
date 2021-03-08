package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * State representation of a Level. Includes a table of characters, where 'w'
 * represents a wall anf 'f' represents a floor tile. Also includes the entrance
 * and exit location.
 * 
 * @author Konstantinos Sfikas
 *
 */
public class Level implements Cloneable
{
	// the level's grid - cells are represented as a 2D array of characters, where
	// 'f' denotes floor tiles and 'w' denotes wall tiles. It is assumed that the
	// character can only move up / down / left / right (not diagonally)
	public char[][] cells;
	// the level also has an entrance and an exit whose coordinates are stored here.
	public Coords entrance;
	public Coords exit;

	public Level(
			int width,
			int height,
			Coords entrance,
			Coords exit)
	{
		// initialize the cells 2d array
		cells = new char[width][height];

		// set all values of the array to 0 (floor)
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				Set_Floor(x, y);
			}
		}

		this.entrance = (Coords) entrance.clone();
		this.exit = (Coords) exit.clone();

	}

	/**
	 * Sets a specific cell as wall (based on coordinates)
	 * 
	 * @param coords
	 */
	public void Set_Wall(Coords coords)
	{
		Set_Wall(coords.x, coords.y);
	}

	/**
	 * Sets a specific cell as wall (based on coordinates x, y)
	 * 
	 * @param x
	 * @param y
	 */
	public void Set_Wall(int x, int y)
	{
		cells[x][y] = 'w';
	}

	/**
	 * Sets a specific cell as floor (based on coordinates)
	 * 
	 * @param coords
	 */
	public void Set_Floor(Coords coords)
	{
		Set_Floor(coords.x, coords.y);
	}

	/**
	 * Sets a specific cell as floor (based on coordinates x, y)
	 * 
	 * @param coords
	 */
	public void Set_Floor(int x, int y)
	{
		cells[x][y] = 'f';
	}

	/**
	 * Returns the width of the level
	 * 
	 * @return
	 */
	public int Width()
	{
		return cells.length;
	}

	/**
	 * Returns the height of the level
	 * 
	 * @return
	 */
	public int Height()
	{
		return cells[0].length;
	}

	/**
	 * Returns whether the level is solvable. I.e. whether it is possible to go from
	 * the entance to the exit.
	 * 
	 * @return
	 */
	public boolean Is_Level__Solvable()
	{
		return Path_Exists(entrance, exit);
	}

	/**
	 * Returns whether a cell belongs to the level. I.e. whether its coordinates are
	 * within the proper range.
	 * 
	 * @param cell
	 * @return
	 */
	public boolean Is_Cell__Within_Bounds(Coords cell)
	{
		int w = Width();
		int h = Height();

		return cell.x >= 0 && cell.y >= 0 && cell.x < w && cell.y < h;
	}

	/**
	 * Returns whether a specific cell (given its coordinates) is of type "wall".
	 * 
	 * @param cell
	 * @return
	 */
	public boolean Is_Cell__Wall(Coords cell)
	{
		return Is_Cell__Wall(cell.x, cell.y);
	}

	/**
	 * Returns whether a specific cell (given its coordinates' x, y) is of type
	 * "wall".
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean Is_Cell__Wall(int x, int y)
	{
		return cells[x][y] == 'w';
	}

	/**
	 * Returns whether a specific cell (given its coordinates) is of type "floor".
	 * 
	 * @param cell
	 * @return
	 */
	public boolean Is_Cell__Floor(Coords cell)
	{
		return Is_Cell__Floor(cell.x, cell.y);
	}

	/**
	 * Returns whether a specific cell (given its coordinates' x, y) is of type
	 * "floor".
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean Is_Cell__Floor(int x, int y)
	{
		return cells[x][y] == 'f';
	}

	/**
	 * Returns all of the cells in the map, in the form of a list of coordinates.
	 * 
	 * @return
	 */
	public ArrayList<Coords> Cells__All__As_List()
	{
		ArrayList<Coords> cells_list = new ArrayList<Coords>();

		int w = Width();
		int h = Height();

		for (int x = 0; x < w; x++)
		{
			for (int y = 0; y < h; y++)
			{
				Coords c = new Coords(x, y);
				cells_list.add(c);
			}
		}

		return cells_list;
	}

	/**
	 * Returns the list of coordinates of all the cells that are of type "floor"
	 * i.e. the cells whose value is 0.
	 * 
	 * @return
	 */
	public ArrayList<Coords> Cells__Floor__As_List()
	{
		ArrayList<Coords> cells_list = new ArrayList<Coords>();

		int w = Width();
		int h = Height();

		for (int x = 0; x < w; x++)
		{
			for (int y = 0; y < h; y++)
			{
				if (Is_Cell__Floor(x, y))
				{
					Coords c = new Coords(x, y);
					cells_list.add(c);
				}
			}
		}

		return cells_list;
	}

	/**
	 * Returns the list of coordinates of all the cells that are of type "wall" i.e.
	 * the cells whose value is 1.
	 * 
	 * @return
	 */
	public ArrayList<Coords> Cells__Wall__As_List()
	{
		ArrayList<Coords> cells_list = new ArrayList<Coords>();

		int w = Width();
		int h = Height();

		for (int x = 0; x < w; x++)
		{
			for (int y = 0; y < h; y++)
			{
				if (Is_Cell__Wall(x, y))
				{
					Coords c = new Coords(x, y);
					cells_list.add(c);
				}
			}
		}

		return cells_list;
	}

	/**
	 * Returns the neighbors of a cell. If the cell is a wall, an empty list will be
	 * returned. If the cell is floor, the method returns any other floor cells that
	 * lie at its left, right, up or down.
	 * 
	 * @param cell
	 * @return
	 */
	public ArrayList<Coords> Cell_Neighbors(Coords cell)
	{
		ArrayList<Coords> cell_neighbors = new ArrayList<Coords>();

		// If the cell is wall, then it has no neighbors.
		// In that case, return an empty list.
		if (Is_Cell__Wall(cell))
		{
			return cell_neighbors;
		}

		if (Is_Cell__Within_Bounds(cell) == false)
		{
			return cell_neighbors;
		}

		// Otherwise, search left, right, up and down for floor cells.
		// up
		if (cell.y < Height() - 1)
		{
			Coords up_cell = cell.Up();
			if (Is_Cell__Floor(up_cell))
			{
				cell_neighbors.add(up_cell);
			}
		}

		// down
		if (cell.y > 0)
		{
			Coords down_cell = cell.Down();
			if (Is_Cell__Floor(down_cell))
			{
				cell_neighbors.add(down_cell);
			}
		}

		// left
		if (cell.x > 0)
		{
			Coords left_cell = cell.Left();
			if (Is_Cell__Floor(left_cell))
			{
				cell_neighbors.add(left_cell);
			}
		}

		// right
		if (cell.x < Width() - 1)
		{
			Coords right_cell = cell.Right();
			if (Is_Cell__Floor(right_cell))
			{
				cell_neighbors.add(right_cell);
			}
		}

		return cell_neighbors;
	}

	/**
	 * Converts the level to an ASCII map, where 'f' is floor, 'w' is wall, 'E' is
	 * entrance (when entrance is floor), 'e' is entrance (when entrance is wall),
	 * 'X' is exit (when exit is floor) and 'x' is exit (when exit is wall). The
	 * method returns the ASCII map in the form of a string, which can be printed to
	 * the console or saved to disk.
	 * 
	 * @return
	 */
	public String To_ASCII()
	{
		String ascii_map = "";

		int w = Width();
		int h = Height();

		for (int y = 0; y < h; y++)
		{
			for (int x = 0; x < w; x++)
			{
				if (entrance.x == x && entrance.y == y)
				{
					if (Is_Cell__Wall(entrance))
					{
						ascii_map += 'e';
					}
					else
					{
						ascii_map += 'E';
					}
				}
				else if (exit.x == x && exit.y == y)
				{
					if (Is_Cell__Wall(exit))
					{
						ascii_map += 'x';
					}
					else
					{
						ascii_map += 'X';
					}
				}
				else
				{
					char cell = cells[x][y];
					ascii_map += cell;
				}
			}
			ascii_map += "\n";
		}

		return ascii_map;
	}

	/**
	 * Finds and returns the shortest path between a root and a destination cell of
	 * a map. Both the root and the destination need to be of type "floor",
	 * otherwise an empty list will be returned.
	 * 
	 * @param root
	 * @param destination
	 * @return
	 */
	public ArrayList<Coords> Shortest_Path__BFS(
			Coords root,
			Coords destination)
	{

		if (root.equals(destination))
		{
			ArrayList<Coords> short_path = new ArrayList<Coords>();
			short_path.add(root);
			return short_path;
		}
		if (Is_Cell__Floor(root) == false)
		{
			return new ArrayList<Coords>();
		}
		if (Is_Cell__Floor(destination) == false)
		{
			return new ArrayList<Coords>();
		}
		if (Is_Cell__Within_Bounds(root) == false)
		{
			return new ArrayList<Coords>();
		}
		if (Is_Cell__Within_Bounds(destination) == false)
		{
			return new ArrayList<Coords>();
		}

		ArrayList<Coords> visited_cells = new ArrayList<Coords>();
		ArrayList<Coords> search_queue = new ArrayList<Coords>();

		ArrayList<Coords> floor_cells = Cells__Floor__As_List();

		HashMap<Coords, Coords> predecessors = new HashMap<Coords, Coords>();

		for (Coords floor_cell : floor_cells)
		{
			predecessors.put(floor_cell, floor_cell);
		}
		search_queue.add(root);
		visited_cells.add(root);

		boolean found_destination = false;

		while (search_queue.size() > 0 && found_destination == false)
		{
			/**
			 * Remove the first element of the search queue. I.e. the one that was first
			 * inserted to it. And use it as the current cell.
			 */
			Coords current = search_queue.remove(0);
			ArrayList<Coords> neighbors = Cell_Neighbors(current);

			// find the unvisited neighbors...
			ArrayList<Coords> unvisited_neighbors = new ArrayList<Coords>();
			for (Coords neighbor : neighbors)
			{
				if (visited_cells.contains(neighbor) == false)
				{
					unvisited_neighbors.add(neighbor);
				}
			}

//			Collections.shuffle(unvisited_neighbors);

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

	/**
	 * Returns whether it is possible to get from cell_1 to cell_2. It operates by
	 * running a BFS search, starting from cell_1 and stopping when cell_2 is
	 * encountered. If cell_2 is never encountered and there are no more places to
	 * search, it means that cell_2 is not reachable from cell_2 and the method
	 * returns false.
	 * 
	 * @param cell_1
	 * @param cell_2
	 * @return
	 */
	public boolean Path_Exists(
			Coords root,
			Coords destination)
	{

		if (Is_Cell__Floor(root) == false)
		{
			return false;
		}
		if (Is_Cell__Floor(destination) == false)
		{
			return false;
		}

		ArrayList<Coords> visited_cells = new ArrayList<Coords>();
		ArrayList<Coords> search_queue = new ArrayList<Coords>();

		search_queue.add(root);
		visited_cells.add(root);

		boolean found_destination = false;

		while (search_queue.size() > 0 && found_destination == false)
		{
			/**
			 * Remove the first element of the search queue. I.e. the one that was first
			 * inserted to it. And use it as the current cell.
			 */
			Coords current = search_queue.remove(0);
			ArrayList<Coords> neighbors = Cell_Neighbors(current);

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

		return found_destination;

	}

	/**
	 * Returns the length of the path from root to destination. If it is impossible
	 * to get from root to destination, the method returns -1.
	 * 
	 * @param level
	 * @param root
	 * @param destination
	 * @return
	 */
	public int Path_Length(
			Coords root,
			Coords destination)
	{
		ArrayList<Coords> shortest_path = Shortest_Path__BFS(
				root,
				destination);

		if (shortest_path == null)
		{
			return -1;
		}
		else if (shortest_path.size() == 0)
		{
			return -1;
		}
		else
		{
			return shortest_path.size();
		}
	}
	
	/**
	 * Returns the manhattan distance between two cells.
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

	/**
	 * Private constructor, to be used by the clone() method.
	 * 
	 * @param level_to_copy
	 */
	private Level(Level level_to_copy)
	{
		entrance = (Coords) level_to_copy.entrance.clone();
		exit = (Coords) level_to_copy.exit.clone();

		int width = level_to_copy.cells.length;
		int height = level_to_copy.cells[0].length;
		cells = new char[width][height];
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				cells[x][y] = level_to_copy.cells[x][y];
			}
		}
	}

	/**
	 * Generates a deep copy of the level. A deep copy is an exact copy which is,
	 * however, a separate object in memory. A deep copy is used when, for example,
	 * we want to copy an individual and mutate it, without affecting its parent.
	 * Utilizes the private constructor.
	 */
	@Override
	public Object clone()
	{
		return new Level(this);
	}
}
