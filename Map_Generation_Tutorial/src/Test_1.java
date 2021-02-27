import java.awt.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import Core.Coords;
import Core.Level;
import Core.Level_Utilities;
import GA.GA;
import GA.Level_Individual;
import GA.evaluation_methods.EM__Maximize_Solution_Path;
import GA.evaluation_methods.Evaluation_Method;
import GA.level_generation_methods.LGM__Random;
import GA.level_generation_methods.LGM__Random__Wall_Probability;
import GA.level_generation_methods.Level_Generation_Method;
import GA.mutation_methods.MM__Flip_Random_Cells__Probability;
import GA.mutation_methods.Mutation_Method;
import GA.parent_selection_methods.PSM__Roulette_Wheel_Selection;
import GA.parent_selection_methods.Parent_Selection_Method;

public class Test_1
{
	public static void main(String[] args) throws IOException
	{
		long current_millis = System.currentTimeMillis();
		String output_folder_name = "Experiment_" + Long.toString(current_millis);
		Path output_folder_path = Paths.get(output_folder_name);
		// if does not exist?
		Files.createDirectories(output_folder_path);
		
		
		
		
		Random rand = new Random();

		Level_Generation_Method level_generation_method = new LGM__Random__Wall_Probability(
				21,
				21,
				new Coords(10, 10),
				new Coords(12, 10),
				0.2);

		Parent_Selection_Method parent_selection_method = new PSM__Roulette_Wheel_Selection();

		Mutation_Method mutation_method = new MM__Flip_Random_Cells__Probability(0.02);

		Evaluation_Method evaluation_method = new EM__Maximize_Solution_Path();

		int population_size = 100;
		int elitism_size = 5;
		int number_of_generations = 500;
		
		GA ga = new GA(
				level_generation_method,
				parent_selection_method,
				mutation_method,
				evaluation_method,
				population_size,
				elitism_size);

		
		for(int rep = 0; rep < 10; rep++) {
			String current_rep_folder_name = "run_" + Integer.toString(rep);
			Path current_rep_folder_path = Paths.get(output_folder_name, current_rep_folder_name);
			// if does not exist?
			Files.createDirectories(current_rep_folder_path);
			
			ga.Initialize_Population(rand);
			
			for (int i = 0; i < number_of_generations; i++)
			{
				ga.Run_One_Step(rand);

				double best_fitness = ga.Best_Individual_Fitness();
				System.out.println(
						"generation: " + Integer.toString(i) +
								" | " +
								"best fitness: " + Double.toString(best_fitness));

				Level_Individual ind = ga.Best_Individual();
				BufferedImage img = Level_Drawing_Utilities.Draw_Level(ind.level_state);
				
				String image_name = "image_" + Integer.toString(i) + ".png";
				Path image_path = Paths.get(output_folder_name,current_rep_folder_name,image_name);
				
				ImageIO.write(img, "png", new File(image_path.toString()));
			}
		}
		

	}

	public static void Run_Experiment()
	{

	}

}
