package genetic_algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import core.Coords;
import core.Level;
import genetic_algorithm.crossover_methods.Crossover_Method;
import genetic_algorithm.evaluation_methods.Evaluation_Method;
import genetic_algorithm.level_generation_methods.Level_Generation_Method;
import genetic_algorithm.mutation_methods.Mutation_Method;
import genetic_algorithm.parent_selection_methods.Parent_Selection_Method;

/**
 * Class that represents all the parts, settings and operation of a typical
 * Genetic Algorithm. The class is implemented in a modular manner, thus the
 * user must define their preferred level generation method, parent selection
 * method, crossover method, mutation method, evaluation method, etc.
 * 
 * 
 * @author Konstantinos Sfikas
 *
 */
public class Genetic_Algorithm
{
	Level_Generation_Method level_generation_method;

	Parent_Selection_Method parent_selection_method;
	Crossover_Method crossover_method;
	Mutation_Method mutation_method;
	Evaluation_Method evaluation_method;

	int elitism_size;
	int population_size;

	public ArrayList<Level_Individual> population;

	public Genetic_Algorithm(
			Level_Generation_Method level_generation_method,
			Parent_Selection_Method parent_selection_method,
			Crossover_Method crossover_method,
			Mutation_Method mutation_method,
			Evaluation_Method evaluation_method,
			int population_size,
			int elitism_size)
	{
		this.level_generation_method = level_generation_method;
		this.parent_selection_method = parent_selection_method;
		this.crossover_method = crossover_method;
		this.mutation_method = mutation_method;
		this.evaluation_method = evaluation_method;

		this.elitism_size = elitism_size;
		this.population_size = population_size;

		population = new ArrayList<Level_Individual>();

	}

	/**
	 * Generates, evaluates and sorts the initial population. Make sure to run this
	 * method, before actually running the algorithm! This method can also be used
	 * for resetting the algorithm (deleting the current population and generating a
	 * new one).
	 * 
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
		Sort_Population(rand);
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
	 * This method includes all the steps to complete a single "generation". It
	 * executes the following steps: 1) Elitism 2) Selection - mutation 3)
	 * Evaluation and sorting of the new population.
	 * 
	 * @param rand
	 */
	public void Run_One_Step(Random rand)
	{
		// Create an empty list, for temporarily storing the new population.
		ArrayList<Level_Individual> new_population = new ArrayList<Level_Individual>();

		// 1. Elitism: Add the n - best individuals to the new population directly
		// (without mutation)
		for (int i = 0; i < elitism_size; i++)
		{
			Level_Individual survivor = (Level_Individual) population.get(i).clone();
			new_population.add(survivor);
		}

		// Select parents, create offspring, mutate offspring, add them to the new
		// population.
		// Repeat until the new population has reached the predetermined size.
		while (new_population.size() < population_size)
		{
			// 2. Select the two parents
			Level_Individual parent_1 = parent_selection_method.Select_Parent__Return_Clone(
					rand,
					population);
			Level_Individual parent_2 = parent_selection_method.Select_Parent__Return_Clone(
					rand,
					population);

			// 3. Generate offspring (by applying crossover to the two parents)
			Level_Individual offspring = crossover_method.Combine_Parents(rand, parent_1, parent_2);

			// 4. Mutate offspring
			mutation_method.Mutate_Individual(
					rand,
					offspring);

			// 5. Add the mutant to the new population
			new_population.add(offspring);

		}

		// 6. Replace the old population with the new one
		population = new_population;

		// 7. evaluate and sort the population...
		Evaluate_Population();
		Sort_Population(rand);
	}

	/**
	 * Sets the population in order, from higher to lower fitness. This is important
	 * for properly selecting the best individuals, when elitism is used.
	 */
	private void Sort_Population(Random rand)
	{
		// shuffle before sorting, to randomize the order of individuals with common fitness
		Collections.shuffle(population, rand);
		
		// call the sort method, to arrange the population from smaller to higher
		// fitness
		Collections.sort(population);

		// reverse the list, so that we have the best individuals first
		Collections.reverse(population);
	}

	/**
	 * Evaluates the individuals of the current population, one by one, and stores
	 * their fitness inside their "fitness" field.
	 */
	private void Evaluate_Population()
	{
		for (int i = 0; i < population_size; i++)
		{
			boolean is_solvable = population.get(i).level_state.Is_Level__Solvable();
			if (is_solvable == false)
			{
				population.get(i).fitness = 0;
			}
			else
			{
				double fitness = evaluation_method.Evaluate_Individual(population.get(i));
				population.get(i).fitness = fitness;
			}
		}
	}

	/**
	 * Returns the fitness of the best individual of the current population. Assumes
	 * that the population is already sorted, which occurs automatically at the end
	 * of each generation.
	 * 
	 * @return
	 */
	public double Best_Individual_Fitness()
	{
		return population.get(0).fitness;
	}

	/**
	 * Returns the best individual of the current population (i.e. the one with the
	 * highest fitness).
	 * 
	 * @return
	 */
	public Level_Individual Best_Individual()
	{
		return (Level_Individual) population.get(0).clone();
	}
}
