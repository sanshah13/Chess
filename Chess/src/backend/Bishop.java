package backend;

import java.util.ArrayList;

/**
 * 
 * @author Sangini Shah
 *
 */
public class Bishop extends Piece
{
	public Bishop(boolean white, int x, int y, Board b)
	{
		isWhite = white;
		pos = new Position(x,y);
		board = b;
		loadValidMoves();
	}
	
	public String toString()
	{
		if(isWhite)
		{
			return "wB";
		}
		else
		{
			return "bB";
		}
	}

	public void loadValidMoves() 
	{
		moves = new ArrayList<Position>();
		Piece[][] p = board.getPieces();
		
		//check NE diagonal
		for(int x = pos.getX() - 1, y = pos.getY() + 1; x >= 0 && y < 8; x--, y++)
		{
			Piece temp = p[x][y];
			if(temp != null)
			{
				if(temp.isWhite != this.isWhite)
				{
					moves.add(new Position(x, y));
				}
				break;
			}
			else if(temp == null)
			{
				moves.add(new Position(x, y));
			}
		}
		
		//check NW diagonal
		for(int x = pos.getX() - 1, y = pos.getY() - 1; x >= 0 && y >= 0; x--, y--)
		{
			Piece temp = p[x][y];
			if(temp != null)
			{
				if(temp.isWhite != this.isWhite)
				{
					moves.add(new Position(x, y));
				}
				break;
			}
			else if(temp == null)
			{
				moves.add(new Position(x, y));
			}
		}
		
		//check SE diagonal
		for(int x = pos.getX() + 1, y = pos.getY() + 1; x < 8 && y < 8; x++, y++)
		{
			Piece temp = p[x][y];
			if(temp != null)
			{
				if(temp.isWhite != this.isWhite)
				{
					moves.add(new Position(x, y));
				}
				break;
			}
			else if(temp == null)
			{
				moves.add(new Position(x, y));
			}
		}
		
		//check SW diagonal
		for(int x = pos.getX() + 1, y = pos.getY() - 1; x < 8 && y >= 0; x++, y--)
		{
			Piece temp = p[x][y];
			if(temp != null)
			{
				if(temp.isWhite != this.isWhite)
				{
					moves.add(new Position(x, y));
				}
				break;
			}
			else if(temp == null)
			{
				moves.add(new Position(x, y));
			}
		}
	}

	
}
