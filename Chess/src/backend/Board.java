package backend;

import java.util.ArrayList;

/**
 * 
 * @author Sangini Shah
 *
 */
public class Board 
{	
	String[][] board = new String[9][9];
	Piece[][] pieces = new Piece[8][8];
	private King whiteK;
	private King blackK;
	public boolean draw1 = false, draw2 = false;
	public boolean enpassant = false;
	public Position passant = null;
	public Pawn pPass = null;
	ArrayList<Position> positions;
	
	public Board()
	{
		//black pieces
		pieces[0][0] = new Rook(false, 0, 0, this);
		pieces[0][1] = new Knight(false, 0, 1, this);
		pieces[0][2] = new Bishop(false, 0, 2, this);
		pieces[0][3] = new Queen(false, 0, 3, this);
		pieces[0][4] = new King(false, this);
		setBlackK((King) pieces[0][4]);
		pieces[0][5] = new Bishop(false, 0, 5, this);
		pieces[0][6] = new Knight(false, 0, 6, this);
		pieces[0][7] = new Rook(false, 0, 7, this);
		pieces[1][0] = new Pawn(false, 1, 0, this);
		pieces[1][1] = new Pawn(false, 1, 1, this);
		pieces[1][2] = new Pawn(false, 1, 2, this);
		pieces[1][3] = new Pawn(false, 1, 3, this);
		pieces[1][4] = new Pawn(false, 1, 4, this);
		pieces[1][5] = new Pawn(false, 1, 5, this);
		pieces[1][6] = new Pawn(false, 1, 6, this);
		pieces[1][7] = new Pawn(false, 1, 7, this);
		
		//white pieces
		pieces[7][0] = new Rook(true, 7, 0, this);
		pieces[7][1] = new Knight(true, 7, 1, this);
		pieces[7][2] = new Bishop(true, 7, 2, this);
		pieces[7][3] = new Queen(true, 7, 3, this);
		pieces[7][4] = new King(true, this);
		setWhiteK((King) pieces[7][4]);
		pieces[7][5] = new Bishop(true, 7, 5, this);
		pieces[7][6] = new Knight(true, 7, 6, this);
		pieces[7][7] = new Rook(true, 7, 7, this);
		pieces[6][0] = new Pawn(true, 6, 0, this);
		pieces[6][1] = new Pawn(true, 6, 1, this);
		pieces[6][2] = new Pawn(true, 6, 2, this);
		pieces[6][3] = new Pawn(true, 6, 3, this);
		pieces[6][4] = new Pawn(true, 6, 4, this);
		pieces[6][5] = new Pawn(true, 6, 5, this);
		pieces[6][6] = new Pawn(true, 6, 6, this);
		pieces[6][7] = new Pawn(true, 6, 7, this);
		
		drawBoard();	
	}
	
