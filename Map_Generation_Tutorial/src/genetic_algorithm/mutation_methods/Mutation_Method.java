package genetic_algorithm.mutation_methods;

import java.util.Random;

import genetic_algorithm.Level_Individual;

public abstract class Mutation_Method
{
	public abstract void Mutate_Individual(
			Random rand,
			Level_Individual individual);
}
