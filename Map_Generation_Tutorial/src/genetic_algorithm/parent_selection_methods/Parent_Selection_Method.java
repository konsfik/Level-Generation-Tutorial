package genetic_algorithm.parent_selection_methods;

import java.util.ArrayList;
import java.util.Random;

import genetic_algorithm.Level_Individual;

public abstract class Parent_Selection_Method
{
	public abstract Level_Individual Select_Parent__Return_Clone(
			Random rand,
			ArrayList<Level_Individual> population
			);
}