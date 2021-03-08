package genetic_algorithm.level_generation_methods;

import java.util.Random;

import core.Coords;
import core.Level;

/**
 * A concrete implementation of a level generation method. This method generates
 * a level by randomly setting each cell of the level to either wall or floor.
 * However, there is a specific probability for asigning walls, thus the user
 * may control the density of walls in the generated levels.
 * 
 * @author Konstantinos Sfikas
 *
 */
public class LGM__Random__Wall_Probability extends Level_Generation_Method
{
	public double wall_probability;

	public LGM__Random__Wall_Probability(
			int level_width,
			int level_height,
			Coords entrance,
			Coords exit,
			double wall_probability)
	{
		super(
				level_width,
				level_height,
				entrance,
				exit);

		this.wall_probability = wall_probability;
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
				if (dice_roll < wall_probability)
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
