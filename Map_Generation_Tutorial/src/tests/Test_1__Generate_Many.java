package tests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import javax.imageio.ImageIO;

import Core.Coords;
import Core.Level;
import genetic_algorithm.level_generation_methods.LGM__Random__Wall_Probability;
import genetic_algorithm.level_generation_methods.Level_Generation_Method;
import io_utilities.IO_Utilities;
import io_utilities.Level_Drawing_Utilities;

public class Test_1__Generate_Many
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
		String general_output__folder_path = "Tests_Output\\Test_1__Generate_Many__"
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

				IO_Utilities.Save_Level_As_Image(level, level_image_save_path);
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

	public static void Create_Folder(String folder_path) throws IOException
	{
		Path p = Paths.get(folder_path);
		Files.createDirectories(p);
	}
}
