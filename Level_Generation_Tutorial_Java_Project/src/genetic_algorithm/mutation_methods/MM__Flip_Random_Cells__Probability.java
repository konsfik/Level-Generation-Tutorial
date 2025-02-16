package genetic_algorithm.mutation_methods;

import java.util.Random;

import genetic_algorithm.Level_Individual;

/**
 * Mutation method that operates by simply flipping the value of cells
 * (converting wall to floor and vice versa). The method iterates over all the
 * cells of the level either flips them or not, based on a user defined
 * probability.
 * 
 * @author Konstantinos Sfikas
 *
 */
public class MM__Flip_Random_Cells__Probability extends Mutation_Method
{
	public double flip_probability;

	public MM__Flip_Random_Cells__Probability(double flip_probability)
	{
		this.flip_probability = flip_probability;
	}

	@Override
	public void Mutate_Individual(Random rand, Level_Individual individual)
	{
		// TODO Auto-generated method stub
		int w = individual.level_state.Width();
		int h = individual.level_state.Height();

		for (int x = 0; x < w; x++)
		{
			for (int y = 0; y < h; y++)
			{
				double dice_roll = rand.nextDouble();
				if (dice_roll < flip_probability)
				{
					// perform the flip here
					if (individual.level_state.Is_Cell__Floor(x, y))
					{
						individual.level_state.Set_Wall(x, y);
					}
					else
					{
						individual.level_state.Set_Floor(x, y);
					}
				}
			}
		}
	}

}
