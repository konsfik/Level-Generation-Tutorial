package genetic_algorithm.parent_selection_methods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import genetic_algorithm.Level_Individual;

public class PSM__Tournament extends Parent_Selection_Method
{
	public int tournament_size;

	public PSM__Tournament(int tournament_size)
	{
		this.tournament_size = tournament_size;
	}

	@Override
	public Level_Individual Select_Parent__Return_Clone(Random rand, ArrayList<Level_Individual> population)
	{
		ArrayList<Level_Individual> population_copy = new ArrayList<Level_Individual>();
		for (Level_Individual individual : population)
		{
			population_copy.add((Level_Individual) individual.clone());
		}

		ArrayList<Level_Individual> tournament_population = new ArrayList<Level_Individual>();
		for (int i = 0; i < tournament_size; i++)
		{
			int random_index = rand.nextInt(population_copy.size());
			Level_Individual ind = population_copy.get(random_index);
			
			// remove the individual from the population copy
			population_copy.remove(random_index);
			
			// add the individual to the tournament population
			tournament_population.add(ind);
		}

		Collections.sort(tournament_population);

		Collections.reverse(tournament_population);

		return tournament_population.get(0);
	}

}
