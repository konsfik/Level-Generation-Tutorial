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
		 * TODO: Use this class to define your own level generation method. For example,
		 * you may set the initial state of the level to be completely covered by walls
		 * and then apply a digger agent that digs corridors and rooms in that level.
		 * Make sure to check out the already implemented variations.
		 */
		return null;
	}

}
