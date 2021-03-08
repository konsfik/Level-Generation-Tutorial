package tests;

import java.io.IOException;
import java.util.Random;

import core.Coords;
import core.Level;
import genetic_algorithm.level_generation_methods.LGM__Random__Wall_Probability;
import genetic_algorithm.level_generation_methods.Level_Generation_Method;
import io_utilities.IO_Utilities;

public class Test_1__Generation_Method
{
	public static void main(String[] args) throws IOException
	{
		Random rand = new Random();

		// test - settings
		int num_levels_to_generate = 100;
		boolean save_images = true;
		boolean save_ascii_maps = true;

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

		// create an output folder
		String general_output__folder_path = "Experiments_Output\\Test_1__Generation_Method__"
				+ Long.toString(System.currentTimeMillis());
		IO_Utilities.Create_Folder(general_output__folder_path);

		for (int i = 0; i < num_levels_to_generate; i++)
		{
			Level level = level_generation_method.Generate_Level(rand);

			if (save_images == true)
			{
				String level_image_save_path = general_output__folder_path
						+ "\\level"
						+ Integer.toString(i)
						+ ".png";

				IO_Utilities.Save__Level__As__Image(level, level_image_save_path);
			}
			if (save_ascii_maps == true)
			{
				String level_ASCII_map_save_path = general_output__folder_path
						+ "\\level"
						+ Integer.toString(i)
						+ ".txt";
				IO_Utilities.Save__Level__As__ASCII_Map(level, level_ASCII_map_save_path);
			}
		}
	}
}
