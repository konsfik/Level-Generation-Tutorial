import java.util.ArrayList;

public class GA
{
	public ArrayList<Map_Individual> population;
	
	
	GA()
	{
		
	}
	
	public void Run(int num_steps)
	{
		for (int i = 0; i < num_steps; i++)
		{
			Run_One_Step();
		}
	}

	public void Run_One_Step()
	{
		// 
		ArrayList<Map_Individual> new_population = new ArrayList<Map_Individual>();
		
		// elitism?
		
	}
	
	public Map_Individual Best_Individual() {
		// not implemented yet
		return null;
		
	}
}
