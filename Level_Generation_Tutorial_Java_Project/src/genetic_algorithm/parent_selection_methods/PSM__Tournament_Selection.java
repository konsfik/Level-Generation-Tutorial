package genetic_algorithm.parent_selection_methods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import genetic_algorithm.Level_Individual;

/**
 * Tournament selection works in the folowing manner: A set of random
 * individuals of size N is selected, out of the population. We refer to this
 * subset as the tournament population. Then, this subset is sorted and the best
 * included individual is returned.
 * 
 * @author Konstantinos Sfikas
 *
 */
public class PSM__Tournament_Selection extends Parent_Selection_Method
{
	public int tournament_size;

	public PSM__Tournament_Selection(int tournament_size)
	{
		this.tournament_size = tournament_size;
	}

	@Override
	public Level_Individual Select_Parent__Return_Clone(
			Random rand,
			ArrayList<Level_Individual> population)
	{
		// create a copy of the current population
		ArrayList<Level_Individual> population_copy = new ArrayList<Level_Individual>();
		for (Level_Individual individual : population)
		{
			population_copy.add((Level_Individual) individual.clone());
		}

		// declare a tournament population
		ArrayList<Level_Individual> tournament_population = new ArrayList<Level_Individual>();

		/**
		 * Fill the tournament population with randomly selected individuals. The
		 * randomly selected individuals are removed from the population copy and added
		 * to the tournament population. This way, we ensure that they are random, yet
		 * unique.
		 */
		for (int i = 0; i < tournament_size; i++)
		{
			int random_index = rand.nextInt(population_copy.size());
			Level_Individual ind = population_copy.get(random_index);

			// remove the individual from the population copy
			population_copy.remove(random_index);

			// add the individual to the tournament population
			tournament_population.add(ind);
		}

		// sort the tournament population
		Collections.sort(tournament_population);

		// reverse the tournament population, so that the first one is the best
		Collections.reverse(tournament_population);

		// return the best individual out of the tournament population.
		return tournament_population.get(0);
	}

}
