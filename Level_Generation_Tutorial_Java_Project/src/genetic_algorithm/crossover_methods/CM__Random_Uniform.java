package genetic_algorithm.crossover_methods;

import java.util.Random;

import genetic_algorithm.Level_Individual;

/**
 * This method generates an offspring initially as a copy of one of the two parents.
 * Then, it iterates over its cells and for each cell:
 * 		it selects one of the two parents at random,
 * 		and copies the cell value of that parent to its cell
 * Thus, the resulting level is a mixture of the parents.
 * @author kostas
 *
 */
public class CM__Random_Uniform extends Crossover_Method
{

	@Override
	public Level_Individual Combine_Parents(
			Random rand,
			Level_Individual parent_1,
			Level_Individual parent_2)
	{
		// initialize the offspring as a clone of parent 1
		Level_Individual offspring = (Level_Individual) parent_1.clone();

		int w = parent_1.level_state.Width();
		int h = parent_1.level_state.Height();

		for (int x = 0; x < w; x++)
		{
			for (int y = 0; y < h; y++)
			{
				double dice_roll = rand.nextDouble();
				// if the dice roll is smaller than 0.5 then 
				if (dice_roll < 0.5)
				{
					offspring.level_state.cells[x][y] = parent_2.level_state.cells[x][y];
				}
			}
		}
		
		// set the offspring fitness to 0, it will be actually evaluated later on.
		offspring.fitness = 0.0;
		
		return offspring;
	}

}
