package genetic_algorithm;

import Core.*;

public class Level_Individual implements Cloneable, Comparable<Level_Individual>
{
	public Level level_state;

	public double fitness;

	public Level_Individual(
			Level level)
	{
		this.level_state = (Level) level.clone();
		this.fitness = 0.0;
	}

	private Level_Individual(Level_Individual level_individual_to_copy)
	{
		this.level_state = (Level) level_individual_to_copy.level_state.clone();
		this.fitness = level_individual_to_copy.fitness;
	}

	@Override
	public Object clone()
	{
		return new Level_Individual(this);
	}

	@Override
	public int compareTo(Level_Individual other)
	{
		if (this.fitness < other.fitness)
		{
			return -1;
		}
		else if (this.fitness > other.fitness)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
}
