package Experiments;

import java.util.Random;

import GA.GA;

public class Experiment_Runner
{
	public void Run_Experiment(
			Random rand,
			int num_repetitions,
			GA genetic_algorithm,
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
