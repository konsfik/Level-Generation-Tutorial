package genetic_algorithm.crossover_methods;

import java.util.Random;

import genetic_algorithm.Level_Individual;

public class CM__Custom extends Crossover_Method
{
	/**
	 * Custom, not implemented crossover method.
	 */
	@Override
	public Level_Individual Crossover_Parents(
			Random rand,
			Level_Individual parent_1,
			Level_Individual parent_2)
	{
		/**
		 * TODO: Use this class to define your own crossover method. The method should
		 * generate an offspring by combining the two parents (somehow) and return it.
		 * For example, the parents may be vertically split in half, and the offspring
		 * can be made up of merging the left part of parent 1 and the right part of
		 * parent 2. You can take a look at how CM__Random_Uniform works, as an example.
		 */
		return null;
	}

}
