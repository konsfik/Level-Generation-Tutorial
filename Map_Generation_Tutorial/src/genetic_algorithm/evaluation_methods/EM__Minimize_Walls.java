package genetic_algorithm.evaluation_methods;

import genetic_algorithm.Level_Individual;

public class EM__Minimize_Walls extends Evaluation_Method
{

	@Override
	public double Evaluate_Individual(Level_Individual individual)
	{
		int w = individual.level_state.Width();
		int h = individual.level_state.Height();
		
		int num_cells = w * h;
		int num_walls = individual.level_state.Wall_Cells__As_List().size();
		
		double walls_percentage = (double)num_walls / (double)num_cells;
		double score = 1 - walls_percentage;
		
		return score;
	}

}
