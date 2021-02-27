package GA.level_generation_methods;

import java.util.Random;
import Core.*;

public abstract class Level_Generation_Method
{
	int level_width;
	int level_height;
	Coords entrance;
	Coords exit;

	public Level_Generation_Method(
			int level_width,
			int level_height,
			Coords entrance,
			Coords exit)
	{
		this.level_width = level_width;
		this.level_height = level_height;
		this.entrance = (Coords) entrance.clone();
		this.exit = (Coords) exit.clone();
	}

	public abstract Level Generate_Level(Random rand);
}
