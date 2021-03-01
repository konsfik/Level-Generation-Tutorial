package genetic_algorithm.evaluation_methods;

import Core.Level_Utilities;
import genetic_algorithm.Level_Individual;

public class EM__Maximize_Solution_Path extends Evaluation_Method
{

	@Override
	public double Evaluate_Individual(Level_Individual individual)
	{

		int path_length = Level_Utilities.Distance_Between_Cells(
				individual.level_state,
				individual.level_state.entrance,
				individual.level_state.exit);

		if (path_length == -1)
		{
			return 0;
		}
		else
		{
			int w = individual.level_state.Width();
			int h = individual.level_state.Height();
			int max_length = w * h - ((w - 1) * (h - 1) / 2);
			double score = (double) path_length / (double) max_length;
			return score;
		}
	}

}
