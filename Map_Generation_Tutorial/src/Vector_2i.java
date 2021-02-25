
public class Vector_2i implements Cloneable
{
	public int x;
	public int y;

	public Vector_2i(
			int x,
			int y)
	{
		this.x = x;
		this.y = y;
	}

	public Vector_2i Up()
	{
		return new Vector_2i(x, y + 1);
	}

	public Vector_2i Down()
	{
		return new Vector_2i(x, y - 1);
	}

	public Vector_2i Left()
	{
		return new Vector_2i(x - 1, y);
	}

	public Vector_2i Right()
	{
		return new Vector_2i(x + 1, y);
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
		if (other instanceof Vector_2i == false)
		{
			return false;
		}

		// typecast other to Vector_2i so that we can compare data members
		Vector_2i other_vector = (Vector_2i) other;

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
	 * Private constructor, for deep copy purposes only.
	 * 
	 * @param vector_to_copy
	 */
	private Vector_2i(Vector_2i vector_to_copy)
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
		return new Vector_2i(this);
	}
}
