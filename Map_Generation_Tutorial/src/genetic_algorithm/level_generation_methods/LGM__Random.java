package genetic_algorithm.level_generation_methods;

import java.util.Random;

import Core.Coords;
import Core.Level;

public class LGM__Random extends Level_Generation_Method
{

	public LGM__Random(
			int level_width,
			int level_height,
			Coords entrance,
			Coords exit)
	{
		super(
				level_width,
				level_height,
				entrance,
				exit);

	}

	@Override
	public Level Generate_Level(Random rand)
	{
		Level level = new Level(
				level_width,
				level_height,
				entrance,
				exit);

		for (int x = 0; x < level_width; x++)
		{
			for (int y = 0; y < level_height; y++)
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
