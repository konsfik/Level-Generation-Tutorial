import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Core.*;
import GA.*;

public class Main
{
	public static void main(String[] args)
	{
		Level map = new Level(
				10,
				10,
				new Coords(0, 0),
				new Coords(9, 9));

		for (int i = 0; i < 10; i++)
		{
			if (i != 9)
			{
				map.Ï__Set_Cell__Wall(5, i);
			}
		}
		
		for (int i = 0; i < 10; i++)
		{
			if (i != 0)
			{
				map.Ï__Set_Cell__Wall(7, i);
			}
		}
		
		ArrayList<Coords> path = Level_Utilities.Shortest_Path__BFS(
				map, 
				map.entrance, 
				map.exit
				);
		
		System.out.print(path.size());
		
		boolean ok = Level_Utilities.Path_Exists(map, map.entrance, map.exit);

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
