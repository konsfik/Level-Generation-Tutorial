package genetic_algorithm.parent_selection_methods;

import java.util.ArrayList;
import java.util.Random;

import genetic_algorithm.Level_Individual;

/**
 * Roulette wheel selection, otherwise referred to as fitness proportionate
 * selection, is a method for selecting an individual out of a population, with
 * a probability that is correlated to its fitness. In other words, individuals
 * with a high fitness have a higher probability of being selected than
 * individuals with a low fitness.
 * 
 * @author Konstantinos Sfikas
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
		// find the sum of the fitnesses of all individuals
		double fitness_sum = 0;
		for (Level_Individual individual : population)
		{
			fitness_sum += individual.fitness;
		}

		// get a random number from 0 to fitness_sum
		double dice_roll = rand.nextDouble() * fitness_sum;

		// find the individual that corresponds to the dice roll.
		// Individuals with higher fitness have a larger probability to be selected.
		// However, even the worst individuals still have a chance of being selected.
		double partial_sum = 0;
		for (Level_Individual individual : population)
		{
			partial_sum += individual.fitness;
			if (partial_sum >= dice_roll)
			{
				return (Level_Individual) individual.clone();
			}
		}
		return null;
	}

}
