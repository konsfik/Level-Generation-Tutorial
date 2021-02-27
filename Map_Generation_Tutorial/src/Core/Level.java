package Core;

import java.util.ArrayList;

public class Level implements Cloneable
{
	public char[][] cells;
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
	 * Returns whether a cell belongs to the level.
	 * I.e. whether its coordinates are within the proper range.
	 * @param cell
	 * @return
	 */
	public boolean Is_Within_Bounds(Coords cell)
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
	public boolean Is_Wall(Coords cell)
	{
		return Is_Wall(cell.x, cell.y);
	}

	/**
	 * Returns whether a specific cell (given its coordinates' x, y) is of type
	 * "wall".
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean Is_Wall(int x, int y)
	{
		return cells[x][y] == 'w';
	}

	/**
	 * Returns whether a specific cell (given its coordinates) is of type "floor".
	 * 
	 * @param cell
	 * @return
	 */
	public boolean Is_Floor(Coords cell)
	{
		return Is_Floor(cell.x, cell.y);
	}

	/**
	 * Returns whether a specific cell (given its coordinates' x, y) is of type
	 * "floor".
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean Is_Floor(int x, int y)
	{
		return cells[x][y] == 'f';
	}

	/**
	 * Returns all of the cells in the map, in the form of a list of coordinates.
	 * 
	 * @return
	 */
	public ArrayList<Coords> All_Cells__As_List()
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
	public ArrayList<Coords> Floor_Cells__As_List()
	{
		ArrayList<Coords> cells_list = new ArrayList<Coords>();

		int w = Width();
		int h = Height();

		for (int x = 0; x < w; x++)
		{
			for (int y = 0; y < h; y++)
			{
				if (Is_Floor(x, y))
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
	public ArrayList<Coords> Wall_Cells__As_List()
	{
		ArrayList<Coords> cells_list = new ArrayList<Coords>();

		int w = Width();
		int h = Height();

		for (int x = 0; x < w; x++)
		{
			for (int y = 0; y < h; y++)
			{
				if (Is_Wall(x, y))
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
		if (Is_Wall(cell))
		{
			return cell_neighbors;
		}

		if (Is_Within_Bounds(cell) == false)
		{
			return cell_neighbors;
		}

		// Otherwise, search left, right, up and down for floor cells.
		// up
		if (cell.y < Height() - 1)
		{
			Coords up_cell = cell.Up();
			if (Is_Floor(up_cell))
			{
				cell_neighbors.add(up_cell);
			}
		}

		// down
		if (cell.y > 0)
		{
			Coords down_cell = cell.Down();
			if (Is_Floor(down_cell))
			{
				cell_neighbors.add(down_cell);
			}
		}

		// left
		if (cell.x > 0)
		{
			Coords left_cell = cell.Left();
			if (Is_Floor(left_cell))
			{
				cell_neighbors.add(left_cell);
			}
		}

		// right
		if (cell.x < Width() - 1)
		{
			Coords right_cell = cell.Right();
			if (Is_Floor(right_cell))
			{
				cell_neighbors.add(right_cell);
			}
		}

		return cell_neighbors;
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
