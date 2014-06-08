package backend;

import java.util.ArrayList;

/**
 * 
 * @author Sangini Shah
 *
 */
public class King extends Piece
{
	private boolean isChecked;
	private boolean checkmate;
	private boolean noMoves;
	public boolean stalemate;
	
	public King(boolean white, Board b)
	{
		isWhite = white;
		if(isWhite)
		{
			pos = new Position(7,4);
		}
		else
		{
			pos = new Position(0,4);
		}
		
		board = b; 
		loadValidMoves();
	}
	
	public King(boolean white, Position position, Board b)
	{
		isWhite = white;
		pos = position;
		board = b;
	}

	public String toString()
	{
		if(isWhite)
		{
			return "wK";
		}
		else
		{
			return "bK";
		}
	}

	public void loadValidMoves() 
	{
		setnoMoves(false);
		moves = new ArrayList<Position>();
		Piece[][] p = board.getPieces();
		
		for(int x = pos.getX()-1; x < pos.getX()+2; x++)
		{
			if(x <= 7 && x >= 0)
			{
				for(int y = pos.getY()-1; y < pos.getY()+2; y++)
				{
					if(y <= 7 && y >= 0)
					{
						Piece temp = p[x][y];
						if((temp != null && (temp.isWhite != this.isWhite))) 
						{
							moves.add(new Position(x,y));
						}
						else if(temp == null)
						{
							moves.add(new Position(x,y));
						}
					}
				}
			}
		}
		//add castling
		if(!hasMoved && !isChecked())
		{
			if(isWhite)
			{
				Piece temp1 = p[7][7];
				Piece temp2 = p[7][0];
				if(temp1 != null && temp1 instanceof Rook)
				{
					if(!temp1.hasMoved && p[7][5] == null && p[7][6] == null)
					{
						moves.add(new Position(7,6));
					}
				}
				if(temp2 != null && temp2 instanceof Rook)
				{
					if(!temp2.hasMoved && p[7][3] == null && p[7][2] == null && p[7][1] == null)
					{
						moves.add(new Position(7,2));
					}
				}
			}
			else
			{
				Piece temp1 = p[0][7];
				Piece temp2 = p[0][0];
				if(temp1 != null && temp1 instanceof Rook)
				{
					if(!temp1.hasMoved && p[0][5] == null && p[0][6] == null)
					{
						moves.add(new Position(0,6));
					}
				}
				if(temp2 != null && temp2 instanceof Rook)
				{
					if(!temp2.hasMoved && p[0][3] == null && p[0][2] == null && p[0][1] == null)
					{
						moves.add(new Position(0,2));
					}
				}
			}
		}
		
		int nummoves = moves.size();
		
		//avoid moving to check
		if(nummoves != 0)
		{
			ArrayList<Position> toRemove = new ArrayList<Position>();
			for(Position pos: moves)
			{
				for(Piece[] row: board.pieces)
				{
					for(Piece piece: row)
					{
						if(piece != null && (piece.isWhite != isWhite) && !(piece instanceof King))
						{
							Piece temp = null;
							boolean pawn= false;
							if(piece instanceof Pawn)
							{
								temp = board.pieces[pos.getX()][pos.getY()];
								board.pieces[pos.getX()][pos.getY()] = new King(isWhite, pos, board);
								pawn = true;
							}
							piece.loadValidMoves();
							if((pawn && piece.moves.contains(pos) && pos.getY() != piece.pos.getY()) || piece.moves.contains(pos))
							{
								toRemove.add(pos);
							}
							if(pawn)
							{
								board.pieces[pos.getX()][pos.getY()] = temp;
							}
						}
					}
				}
			}
			moves.removeAll(toRemove);
		}
		if(nummoves != 0 && moves.size() == 0)
		{
			setnoMoves(true);
			if(!isChecked)
			{
				stalemate = true;
			}
		}
	}

	public void setnoMoves(boolean b) {
		noMoves = b;
	}
	
	public boolean getnoMoves()
	{
		return noMoves;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public boolean isCheckmate() {
		return checkmate;
	}

	public void setCheckmate(boolean checkmate) {
		this.checkmate = checkmate;
	}
}
