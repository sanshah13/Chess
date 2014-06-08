package backend;
/**
 * 
 * @author Sangini Shah
 *
 */
public class Position 
{
	int x, y;
	
	public Position(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public boolean equals(Object o)
	{
		if(o == null || !(o instanceof Position))
		{
			return false;
		}
		
		Position p = (Position)o;
		return (this.x == p.x && this.y == p.y);
	}
	
	public String toString()
	{
		return (char)(y+97) + Integer.toString(8 - x);
		
	}
}
