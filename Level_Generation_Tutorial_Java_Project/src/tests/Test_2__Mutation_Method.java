package tests;

import java.io.IOException;
import java.util.Random;

import core.Coords;
import core.Level;
import genetic_algorithm.Level_Individual;
import genetic_algorithm.level_generation_methods.LGM__Random__Wall_Probability;
import genetic_algorithm.level_generation_methods.Level_Generation_Method;
import genetic_algorithm.mutation_methods.MM__Flip_Random_Cells__Probability;
import genetic_algorithm.mutation_methods.Mutation_Method;
import io_utilities.IO_Utilities;

public class Test_2__Mutation_Method
{
	public static void main(String[] args) throws IOException
	{
		Random rand = new Random();
		
		/**
		 * Create an output folder for this test
		 */
		String general_output__folder_path = "Experiments_Output\\Test_2__Mutation_Method__"
				+ Long.toString(System.currentTimeMillis());
		IO_Utilities.Create_Folder(general_output__folder_path);

		/**
		 * Settings
		 */
		int num_mutations = 1000;
		boolean save_images = true;
		boolean save_ascii_maps = false;

		/**
		 * Level Generation Method: This method is responsible for generating the
		 * initial population.
		 */
		Level_Generation_Method level_generation_method = new LGM__Random__Wall_Probability(
				21,
				21,
				new Coords(0, 10),
				new Coords(20, 10),
				0.2);
		
		/**
		 * Define a mutation method which will be tested
		 */
		Mutation_Method mutation_method = new MM__Flip_Random_Cells__Probability(0.05);
		
		/**
		 * Generate an initial individual
		 */
		Level initial_level = level_generation_method.Generate_Level(rand);
		
		Level_Individual level_individual= new Level_Individual(initial_level);
		
		/**
		 * Save the initial individual
		 */
		if (save_images == true)
		{
			String level_image_save_path = general_output__folder_path
					+ "\\level"
					+ Integer.toString(0)
					+ ".png";

			IO_Utilities.Save__Level_Individual__As__Image(
					level_individual, 
					level_image_save_path);
		}
		if (save_ascii_maps == true)
		{
			String level_ASCII_map_save_path = general_output__folder_path
					+ "\\level"
					+ Integer.toString(0)
					+ ".txt";
			IO_Utilities.Save__Level_Individual__As__ASCII_Map(
					level_individual, 
					level_ASCII_map_save_path);
		}
		
		/**
		 * Mutate the individual a number of times and save the data to disk.
		 */
		for (int i = 1; i <= num_mutations; i++)
		{
			// use the mutation method to mutate the individual
			mutation_method.Mutate_Individual(rand, level_individual);
			
			/**
			 * Save the mutated individual
			 */
			if (save_images == true)
			{
				String level_image_save_path = general_output__folder_path
						+ "\\level"
						+ Integer.toString(i)
						+ ".png";

				IO_Utilities.Save__Level_Individual__As__Image(
						level_individual, 
						level_image_save_path);
			}
			if (save_ascii_maps == true)
			{
				String level_ASCII_map_save_path = general_output__folder_path
						+ "\\level"
						+ Integer.toString(i)
						+ ".txt";
				IO_Utilities.Save__Level_Individual__As__ASCII_Map(
						level_individual, 
						level_ASCII_map_save_path);
			}
		}
	}
}
