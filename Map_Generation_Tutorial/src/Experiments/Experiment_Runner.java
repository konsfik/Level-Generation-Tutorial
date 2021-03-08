package Experiments;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import Core.*;
import genetic_algorithm.*;
import genetic_algorithm.level_generation_methods.*;
import genetic_algorithm.parent_selection_methods.*;
import genetic_algorithm.crossover_methods.*;
import genetic_algorithm.mutation_methods.*;
import genetic_algorithm.evaluation_methods.*;
import io_utilities.IO_Utilities;

public class Experiment_Runner
{
	/**
	 * Main method that sets up and runs an experiment. One needs to define the
	 * following modules: 1) Level Generation Method 2) Parent Selection Method 3)
	 * Crossover Method 4) Mutation Method 5) Evaluation Method The rest of the GA
	 * settings: - population size - elitism size - number of generations Plus some
	 * more experiment settings: - number of experiment repetitions - save images
	 * (boolean) - save ascii maps (boolean) - save rate (for images and ascii maps)
	 * 
	 * Once all of these settings have been defined, the algorithm will run an
	 * experiment and save the relevant data on disk.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		Random rand = new Random();

		/**
		 * 1) Level Generation Method: This method is responsible for generating the
		 * initial population.
		 */
		Level_Generation_Method level_generation_method = new LGM__Random__Wall_Probability(
				51,
				51,
				new Coords(0, 10),
				new Coords(50, 10),
				0.2);

		/*
		 * 2) Parent selection method: This method is responsible for selecting the
		 * parent(s) which will give birth to the offspring for the next genertion.
		 */
		int tournament_size = 3;
		Parent_Selection_Method parent_selection_method = new PSM__Tournament(tournament_size);

		/**
		 * 3) Crossover method: This method is responsible for combining a pair of
		 * individuals and producing an offspring.
		 */
		Crossover_Method crossover_method = new CM__Random_Uniform();

		/**
		 * 4) Mutation method: This method will be responsible for mutating an
		 * offspring.
		 */
		Mutation_Method mutation_method = new MM__Flip_Random_Cells__Probability(0.02);

		/*
		 * 5) Evaluation Method: This method will be responsible for calculating the
		 * fitness of each individual.
		 */

		Evaluation_Method evaluation_method = new EM__Maximize_Solution_Path();

		/*
		 * Define the rest of the genetic algorithm's settings:
		 */
		int population_size = 20;
		int elitism_size = 2;
		int number_of_generations = 500;

		/**
		 * Define the rest of the experiment's settings:
		 */
		int experiment_repetitions = 10;
		boolean save_images = true;
		boolean save_ascii_maps = true;
		int save_rate = 50;

		/*
		 * Put the parts together, defining a fully functional genetic algorithm:
		 */
		Genetic_Algorithm genetic_algorithm = new Genetic_Algorithm(
				level_generation_method,
				parent_selection_method,
				crossover_method,
				mutation_method,
				evaluation_method,
				population_size,
				elitism_size);
		
		/**
		 * Actually run the experiment
		 */
		Run_Experiment(
				rand,
				genetic_algorithm,
				number_of_generations,
				experiment_repetitions,
				save_images,
				save_ascii_maps,
				save_rate);

	}
	
	/**
	 * Runs the experiment.
	 * @param rand
	 * @param genetic_algorithm
	 * @param number_of_generations
	 * @param experiment_repetitions
	 * @param save_images
	 * @param save_ascii_maps
	 * @param save_rate
	 * @throws IOException
	 */
	public static void Run_Experiment(
			Random rand,
			Genetic_Algorithm genetic_algorithm,
			int number_of_generations,
			int experiment_repetitions,
			boolean save_images,
			boolean save_ascii_maps,
			int save_rate) throws IOException
	{
		String general_output__folder_path = "Experiments_Output\\Experiment_"
				+ Long.toString(System.currentTimeMillis());
		IO_Utilities.Create_Folder(general_output__folder_path);

		for (int rep = 0; rep < experiment_repetitions; rep++)
		{
			String current_repetition__folder_path = general_output__folder_path
					+ "\\run_"
					+ Integer.toString(rep);
			IO_Utilities.Create_Folder(current_repetition__folder_path);

			String data_log__file_path = current_repetition__folder_path + "\\log.csv";
			FileWriter data_log__writer = new FileWriter(data_log__file_path);

			data_log__writer.append("generation,best_fitness\n");

			genetic_algorithm.Initialize_Population(rand);

			if (save_images)
			{
				// save the best individual of the initial population as an image
				Level_Individual best_individual = genetic_algorithm.Best_Individual();
				String image_path = current_repetition__folder_path
						+ "\\generation_"
						+ Integer.toString(0)
						+ ".png";

				IO_Utilities.Save__Level_Individual__As__Image(best_individual, image_path);
			}
			if (save_ascii_maps)
			{
				// save the best individual of the initial population as an ascii map
				Level_Individual best_individual = genetic_algorithm.Best_Individual();
				String ascii_map = best_individual.level_state.To_ASCII();

				String ascii_map_save_path = current_repetition__folder_path
						+ "\\generation_"
						+ Integer.toString(0)
						+ ".txt";

				FileWriter ascii_writer = new FileWriter(ascii_map_save_path);
				ascii_writer.append(ascii_map);
				ascii_writer.close();
			}

			for (int generation = 1; generation <= number_of_generations; generation++)
			{
				// advance the ga by one step
				genetic_algorithm.Run_One_Step(rand);

				// console log
				double best_fitness = genetic_algorithm.Best_Individual_Fitness();
				System.out.println(
						"generation: " + Integer.toString(generation) +
								" | " +
								"best fitness: " + Double.toString(best_fitness));

				// save data log
				data_log__writer.append(
						Integer.toString(generation)
								+ ","
								+ Double.toString(best_fitness)
								+ "\n");

				// save best individual image && ascii map
				if (generation % save_rate == 0 || generation == number_of_generations)
				{
					if (save_images)
					{
						Level_Individual best_individual = genetic_algorithm.Best_Individual();
						String image_path = current_repetition__folder_path
								+ "\\generation_"
								+ Integer.toString(generation)
								+ ".png";
						IO_Utilities.Save__Level_Individual__As__Image(best_individual, image_path);
					}

					if (save_ascii_maps)
					{
						Level_Individual best_individual = genetic_algorithm.Best_Individual();
						String ascii_map_save_path = current_repetition__folder_path
								+ "\\generation_"
								+ Integer.toString(generation)
								+ ".txt";
						IO_Utilities.Save__Level_Individual__As__ASCII_Map(best_individual, ascii_map_save_path);
					}
				}
			}
			data_log__writer.close();
		}
	}

}
