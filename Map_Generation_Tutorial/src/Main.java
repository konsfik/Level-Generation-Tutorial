import java.util.ArrayList;

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
		
		boolean ok = Level_Utilities.Cells_Reachable(map, map.entrance, map.exit);

		if (ok)
		{
			System.out.print("yes");
		}
		else
		{
			System.out.print("no");
		}

	}
}
