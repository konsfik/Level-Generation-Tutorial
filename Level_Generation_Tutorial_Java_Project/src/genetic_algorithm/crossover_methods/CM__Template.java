package genetic_algorithm.crossover_methods;

import java.util.Random;

import genetic_algorithm.Level_Individual;

public class CM__Template extends Crossover_Method
{
	/**
	 * Custom, not implemented crossover method.
	 */
	@Override
	public Level_Individual Combine_Parents(
			Random rand,
			Level_Individual parent_1,
			Level_Individual parent_2)
	{
		/**
		 * TODO: Use this class to define your own crossover method. The method should
		 * generate an offspring by combining the two parents and return it. You can
		 * take a look at how CM__Random_Uniform works, as an example.
		 */

		/**
		 * Proposed task: Split the two parents in half and compose the offspring by
		 * merging the left part of parent 1 with the right part of parent 2.
		 */
		return null;
	}

}
