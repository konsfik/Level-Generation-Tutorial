package GA;

import java.util.Random;
import Core.*;

public abstract class Level_Generation_Method
{
	public abstract Level Generate_Level(
			Random rand,
			int width, 
			int height, 
			Coords entrance,
			Coords exit
			);
}
