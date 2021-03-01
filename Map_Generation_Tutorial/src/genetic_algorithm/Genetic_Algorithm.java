package genetic_algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Core.Coords;
import Core.Level;
import genetic_algorithm.evaluation_methods.Evaluation_Method;
import genetic_algorithm.level_generation_methods.Level_Generation_Method;
import genetic_algorithm.mutation_methods.Mutation_Method;
import genetic_algorithm.parent_selection_methods.Parent_Selection_Method;

public class Genetic_Algorithm
{
	Level_Generation_Method level_generation_method;
	Parent_Selection_Method parent_selection_method;
	Mutation_Method mutation_method;
	Evaluation_Method evaluation_method;
	int elitism_size;
	int population_size;

	public ArrayList<Level_Individual> population;

	public Genetic_Algorithm(
			Level_Generation_Method level_generation_method,
			Parent_Selection_Method parent_selection_method,
			Mutation_Method mutation_method,
			Evaluation_Method evaluation_method,
			int population_size,
			int elitism_size)
	{
		this.level_generation_method = level_generation_method;
		this.parent_selection_method = parent_selection_method;
		this.mutation_method = mutation_method;
		this.evaluation_method = evaluation_method;

		this.elitism_size = elitism_size;
		this.population_size = population_size;

		population = new ArrayList<Level_Individual>();

	}

	/**
	 * Generates, evaluates and sorts the initial population.
	 * @param rand
	 */
	public void Initialize_Population(Random rand)
	{
		population = new ArrayList<Level_Individual>();
		
		for (int i = 0; i < population_size; i++)
		{
			// generate a new level from scratch,
			// by utilizing the available level generator
			Level level = level_generation_method.Generate_Level(rand);

			// assign this level to a new individual
			Level_Individual individual = new Level_Individual(level);
			
			// store the new individual in the population
			population.add(individual);
		}
		
		// evaluate all the individuals of the population
		Evaluate_Population();
		
		// properly sort the population
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

	/**
	 * This method includes all the steps to complete a single "generation".
	 * It executes the following steps:
	 * 1) Elitism
	 * 2) Selection - mutation
	 * 3) Evaluation and sorting of the new population.
	 * @param rand
	 */
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
			mutation_method.Mutate_Individual(
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
			double fitness = evaluation_method.Evaluate_Individual(population.get(i));
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
