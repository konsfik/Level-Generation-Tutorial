package Core;

import java.util.ArrayList;

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

	public void Set_Wall(Coords coords)
	{
		Set_Wall(coords.x, coords.y);
	}

	public void Set_Wall(int x, int y)
	{
		cells[x][y] = 'w';
	}

	public void Set_Floor(Coords coords)
	{
		Set_Floor(coords.x, coords.y);
	}

	public void Set_Floor(int x, int y)
	{
		cells[x][y] = 'f';
	}

	public int Width()
	{
		return cells.length;
	}

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
		return Level_Utilities.Path_Exists(this, entrance, exit);
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
					ascii_map += 'e';
				}
				else if (exit.x == x && exit.y == y)
				{
					ascii_map += 'x';
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
	 * Generates a deep copy of the map. Utilizes the private constructor.
	 */
	@Override
	public Object clone()
	{
		return new Level(this);
	}
}
