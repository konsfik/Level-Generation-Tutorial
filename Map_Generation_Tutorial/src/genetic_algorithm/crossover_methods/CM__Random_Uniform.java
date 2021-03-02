package genetic_algorithm.crossover_methods;

import java.util.Random;

import genetic_algorithm.Level_Individual;

public class CM__Random_Uniform extends Crossover_Method
{

	@Override
	public Level_Individual Crossover_Parents(
			Random rand,
			Level_Individual parent_1,
			Level_Individual parent_2)
	{
		Level_Individual offspring = (Level_Individual) parent_1.clone();

		int w = parent_1.level_state.Width();
		int h = parent_1.level_state.Height();

		for (int x = 0; x < w; x++)
		{
			for (int y = 0; y < h; y++)
			{
				double dice_roll = rand.nextDouble();
				if (dice_roll < 0.5)
				{
					offspring.level_state.cells[x][y] = parent_1.level_state.cells[x][y];
				}
				else
				{
					offspring.level_state.cells[x][y] = parent_2.level_state.cells[x][y];
				}
			}
		}

		offspring.fitness = 0.0;
		
		return offspring;
	}

}
