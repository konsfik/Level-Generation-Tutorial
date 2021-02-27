package GA;

import java.util.Random;

public abstract class Mutation_Method
{
	abstract void Mutate_Individual(
			Random rand,
			Level_Individual individual);
}