	private void drawBoard()
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if((i+j)%2 != 0)
				{
					board[i][j] = "##";
				}
				else
				{
					board[i][j] = "  ";
				}
				Piece p = pieces[i][j];
				if(p != null)
				{
					board[i][j] = p.toString();
				}
			}
		}
		
		for(int x = 0; x < 9; x++)
		{
			board[x][8] = Integer.toString(8-x);
			char c = (char) ('a' + x);
			board[8][x] = " " + c;
		}
		board[8][8] = "  ";
	}
		
	public Piece getPiece(int x, char c)
	{
		return pieces[8-x][(int)c-97];
	}
	
	public Piece[][] getPieces()
	{
		return pieces;
	}
		
	public King getWhiteK() {
		return whiteK;
	}

	public void setWhiteK(King whiteK) {
		this.whiteK = whiteK;
	}

	public King getBlackK() {
		return blackK;
	}

	public void setBlackK(King blackK) {
		this.blackK = blackK;
	}
	
	public String toString()
	{
		String b = new String();
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				b += board[i][j];
				b += " ";
			}
			b+= "\n";
		}
		return b;
	}
	
	public void move(boolean white, String[] args)
	{
		if(args[0].equalsIgnoreCase("draw"))
		{
			if(draw1)
			{
				draw2 = true;
				return;
			}
			else
			{
				throw new IllegalArgumentException();
			}
		}
		draw1 = false;
		Piece p = getPiece(Integer.parseInt(args[0].charAt(1) + ""), args[0].charAt(0));
		p.loadValidMoves();
		if(p.isWhite != white)
		{
			throw new IllegalArgumentException();
		}
		if(enpassant)
		{
			enpassant = false;
			pPass = null;
			passant = null;
		}
		Position moveTo = new Position(8 - Integer.parseInt(args[1].charAt(1) + ""), (int)(args[1].charAt(0)-97));
		if(p.isValidMove(moveTo))
		{
			if(p.isWhite && whiteK.isChecked())
			{
				if(positions == null && !whiteK.moves.contains(moveTo))
				{
					throw new IllegalArgumentException();
				}
				else if(positions != null && !positions.contains(moveTo) && !whiteK.moves.contains(moveTo))
				{
					throw new IllegalArgumentException();
				}
			}
			if(!p.isWhite && blackK.isChecked())
			{
				if(positions == null && !blackK.moves.contains(moveTo))
				{
					throw new IllegalArgumentException();
				}
				else if(positions != null && !positions.contains(moveTo) && !blackK.moves.contains(moveTo))
				{
					throw new IllegalArgumentException();
				}
			}
			Position current = p.pos;
			if(p instanceof King)
			{
				if(moveTo.getY() == current.getY() + 2)
				{
					pieces[current.getX()][current.getY()] = null;
					p.move(moveTo);
					pieces[moveTo.getX()][moveTo.getY()] = p;
					Piece rook = pieces[current.getX()][7];
					pieces[current.getX()][7] = null;
					rook.move(new Position(current.getX(),5));
					pieces[current.getX()][5] = rook;
					drawBoard();
				}
				else if(moveTo.getY() == current.getY() - 2)
				{
					pieces[current.getX()][current.getY()] = null;
					p.move(moveTo);
					pieces[moveTo.getX()][moveTo.getY()] = p;
					Piece rook = pieces[current.getX()][0];
					pieces[current.getX()][0] = null;
					rook.move(new Position(current.getX(),3));
					pieces[current.getX()][3] = rook;
					drawBoard();
				}
				else
				{
					pieces[current.getX()][current.getY()] = null;

					p.move(moveTo);
					pieces[moveTo.getX()][moveTo.getY()] = p;
					drawBoard();
				}
				if(p.isWhite)
				{
					setWhiteK((King)p);
				}
				else
				{
					setBlackK((King)p);
				}
			}
			else if(p instanceof Pawn)
			{
				boolean moved = false;
				if(moveTo.getX() == 0 || moveTo.getX() == 7)
				{
					if(args.length == 3)
					{
						String type = args[2];
						if(type.equalsIgnoreCase("N"))
						{
							pieces[current.getX()][current.getY()] = null;
							pieces[moveTo.getX()][moveTo.getY()] = new Knight(p.isWhite, moveTo.getX(), moveTo.getY(), this);
							drawBoard();
							moved = true;
						}
						else if(type.equalsIgnoreCase("R"))
						{
							pieces[current.getX()][current.getY()] = null;
							pieces[moveTo.getX()][moveTo.getY()] = new Rook(p.isWhite, moveTo.getX(), moveTo.getY(), this);
							drawBoard();
							moved = true;
						}
						else if(type.equalsIgnoreCase("B"))
						{
							pieces[current.getX()][current.getY()] = null;
							pieces[moveTo.getX()][moveTo.getY()] = new Bishop(p.isWhite, moveTo.getX(), moveTo.getY(), this);
							drawBoard();
							moved = true;
						}
						else if(type.equalsIgnoreCase("Q") || type.equalsIgnoreCase("draw?"))
						{
							pieces[current.getX()][current.getY()] = null;
							pieces[moveTo.getX()][moveTo.getY()] = new Queen(p.isWhite, moveTo.getX(), moveTo.getY(), this);
							drawBoard();
							moved = true;
						}
						else
						{
							throw new IllegalArgumentException();
						}
					}
					else
					{
						pieces[current.getX()][current.getY()] = null;
						pieces[moveTo.getX()][moveTo.getY()] = new Queen(p.isWhite, moveTo.getX(), moveTo.getY(), this);
						drawBoard();
						moved = true;
					}
				}
				if(moveTo.getX() == current.getX() + 2 || moveTo.getX() == current.getX() - 2)
				{
					enpassant = true;
					pPass = (Pawn) p;
					passant = new Position((current.getX() + moveTo.getX())/2, current.getY());
					pieces[current.getX()][current.getY()] = null;
					p.move(moveTo);
					pieces[moveTo.getX()][moveTo.getY()] = p;
					drawBoard();
					moved = true;
				}
				if(pieces[moveTo.getX()][moveTo.getY()] == null && moveTo.getY() != current.getY())
				{
					pieces[current.getX()][current.getY()] = null;
					p.move(moveTo);
					pieces[moveTo.getX()][moveTo.getY()] = p;
					pieces[current.getX()][moveTo.getY()] = null;
					drawBoard();
					moved = true;
				}
				if(!moved)
				{
					pieces[current.getX()][current.getY()] = null;
					p.move(moveTo);
					pieces[moveTo.getX()][moveTo.getY()] = p;
					drawBoard();
				}
			}
			else
			{
				pieces[current.getX()][current.getY()] = null;
				p.move(moveTo);
				pieces[moveTo.getX()][moveTo.getY()] = p;
				drawBoard();
			}
			if(args.length == 3 && args[2].equalsIgnoreCase("draw?"))
			{
				draw1 = true;
			}
			boolean check = checkForCheck(p);
			if(!check)
			{
				checkForStaleMate(p.isWhite);
			}
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	public boolean checkForCheck(Piece p)
	{
		if(p.isWhite)
		{
			getBlackK().setChecked(false);
			for(Piece[] row: pieces)
			{
				for(Piece piece: row)
				{
					if(piece != null && (piece.isWhite == p.isWhite))
					{
						piece.loadValidMoves();
						if(piece.moves.contains(getBlackK().pos))
						{
							getBlackK().setChecked(true);
							checkforCheckMate(getBlackK(), p);
							return true;
						}
					}
				}
			}
			return false;
		}
		else
		{
			getWhiteK().setChecked(false);
			for(Piece[] row: pieces)
			{
				for(Piece piece: row)
				{
					if(piece != null && (piece.isWhite == p.isWhite))
					{
						piece.loadValidMoves();
						if(piece.moves.contains(getWhiteK().pos))
						{
							getWhiteK().setChecked(true);
							checkforCheckMate(getWhiteK(), p);
							return true;
						}
					}
				}
			}
			return false;
		}
	}
	
	public boolean checkforCheckMate(King king, Piece checker)
	{
		king.loadValidMoves();
		if(king.getnoMoves())
		{
			positions = new ArrayList<Position>();
			if(checker.pos.getX() == king.pos.getX())
			{
				if(checker.pos.getY()<king.pos.getY())
				{
					for(int y = checker.pos.getY(); y < king.pos.getY(); y++)
					{
						positions.add(new Position(checker.pos.getX(), y));
					}
				}
				else
				{
					for(int y = king.pos.getY() + 1; y <= checker.pos.getY(); y++)
					{
						positions.add(new Position(checker.pos.getX(), y));
					}
				}
			}
			else if(checker.pos.getY() == king.pos.getY())
			{
				if(checker.pos.getX()<king.pos.getX())
				{
					for(int x = checker.pos.getX(); x < king.pos.getX(); x++)
					{
						positions.add(new Position(x, checker.pos.getY()));
					}
				}
				else
				{
					for(int x = king.pos.getX() + 1; x <= checker.pos.getX(); x++)
					{
						positions.add(new Position(x, checker.pos.getY()));
					}
				}
			}
			else if(checker.pos.getY() > king.pos.getY() && !(checker instanceof Knight))
			{
				if(checker.pos.getX() < king.pos.getX())
				{
					for(int x = king.pos.getX() - 1, y = king.pos.getY() + 1; x >= checker.pos.getX() && y <= checker.pos.getY(); x--, y++)
					{
						positions.add(new Position(x,y));
					}
				}
				else
				{
					for(int x = king.pos.getX() + 1, y = king.pos.getY() + 1; x <= checker.pos.getX() && y <= checker.pos.getY(); x++, y++)
					{
						positions.add(new Position(x,y));
					}
				}
			}
			else if(checker.pos.getY() < king.pos.getY() && !(checker instanceof Knight))
			{
				if(checker.pos.getX() < king.pos.getX())
				{
					for(int x = king.pos.getX() - 1, y = king.pos.getY() - 1; x >= checker.pos.getX() && y >= checker.pos.getY(); x--, y--)
					{
						positions.add(new Position(x,y));
					}
				}
				else
				{
					for(int x = king.pos.getX() + 1, y = king.pos.getY() - 1; x < checker.pos.getX() && y >= checker.pos.getY(); x++, y--)
					{
						positions.add(new Position(x,y));
					}
				}
			}
			else
			{
				positions.add(new Position(checker.pos.getX(), checker.pos.getY()));
			}
			for(Piece[] row: pieces)
			{
				for(Piece current: row)
				{
					if(current != null && (current.isWhite == king.isWhite))
					{
						current.loadValidMoves();
						for(Position pos: positions)
						{
							if(current.moves.contains(pos))
							{
								king.setCheckmate(false);
								return false;
							}
						}
					}
				}
			}
			king.setCheckmate(true);
			return true;	
		}
		king.setCheckmate(false);
		return false;
	}
	
	public boolean checkForStaleMate(boolean white)
	{
		if(white)
		{
			blackK.loadValidMoves();
			return blackK.stalemate;
		}
		else
		{
			whiteK.loadValidMoves();
			return whiteK.stalemate;
		}
	}

}

