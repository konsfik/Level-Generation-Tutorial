package io_utilities;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import core.Level;
import genetic_algorithm.Level_Individual;

public class IO_Utilities
{
	public static void Create_Folder(String folder_path) throws IOException
	{
		Path p = Paths.get(folder_path);
		Files.createDirectories(p);
	}
	
	public static void Save__Level_Individual__As__Image(
			Level_Individual individual,
			String path) throws IOException
	{
		BufferedImage image = Level_Drawing_Utilities.Draw_Level(individual.level_state);
		ImageIO.write(image, "png", new File(path));
		image.flush();
	}
	
	public static void Save__Level_Individual__As__ASCII_Map(
			Level_Individual individual,
			String path) throws IOException
	{
		String ascii_map = individual.level_state.To_ASCII();
		FileWriter ascii_writer = new FileWriter(path);
		ascii_writer.append(ascii_map);
		ascii_writer.close();
	}
	
	public static void Save__Level__As__Image(
			Level level,
			String path) throws IOException
	{
		BufferedImage image = Level_Drawing_Utilities.Draw_Level(level);
		ImageIO.write(image, "png", new File(path));
		image.flush();
	}
	
	public static void Save__Level__As__ASCII_Map(
			Level level,
			String path) throws IOException
	{
		String ascii_map = level.To_ASCII();
		FileWriter ascii_writer = new FileWriter(path);
		ascii_writer.append(ascii_map);
		ascii_writer.close();
	}
	
}
