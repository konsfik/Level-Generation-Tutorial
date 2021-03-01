package Experiments;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Core.Coords;
import Core.Level;
import Core.Level_Utilities;

public class Level_Drawing_Utilities
{
	public static BufferedImage Draw_Level(
			Level level)
	{

		int cell_size = 32;

		int w = level.Width();
		int h = level.Height();

		BufferedImage img = new BufferedImage(
				w * cell_size,
				h * cell_size,
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D gr = img.createGraphics();

		for (int y = 0; y < h; y++)
		{
			for (int x = 0; x < w; x++)
			{

				if (level.Is_Cell__Floor(x, y))
				{
					gr.setColor(Color.WHITE);
					gr.fillRect(
							x * cell_size,
							y * cell_size,
							cell_size,
							cell_size);

				}
				else
				{
					gr.setColor(Color.BLACK);
					gr.fillRect(
							x * cell_size,
							y * cell_size,
							cell_size,
							cell_size);
				}

			}
		}

		gr.setColor(Color.GREEN);
		gr.setStroke(new BasicStroke(2));
		gr.drawRect(
				level.entrance.x * cell_size,
				level.entrance.y * cell_size,
				cell_size,
				cell_size);
		gr.setColor(Color.RED);
		gr.drawRect(
				level.exit.x * cell_size,
				level.exit.y * cell_size,
				cell_size,
				cell_size);

		// draw the path...

		ArrayList<Coords> solution_path = Level_Utilities.Shortest_Path__BFS(
				level,
				level.entrance,
				level.exit);

		gr.setColor(Color.RED);
		gr.setStroke(new BasicStroke(6));
		for (int i = 0; i < solution_path.size() - 1; i++)
		{
			Coords p1 = solution_path.get(i);
			Coords p2 = solution_path.get(i + 1);

			gr.drawLine(
					p1.x * cell_size + cell_size / 2,
					p1.y * cell_size + cell_size / 2,
					p2.x * cell_size + cell_size / 2,
					p2.y * cell_size + cell_size / 2);
		}

		gr.dispose();

		return img;
	}
}
