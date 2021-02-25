package Core;

import java.util.ArrayList;

public class Level implements Cloneable
{
	public int[][] cells;
	public Coords entrance;
	public Coords exit;

	public Level(
			int width,
			int height,
			Coords entrance,
			Coords exit)
	{
		// initialize the cells 2d array
		cells = new int[width][height];

		// set all values of the array to 0 (floor)
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				Ï__Set_Cell__Floor(x, y);
			}
		}

		this.entrance = (Coords) entrance.clone();
		this.exit = (Coords) exit.clone();

	}

	public void Ï__Set_Cell__Wall(Coords coords)
	{
		cells[coords.x][coords.y] = 1;
	}

	public void Ï__Set_Cell__Wall(int x, int y)
	{
		cells[x][y] = 1;
	}

	public void Ï__Set_Cell__Floor(Coords coords)
	{
		cells[coords.x][coords.y] = 0;
	}

	public void Ï__Set_Cell__Floor(int x, int y)
	{
		cells[x][y] = 0;
	}

	public int Q__Map_Width()
	{
		return cells.length;
	}

	public int Q__Map_Height()
	{
		return cells[0].length;
	}

	public boolean Q__Is_Cell__Wall(Coords cell)
	{
		return Q__Is_Cell__Wall(cell.x, cell.y);
	}

	public boolean Q__Is_Cell__Wall(int x, int y)
	{
		return cells[x][y] == 1;
	}

	public boolean Q__Is_Cell__Floor(Coords cell)
	{
		return Q__Is_Cell__Floor(cell.x, cell.y);
	}

	public boolean Q__Is_Cell__Floor(int x, int y)
	{
		return cells[x][y] == 0;
	}

	/**
	 * Returns all of the cells in the map, in the form of a list of coordinates.
	 * 
	 * @return
	 */
	public ArrayList<Coords> Q__All_Cells()
	{
		ArrayList<Coords> cells_list = new ArrayList<Coords>();

		int w = Q__Map_Width();
		int h = Q__Map_Height();

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
	public ArrayList<Coords> Q__Floor_Cells()
	{
		ArrayList<Coords> cells_list = new ArrayList<Coords>();

		int w = Q__Map_Width();
		int h = Q__Map_Height();

		for (int x = 0; x < w; x++)
		{
			for (int y = 0; y < h; y++)
			{
				if (Q__Is_Cell__Floor(x, y))
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
	public ArrayList<Coords> Q__Wall_Cells()
	{
		ArrayList<Coords> cells_list = new ArrayList<Coords>();

		int w = Q__Map_Width();
		int h = Q__Map_Height();

		for (int x = 0; x < w; x++)
		{
			for (int y = 0; y < h; y++)
			{
				if (Q__Is_Cell__Wall(x, y))
				{
					Coords c = new Coords(x, y);
					cells_list.add(c);
				}
			}
		}

		return cells_list;
	}

	/**
	 * Returns the neighbors of a cell.
	 * 
	 * @param cell
	 * @return
	 */
	public ArrayList<Coords> Q__Cell__Neighbors(Coords cell)
	{
		ArrayList<Coords> cell_neighbors = new ArrayList<Coords>();

		/**
		 * If the cell is wall, then it has no neighbors. In that case, return an empty
		 * list.
		 */
		if (Q__Is_Cell__Wall(cell))
		{
			return cell_neighbors;
		}

		// up
		if (cell.y < Q__Map_Height() - 1)
		{
			Coords up_cell = cell.Up();
			if (Q__Is_Cell__Floor(up_cell))
			{
				cell_neighbors.add(up_cell);
			}
		}

		// down
		if (cell.y > 0)
		{
			Coords down_cell = cell.Down();
			if (Q__Is_Cell__Floor(down_cell))
			{
				cell_neighbors.add(down_cell);
			}
		}

		// left
		if (cell.x > 0)
		{
			Coords left_cell = cell.Left();
			if (Q__Is_Cell__Floor(left_cell))
			{
				cell_neighbors.add(left_cell);
			}
		}

		// right
		if (cell.x < Q__Map_Width() - 1)
		{
			Coords right_cell = cell.Right();
			if (Q__Is_Cell__Floor(right_cell))
			{
				cell_neighbors.add(right_cell);
			}
		}

		return cell_neighbors;
	}

	/**
	 * Private constructor, intended for use by the clone() method.
	 * 
	 * @param map_to_copy
	 */
	private Level(Level map_to_copy)
	{
		entrance = (Coords) map_to_copy.entrance.clone();
		exit = (Coords) map_to_copy.exit.clone();

		int width = map_to_copy.cells.length;
		int height = map_to_copy.cells[0].length;
		cells = new int[width][height];
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				cells[x][y] = map_to_copy.cells[x][y];
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
