package genetic_algorithm.mutation_methods;

import java.util.Random;

import genetic_algorithm.Level_Individual;

/**
 * Abstract class that serves as a parent for all mutation methods. Mutation
 * methods are used in the context of the Genetic_Algorithm class.
 * 
 * @author kostas
 *
 */
public abstract class Mutation_Method
{
	public abstract void Mutate_Individual(
			Random rand,
			Level_Individual individual);
}
