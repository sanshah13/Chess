package backend;

import java.util.ArrayList;

/**
 * 
 * @author Sangini Shah
 *
 */
public class Pawn extends Piece
{
	public Pawn(boolean white, int x, int y, Board b)
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
			return "wp";
		}
		else
		{
			return "bp";
		}
	}

	public void loadValidMoves()
	{
		moves = new ArrayList<Position>();
		Piece[][] p = board.getPieces();
		
		if(isWhite)
		{
			if(pos.getX() - 1 >= 0)
			{
				Piece temp = p[pos.getX() - 1][pos.getY()];
				if(temp == null)
				{
					moves.add(new Position(pos.getX()-1, pos.getY()));
					if(pos.getX() == 6)
					{
						if(p[pos.getX()-2][pos.getY()] == null)
						{
							moves.add(new Position(pos.getX() - 2, pos.getY()));
						}
					}
				}
			}
			
			if(pos.getX() - 1 >= 0 && pos.getY() + 1 < 8)
			{
				Piece temp = p[pos.getX()-1][pos.getY()+1];
				Position tempPos = new Position(pos.getX()-1, pos.getY()+1);
				if(temp != null && !temp.isWhite || board.enpassant && board.passant.equals(tempPos) && !board.pPass.isWhite)
				{
					moves.add(new Position(pos.getX() -1, pos.getY()+1));
				}
			}
			
			if(pos.getX() - 1 >= 0 && pos.getY() - 1 >= 0)
			{
				Piece temp = p[pos.getX()-1][pos.getY()-1];
				Position tempPos = new Position(pos.getX()-1, pos.getY()-1);
				if(temp != null && !temp.isWhite || board.enpassant && board.passant.equals(tempPos) && !board.pPass.isWhite)
				{
					moves.add(new Position(pos.getX()-1, pos.getY()-1));
				}
			}
		}
		else
		{
			if(pos.getX() + 1 < 8)
			{
				Piece temp = p[pos.getX() + 1][pos.getY()];
				if(temp == null)
				{
					moves.add(new Position(pos.getX()+1, pos.getY()));
					if(pos.getX() == 1)
					{
						if(p[pos.getX()+2][pos.getY()] == null)
						{
							moves.add(new Position(pos.getX() + 2, pos.getY()));
						}
					}
				}
			}
			
			if(pos.getX() + 1 < 8 && pos.getY() + 1 < 8)
			{
				Piece temp = p[pos.getX()+1][pos.getY()+1];
				Position tempPos = new Position(pos.getX()+1, pos.getY()+1);
				if(temp != null && temp.isWhite || board.enpassant && board.passant.equals(tempPos) && board.pPass.isWhite)
				{
					moves.add(new Position(pos.getX()+1, pos.getY()+1));
				}
			}
			
			if(pos.getX() + 1 < 8 && pos.getY() - 1 >= 0)
			{
				Piece temp = p[pos.getX()+1][pos.getY()-1];
				Position tempPos = new Position(pos.getX()+1, pos.getY()-1);
				if(temp != null && temp.isWhite || board.enpassant && board.passant.equals(tempPos) && board.pPass.isWhite)
				{
					moves.add(new Position(pos.getX()+1, pos.getY()-1));
				}
			}
		}
	}
	
	

}
