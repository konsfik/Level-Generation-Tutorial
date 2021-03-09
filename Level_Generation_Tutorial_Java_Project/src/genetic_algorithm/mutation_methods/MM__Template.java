package genetic_algorithm.mutation_methods;

import java.util.Random;

import genetic_algorithm.Level_Individual;

public class MM__Template extends Mutation_Method
{

	@Override
	public void Mutate_Individual(Random rand, Level_Individual individual)
	{
		/**
		 * TODO: Use this class to define your own mutation method. This method should
		 * operate directly on the individual and (slightly) alter its structure. Make
		 * sure to take a look at the already implemented method(s) as an example.
		 */

		/**
		 * Proposed task: implement a mutation method that selects a region (rectangle)
		 * in the level and sets all the cells of that region to either walls or floor,
		 * at random. Make sure that the region is not very large. Its area should
		 * ideally be between 1% and 5% of the total area.
		 */
	}

}
