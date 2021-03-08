package genetic_algorithm.level_generation_methods;

import java.util.Random;

import core.*;

/**
 * An abstract class that can be extended to define a level generation method.
 * This class is to be used by the genetic algorithm in order to generate the
 * initial population. Make sure to take a look at the already implemented
 * classes.
 * 
 * @author Konstantinos Sfikas
 *
 */
public abstract class Level_Generation_Method
{
	int level_width;
	int level_height;
	Coords entrance;
	Coords exit;

	public Level_Generation_Method(
			int level_width,
			int level_height,
			Coords entrance,
			Coords exit)
	{
		this.level_width = level_width;
		this.level_height = level_height;
		this.entrance = (Coords) entrance.clone();
		this.exit = (Coords) exit.clone();
	}

	public abstract Level Generate_Level(Random rand);
}
