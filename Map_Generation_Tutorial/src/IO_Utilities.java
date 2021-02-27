import Core.Level;

public class IO_Utilities
{
	public static String Convert_Level_To_ASCII(Level level)
	{
		String ascii_map = "";

		int w = level.Width();
		int h = level.Height();

		for (int y = 0; y < h; y++)
		{
			for (int x = 0; x < w; x++)
			{
				if (level.entrance.x == x && level.entrance.y == y)
				{
					ascii_map += 'e';
				}
				else if (level.exit.x == x && level.exit.y == y)
				{
					ascii_map += 'x';
				}
				else
				{
					char cell = level.cells[x][y];
					ascii_map += cell;
				}
			}
			ascii_map += "\n";
		}

		return ascii_map;
	}
}
