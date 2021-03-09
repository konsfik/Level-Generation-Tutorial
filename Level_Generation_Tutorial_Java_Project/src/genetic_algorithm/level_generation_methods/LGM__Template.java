package genetic_algorithm.level_generation_methods;

import java.util.Random;

import core.Coords;
import core.Level;

public class LGM__Template extends Level_Generation_Method
{
	/**
	 * Constructor: A level generation method should contain the desired level width
	 * and height, as well as the location of the entrance and exit of the level.
	 * 
	 * @param level_width
	 * @param level_height
	 * @param entrance
	 * @param exit
	 */
	public LGM__Template(int level_width, int level_height, Coords entrance, Coords exit)
	{
		super(level_width, level_height, entrance, exit);

	}

	@Override
	public Level Generate_Level(Random rand)
	{
		/**
		 * TODO: Use this class to define your own level generation method. Make sure to
		 * check out the already implemented variations for reference.
		 */

		/**
		 * Proposed task 1: Start by setting all of the level's cells to walls and then
		 * aooly a simple digging agent that moves around and creates corridors and
		 * rooms.
		 */

		/**
		 * Proposed task 2: Start with an empty level (only floor) and assign random
		 * rectangle areas as either walls or floors. This method may result to levels
		 * that have more regions and are not as "noisy" as the cel - by - cell random
		 * method.
		 */
		return null;
	}

}
