package Core;

public class Coords implements Cloneable
{
	public int x;
	public int y;

	public Coords(
			int x,
			int y)
	{
		this.x = x;
		this.y = y;
	}

	public Coords Up()
	{
		return new Coords(x, y + 1);
	}

	public Coords Down()
	{
		return new Coords(x, y - 1);
	}

	public Coords Left()
	{
		return new Coords(x - 1, y);
	}

	public Coords Right()
	{
		return new Coords(x + 1, y);
	}

	// Overriding equals() to compare two Vector_2i objects
	@Override
	public boolean equals(Object other)
	{

		// If the object is compared with itself then return true
		if (other == this)
		{
			return true;
		}

		/*
		 * Check if o is an instance of Vector_2i or not.
		 * "null instanceof [type]" also returns false
		 */
		if (other instanceof Coords == false)
		{
			return false;
		}

		// typecast other to Vector_2i so that we can compare data members
		Coords other_vector = (Coords) other;

		// Compare the data members and return accordingly
		return 
				this.x == other_vector.x 
				&& 
				this.y == other_vector.y;
	}
	
	//Idea from effective Java : Item 9
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

	/**
	 * Private constructor, to be used by the clone() method.
	 * 
	 * @param vector_to_copy
	 */
	private Coords(Coords vector_to_copy)
	{
		this.x = vector_to_copy.x;
		this.y = vector_to_copy.y;
	}

	/**
	 * returns a deep copy of this vector.
	 */
	@Override
	public Object clone()
	{
		return new Coords(this);
	}
}
