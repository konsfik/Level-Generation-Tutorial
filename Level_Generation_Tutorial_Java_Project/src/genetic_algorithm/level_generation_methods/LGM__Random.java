package genetic_algorithm.level_generation_methods;

import java.util.Random;

import core.Coords;
import core.Level;

/**
 * A concrete implementation of a level generation method. This method generates
 * a level by randomly setting each cell of the level to either wall or floor.
 * There is an equal probability that a cell will be set to wall or floor, thus
 * around half of the levels' cells will be walls.
 * 
 * @author Konstantinos Sfikas
 *
 */
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
