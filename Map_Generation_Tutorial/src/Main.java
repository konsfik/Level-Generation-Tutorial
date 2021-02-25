import java.util.ArrayList;

public class Main
{
	public static void main(String[] args)
	{
		Map_State map = new Map_State(
				10,
				10,
				new Vector_2i(0, 0),
				new Vector_2i(9, 9));

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
		
		ArrayList<Vector_2i> path = Map_Utilities.Shortest_Path__BFS(
				map, 
				map.entrance, 
				map.exit
				);
		
		System.out.print(path.size());
		
		boolean ok = Map_Utilities.Cells_Reachable(map, map.entrance, map.exit);

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
