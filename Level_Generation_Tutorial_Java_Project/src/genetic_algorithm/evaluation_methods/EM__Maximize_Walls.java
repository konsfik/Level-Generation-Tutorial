package genetic_algorithm.evaluation_methods;

import genetic_algorithm.Level_Individual;

public class EM__Maximize_Walls extends Evaluation_Method
{

	@Override
	public double Evaluate_Individual(Level_Individual individual)
	{
		int w = individual.level_state.Width();
		int h = individual.level_state.Height();
		
		int num_cells = w * h;
		int num_walls = individual.level_state.Cells__Wall__As_List().size();
		
		double walls_percentage = (double)num_walls / (double)num_cells;
		
		return walls_percentage;
	}

}
