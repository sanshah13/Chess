package backend;

import java.util.ArrayList;

/**
 * 
 * @author Sangini Shah
 *
 */
public class Knight extends Piece
{

	public Knight(boolean white, int x, int y, Board b)
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
			return "wN";
		}
		else
		{
			return "bN";
		}
	}

	public void loadValidMoves() 
	{
		moves = new ArrayList<Position>();
		Piece[][] p = board.getPieces();
		
		int posx = pos.getX();
		int posy = pos.getY();
		
		//top right
		if(((posx-2) >= 0) && ((posy+1) < 8))
		{
			Piece temp = p[posx-2][posy+1];
			if(temp != null)
			{
				if(temp.isWhite != this.isWhite)
				{
					moves.add(new Position(posx-2, posy+1));
				}
			}
			else if(temp == null)
			{
				moves.add(new Position(posx-2, posy+1));
			}
		}
		
		if(((posx-1) >= 0) && ((posy+2) < 8))
		{
			Piece temp = p[posx-1][posy+2];
			if(temp != null)
			{
				if(temp.isWhite != this.isWhite)
				{
					moves.add(new Position(posx-1, posy+2));
				}
			}
			else if(temp == null)
			{
				moves.add(new Position(posx-1, posy+2));
			}
		}
		
		//top left
		if(((posx-2) >= 0) && ((posy-1) >= 0))
		{
			Piece temp = p[posx-2][posy-1];
			if(temp != null)
			{
				if(temp.isWhite != this.isWhite)
				{
					moves.add(new Position(posx-2, posy-1));
				}
			}
			else if(temp == null)
			{
				moves.add(new Position(posx-2, posy-1));
			}
		}
		
		if(((posx-1) >= 0) && ((posy-2) >= 0))
		{
			Piece temp = p[posx-1][posy-2];
			if(temp != null)
			{
				if(temp.isWhite != this.isWhite)
				{
					moves.add(new Position(posx-1, posy-2));
				}
			}
			else if(temp == null)
			{
				moves.add(new Position(posx-1, posy-2));
			}
		}
		
		//bottom left
		if(((posx+2) < 8) && ((posy-1) >= 0))
		{
			Piece temp = p[posx+2][posy-1];
			if(temp != null)
			{
				if(temp.isWhite != this.isWhite)
				{
					moves.add(new Position(posx+2, posy-1));
				}
			}
			else if(temp == null)
			{
				moves.add(new Position(posx+2, posy-1));
			}
		}
		
		if(((posx+1) < 8) && ((posy-2) >= 0))
		{
			Piece temp = p[posx+1][posy-2];
			if(temp != null)
			{
				if(temp.isWhite != this.isWhite)
				{
					moves.add(new Position(posx+1, posy-2));
				}
			}
			else if(temp == null)
			{
				moves.add(new Position(posx+1, posy-2));
			}
		}
		
		//bottom right
		if(((posx+2) < 8) && ((posy+1) < 8))
		{
			Piece temp = p[posx+2][posy+1];
			if(temp != null)
			{
				if(temp.isWhite != this.isWhite)
				{
					moves.add(new Position(posx+2, posy+1));
				}
			}
			else if(temp == null)
			{
				moves.add(new Position(posx+2, posy+1));
			}
		}
		
		if(((posx+1) < 8) && ((posy+2) < 8))
		{
			Piece temp = p[posx+1][posy+2];
			if(temp != null)
			{
				if(temp.isWhite != this.isWhite)
				{
					moves.add(new Position(posx+1, posy+2));
				}
			}
			else if(temp == null)
			{
				moves.add(new Position(posx+1, posy+2));
			}
		}

		
	}
	
	

}
