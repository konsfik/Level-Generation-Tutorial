package GA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Core.Coords;
import Core.Level;

public class GA
{
	Level_Generation_Method level_generation_method;
	Parent_Selection_Method parent_selection_method;
	Mutation_Method level_mutation_method;
	Evaluation_Method level_evaluation_method;
	int elitism_size;
	int population_size;
	int tournament_size;

	public ArrayList<Level_Individual> population;

	GA(
			Level_Generation_Method level_generation_method,
			Parent_Selection_Method parent_selection_method,
			Mutation_Method level_mutation_method,
			Evaluation_Method level_evaluation_method,
			int population_size,
			int elitism_size,
			int tournament_size)
	{
		this.level_generation_method = level_generation_method;
		this.parent_selection_method = parent_selection_method;
		this.level_mutation_method = level_mutation_method;
		this.level_evaluation_method = level_evaluation_method;

		this.elitism_size = elitism_size;
		this.population_size = population_size;
		this.tournament_size = tournament_size;

		population = new ArrayList<Level_Individual>();

	}

	public void Initialize_Population(
			Random rand,
			int width,
			int height,
			Coords entrance,
			Coords exit)
	{

		for (int i = 0; i < population_size; i++)
		{
			Level level = level_generation_method.Generate_Level(
					rand,
					width,
					height,
					entrance,
					exit);

			Level_Individual individual = new Level_Individual(level);
		}

		Evaluate_Population();
		Sort_Population();
	}

	public void Run(
			Random rand,
			int num_steps)
	{
		for (int i = 0; i < num_steps; i++)
		{
			Run_One_Step(rand);
		}
	}

	public void Run_One_Step(Random rand)
	{
		ArrayList<Level_Individual> new_population = new ArrayList<Level_Individual>();

		// Add the n - best individuals to the new population directly (without
		// mutation)
		// This is called elitism.
		// I.e. a number of the best individuals survive through generations.
		for (int i = 0; i < elitism_size; i++)
		{
			Level_Individual survivor = (Level_Individual) population.get(i).clone();
			new_population.add(survivor);
		}

		// Select parents, create offspring, mutate offspring, add them to the new
		// population. Repeat until the new population has reached the predetermined
		// size.
		while (new_population.size() < population_size)
		{
			// select parent -> generate offspring (as copy of parent)
			Level_Individual offspring = parent_selection_method.Select_Parent__Return_Clone(
					rand,
					population);

			// mutate offspring
			level_mutation_method.Mutate_Individual(
					rand,
					offspring);

			// add the mutant to the new population
			new_population.add(offspring);

		}

		// replace the old population with the new one
		population = new_population;

		// evaluate and sort the population...
		Evaluate_Population();
		Sort_Population();
	}

	private void Sort_Population()
	{
		// call the sort method, to arrange the population from smaller to higher
		// fitness
		Collections.sort(population);
		// reverse the list, so that we have the best individuals first
		Collections.reverse(population);
	}

	private void Evaluate_Population()
	{
		for (int i = 0; i < population_size; i++)
		{
			double fitness = level_evaluation_method.Evaluate_Individual(population.get(i));
			population.get(i).fitness = fitness;
		}
	}

	public double Best_Individual_Fitness()
	{
		return population.get(0).fitness;
	}

	public Level_Individual Best_Individual()
	{
		return (Level_Individual) population.get(0).clone();
	}
}
