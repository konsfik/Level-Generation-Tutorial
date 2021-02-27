package GA.parent_selection_methods;

import java.util.ArrayList;
import java.util.Random;

import GA.Level_Individual;

public class PSM__Roulette_Wheel_Selection extends Parent_Selection_Method
{

	/**
	 * https://en.wikipedia.org/wiki/Fitness_proportionate_selection
	 */
	@Override
	public Level_Individual Select_Parent__Return_Clone(
			Random rand,
			ArrayList<Level_Individual> population)
	{
		// find the sum of the fitnessesof all individuals
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
