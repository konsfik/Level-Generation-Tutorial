package genetic_algorithm.evaluation_methods;

import java.util.ArrayList;

import genetic_algorithm.Level_Individual;

public class EM__Average extends Evaluation_Method
{

	public ArrayList<Evaluation_Method> methods;

	public EM__Average(ArrayList<Evaluation_Method> methods)
	{
		this.methods = methods;
	}

	@Override
	public double Evaluate_Individual(Level_Individual individual)
	{

		double score_sum = 0;
		for (Evaluation_Method m : methods)
		{
			score_sum += m.Evaluate_Individual(individual);
		}
		
		return score_sum / (double)methods.size();
	}

}
