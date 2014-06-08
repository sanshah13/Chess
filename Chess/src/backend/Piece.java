package backend;

import java.util.ArrayList;

/**
 * 
 * @author Sangini Shah
 *
 */
public abstract class Piece
{
	Position pos;
	boolean isWhite, hasMoved;
	Board board;
	ArrayList<Position> moves;
	
	public void move(int x, int y)
	{
		pos = new Position(x,y);
		hasMoved = true;
	}
	
	public void move(Position p)
	{
		pos = p;
		hasMoved = true;
	}
	
	public abstract void loadValidMoves();
	
	public boolean isValidMove(int x, int y)
	{
		Position p = new Position(x,y);
		return moves.contains(p);
	}
	
	public boolean isValidMove(Position p)
	{
		return moves.contains(p);
	}
}
