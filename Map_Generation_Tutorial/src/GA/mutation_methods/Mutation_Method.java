package GA.mutation_methods;

import java.util.Random;

import GA.Level_Individual;

public abstract class Mutation_Method
{
	public abstract void Mutate_Individual(
			Random rand,
			Level_Individual individual);
}
