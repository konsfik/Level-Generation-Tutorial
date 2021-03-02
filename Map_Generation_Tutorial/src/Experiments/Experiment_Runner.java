package Experiments;

import java.awt.image.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import Core.*;
import genetic_algorithm.Genetic_Algorithm;
import genetic_algorithm.Level_Individual;
import genetic_algorithm.crossover_methods.CM__Random_Uniform;
import genetic_algorithm.crossover_methods.Crossover_Method;
import genetic_algorithm.evaluation_methods.EM__Average;
import genetic_algorithm.evaluation_methods.EM__Maximize_Solution_Path;
import genetic_algorithm.evaluation_methods.EM__Maximize_Walls;
import genetic_algorithm.evaluation_methods.EM__Minimize_Walls;
import genetic_algorithm.evaluation_methods.Evaluation_Method;
import genetic_algorithm.level_generation_methods.LGM__Random__Wall_Probability;
import genetic_algorithm.level_generation_methods.Level_Generation_Method;
import genetic_algorithm.mutation_methods.MM__Flip_Random_Cells__Probability;
import genetic_algorithm.mutation_methods.Mutation_Method;
import genetic_algorithm.parent_selection_methods.PSM__Roulette_Wheel_Selection;
import genetic_algorithm.parent_selection_methods.Parent_Selection_Method;

public class Experiment_Runner
{
	public static void main(String[] args) throws IOException
	{
		long current_millis = System.currentTimeMillis();
		String output_folder = "Experiments_Output\\Experiment_" + Long.toString(current_millis);
		Path output_folder__path = Paths.get(output_folder);
		// if does not exist?
		Files.createDirectories(output_folder__path);

		Random rand = new Random();

		/**
		 * Select a level generation method. This method will be responsible only for
		 * generating the initial population.
		 */
		Level_Generation_Method level_generation_method = new LGM__Random__Wall_Probability(
				21,
				21,
				new Coords(0, 10),
				new Coords(20, 10),
				0.2);

		/*
		 * Select a parent selection method. This method is responsible for selecting
		 * the parent(s) which will give birth to the offspring for the next genertion.
		 */
		Parent_Selection_Method parent_selection_method = new PSM__Roulette_Wheel_Selection();

		Crossover_Method crossover_method = new CM__Random_Uniform();

		/**
		 * Select a mutation method. This method will be responsible for mutating the
		 * offspring.
		 */
		Mutation_Method mutation_method = new MM__Flip_Random_Cells__Probability(0.02);

		/*
		 * Define an evaluation method: This method will be responsible for calculating
		 * the fitness of each individual.
		 */

//		ArrayList<Evaluation_Method> sub_methods = new ArrayList<Evaluation_Method>();
//		sub_methods.add(new EM__Maximize_Solution_Path());
//		sub_methods.add(new EM__Maximize_Walls());

		Evaluation_Method evaluation_method = new EM__Maximize_Solution_Path();

		/*
		 * Define the rest of the genetic algorithm's settings:
		 */
		int population_size = 100;
		int elitism_size = 5;
		int number_of_generations = 5000;

		int experiment_repetitions = 10;
		int image_save_rate = 500;
		int ascii_map_save_rate = 500;

		/*
		 * Put the parts together, defining a fully functional genetic algorithm:
		 */
		Genetic_Algorithm ga = new Genetic_Algorithm(
				level_generation_method,
				parent_selection_method,
				crossover_method,
				mutation_method,
				evaluation_method,
				population_size,
				elitism_size);

		Run_Experiment(
				rand,
				ga,
				number_of_generations,
				experiment_repetitions,
				image_save_rate,
				ascii_map_save_rate);

	}

	public static void Run_Experiment(
			Random rand,
			Genetic_Algorithm ga,
			int number_of_generations,
			int experiment_repetitions,
			int image_save_rate,
			int ascii_map_save_rate) throws IOException
	{
		String general_output__folder_path = "Experiments_Output\\Experiment_"
				+ Long.toString(System.currentTimeMillis());
		Create_Folder(general_output__folder_path);

		for (int rep = 0; rep < experiment_repetitions; rep++)
		{
			String current_repetition__folder_path = general_output__folder_path + "\\run_" + Integer.toString(rep);
			Create_Folder(current_repetition__folder_path);

			String data_log__file_path = current_repetition__folder_path + "\\log.csv";
			FileWriter data_log__writer = new FileWriter(data_log__file_path);

			data_log__writer.append("generation,best_fitness\n");

			ga.Initialize_Population(rand);

			Level_Individual best_individual = ga.Best_Individual();
			Path image_path = Paths.get(
					current_repetition__folder_path,
					"generation_" + Integer.toString(number_of_generations) + ".png");
			Save_Level_As_Image(best_individual, image_path);

			for (int generation = 0; generation < number_of_generations; generation++)
			{
				ga.Run_One_Step(rand);

				double best_fitness = ga.Best_Individual_Fitness();
				System.out.println(
						"generation: " + Integer.toString(generation) +
								" | " +
								"best fitness: " + Double.toString(best_fitness));

				data_log__writer.append(
						Integer.toString(generation)
								+ ","
								+ Double.toString(best_fitness)
								+ "\n");

				if (generation % image_save_rate == 0)
				{
					best_individual = ga.Best_Individual();
					image_path = Paths.get(
							current_repetition__folder_path,
							"generation_" + Integer.toString(generation) + ".png");
					Save_Level_As_Image(best_individual, image_path);
				}
				if (generation % ascii_map_save_rate == 0)
				{
					best_individual = ga.Best_Individual();

					String ascii_map = best_individual.level_state.To_ASCII();

					String ascii_map_save_path = current_repetition__folder_path
							+ "\\generation_" + Integer.toString(generation) + ".txt";
					;

					FileWriter ascii_writer = new FileWriter(ascii_map_save_path);
					ascii_writer.append(ascii_map);
					ascii_writer.close();
				}

			}

			best_individual = ga.Best_Individual();
			image_path = Paths.get(current_repetition__folder_path,
					"generation_" + Integer.toString(number_of_generations) + ".png");
			Save_Level_As_Image(best_individual, image_path);

			data_log__writer.close();
		}
	}

	public static void Create_Folder(String folder_path) throws IOException
	{
		Path p = Paths.get(folder_path);
		Files.createDirectories(p);
	}

	public static void Save_Level_As_Image(
			Level_Individual individual,
			Path path) throws IOException
	{
		BufferedImage image = Level_Drawing_Utilities.Draw_Level(individual.level_state);
		ImageIO.write(image, "png", new File(path.toString()));
		image.flush();
	}

}
