package Experiments;

import java.util.Random;

import genetic_algorithm.Genetic_Algorithm;

public class Experiment_Runner
{
	public void Run_Experiment(
			Random rand,
			int num_repetitions,
			Genetic_Algorithm genetic_algorithm,
			int num_generations,
			int log_rate,
			int image_rate
			)
	{

		for (int rep = 0; rep < num_repetitions; rep++)
		{
			genetic_algorithm.Initialize_Population(rand);
		}

	}
}
