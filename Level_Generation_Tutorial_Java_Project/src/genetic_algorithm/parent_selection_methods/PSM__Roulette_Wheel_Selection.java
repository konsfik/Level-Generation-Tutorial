package genetic_algorithm.parent_selection_methods;

import java.util.ArrayList;
import java.util.Random;

import genetic_algorithm.Level_Individual;

/**
 * Roulette wheel selection, otherwise known as fitness proportionate selection,
 * is a method for selecting an individual out of a population, with a
 * probability that is correlated to its fitness. In other words, individuals
 * with a high fitness should have a higher probability of being selected than
 * individuals with a low fitness. This wikipedia article explains the
 * operation: https://en.wikipedia.org/wiki/Fitness_proportionate_selection
 *
 */
public class PSM__Roulette_Wheel_Selection extends Parent_Selection_Method
{

	/**
	 * This wikipedia article explains the operation:
	 * https://en.wikipedia.org/wiki/Fitness_proportionate_selection
	 */
	@Override
	public Level_Individual Select_Parent__Return_Clone(
			Random rand,
			ArrayList<Level_Individual> population)
	{
		// write your implementation here:
		
		// select a parent individual, using the roulette wheel selection method
		
		// create a deep copy of the selected individual, using the individual.clone() method
		
		// return that clone so that it can be used by the GA
		return null;
	}

}
