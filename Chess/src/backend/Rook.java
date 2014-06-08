package backend;

import java.util.ArrayList;

/**
 * 
 * @author Sangini Shah
 *
 */
public class Rook extends Piece 
{	
	public Rook(boolean white, int x, int y, Board b)
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
			return "wR";
		}
		else
		{
			return "bR";
		}
	}

	public void loadValidMoves() 
	{
		moves = new ArrayList<Position>();
		Piece[][] p = board.getPieces();
		
		//check right
		for(int y = pos.getY() + 1; y < 8; y++)
		{
			Piece temp = p[pos.getX()][y];
			if(temp != null)
			{
				if(temp.isWhite != this.isWhite)
				{
					moves.add(new Position(pos.getX(), y));
				}
				break;
			}
			else if(temp == null)
			{
				moves.add(new Position(pos.getX(), y));
			}
		}
		
		//check left
		for(int y = pos.getY() - 1; y >= 0; y--)
		{
			Piece temp = p[pos.getX()][y];
			if(temp != null)
			{
				if(temp.isWhite != this.isWhite)
				{
					moves.add(new Position(pos.getX(), y));
				}
				break;
			}
			else if(temp == null)
			{
				moves.add(new Position(pos.getX(), y));
			}
		}
		
		//check down
		for(int x = pos.getX() + 1; x < 8; x++)
		{
			Piece temp = p[x][pos.getY()];
			if(temp != null)
			{
				if(temp.isWhite != this.isWhite)
				{
					moves.add(new Position(x, pos.getY()));
				}
				break;
			}
			else if(temp == null)
			{
				moves.add(new Position(x, pos.getY()));
			}
		}
		
		//check down
		for(int x = pos.getX() - 1; x >= 0; x--)
		{
			Piece temp = p[x][pos.getY()];
			if(temp != null)
			{
				if(temp.isWhite != this.isWhite)
				{
					moves.add(new Position(x, pos.getY()));
				}
				break;
			}
			else if(temp == null)
			{
				moves.add(new Position(x, pos.getY()));
			}
		}		
		//castling?
	}

}
