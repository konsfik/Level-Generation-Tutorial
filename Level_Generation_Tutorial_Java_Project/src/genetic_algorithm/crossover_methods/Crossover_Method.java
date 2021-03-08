package genetic_algorithm.crossover_methods;

import java.util.Random;

import genetic_algorithm.Level_Individual;

public abstract class Crossover_Method
{
	public abstract Level_Individual Combine_Parents(
			Random rand,
			Level_Individual parent_1,
			Level_Individual parent_2
			);
}
