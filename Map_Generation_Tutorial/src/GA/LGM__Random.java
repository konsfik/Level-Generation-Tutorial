package GA;

import java.util.Random;

import Core.Coords;
import Core.Level;

public class LGM__Random extends Level_Generation_Method
{

	@Override
	public Level Generate_Level(
			Random rand,
			int width,
			int height,
			Coords entrance,
			Coords exit)
	{
		Level level = new Level(
				width,
				height,
				entrance,
				exit);

		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				double dice_roll = rand.nextDouble();
				if (dice_roll < 0.5)
				{
					level.Set_Wall(x, y);
				}
				else
				{
					level.Set_Floor(x, y);
				}
			}
		}
		
		return level;
	}

}
