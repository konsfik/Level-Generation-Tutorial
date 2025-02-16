package genetic_algorithm.evaluation_methods;

import genetic_algorithm.Level_Individual;

public class EM__Maximize_Solution_Path extends Evaluation_Method
{

	@Override
	public double Evaluate_Individual(Level_Individual individual)
	{

		int path_length = individual.level_state.Path_Length(
				individual.level_state.entrance,
				individual.level_state.exit);

		if (path_length == -1)
		{
			return 0;
		}
		else
		{
			// approximation of the maximum possible solution path length
			int w = individual.level_state.Width();
			int h = individual.level_state.Height();
			int max_path_length = w * h - ((w - 1) * (h - 1) / 2);

			// score calculation
			double score = (double) path_length / (double) max_path_length;
			return score;
		}
	}

}
