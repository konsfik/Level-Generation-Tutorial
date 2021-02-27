import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Core.*;
import GA.*;

public class Main
{
	public static void main(String[] args)
	{
		
		Random rand = new Random();
		
		LGM__Random generator = new LGM__Random();
		
		Level random_level = generator.Generate_Level(
				rand, 
				10, 
				10, 
				new Coords(0, 0),
				new Coords(9, 9)
				);
		
		String ascii_map = IO_Utilities.Convert_Level_To_ASCII(random_level);
		
		System.out.print(ascii_map);
		
		Level level = new Level(
				10,
				10,
				new Coords(0, 0),
				new Coords(9, 9));
		
		
		for (int i = 0; i < 10; i++)
		{
			if (i != 9)
			{
				level.Set_Wall(5, i);
			}
		}
		
		for (int i = 0; i < 10; i++)
		{
			if (i != 0)
			{
				level.Set_Wall(7, i);
			}
		}
		
		
		ascii_map = IO_Utilities.Convert_Level_To_ASCII(level);
		System.out.print(ascii_map);
		ArrayList<Coords> path = Level_Utilities.Shortest_Path__BFS(
				level, 
				level.entrance, 
				level.exit
				);
		
		System.out.print(path.size());
		
		boolean ok = Level_Utilities.Path_Exists(level, level.entrance, level.exit);

		if (ok)
		{
			System.out.print("yes");
		}
		else
		{
			System.out.print("no");
		}
		
		BufferedImage img = new BufferedImage(500, 400, BufferedImage.TYPE_3BYTE_BGR);
		
		Graphics2D gr = img.createGraphics();
		
//		gr.drawLine(x1, y1, x2, y2);
		gr.dispose();
		
		

	}
}
