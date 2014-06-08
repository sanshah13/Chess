package frontend;
import java.util.Scanner;

import backend.*;
/**
 * 
 * @author Sangini Shah
 *
 */
public class Chess 
{
	public static void main(String[] args) 
	{
		Board board = new Board();
		Scanner scan = new Scanner(System.in);
		
		System.out.println(board);
		
		boolean gameOver = false, whiteTurn = true;
		
		while(!gameOver)
		{
			String input = null;
			if(whiteTurn)
			{
				System.out.print("White's move: ");
				input = scan.nextLine();
				try
				{
					if(input.equalsIgnoreCase("resign"))
					{
						gameOver = true;
						System.out.println("\nBlack wins");
					}
					else
					{
						board.move(whiteTurn, input.split(" "));
						System.out.println();
						if(board.draw2)
						{
							gameOver = true;
							System.out.println("Draw");
						}
						else
						{
							System.out.println(board);
							if(board.getBlackK().isCheckmate())
							{
								gameOver = true;
								System.out.println("Checkmate\nWhite wins");
							}
							else if(board.getBlackK().isChecked())
							{
								System.out.println("Check\n");
							}
							else if(board.getBlackK().stalemate)
							{
								gameOver = true;
								System.out.println("Stalemate\nDraw");
							}
							whiteTurn = false;
						}
					}
				}
				catch(Exception e)
				{
					System.out.println("Illegal move, try again\n");
				}
			}
			else
			{
				System.out.print("Black's move: ");
				input = scan.nextLine();
				try
				{
					if(input.equalsIgnoreCase("resign"))
					{
						gameOver = true;
						System.out.println("\nWhite wins");
					}
					else
					{
						board.move(whiteTurn, input.split(" "));
						System.out.println();
						if(board.draw2)
						{
							gameOver = true;
							System.out.println("Draw");
						}
						else
						{
							System.out.println(board);
							if(board.getWhiteK().isCheckmate())
							{
								gameOver = true;
								System.out.println("Checkmate\nBlack wins");
							}
							else if(board.getWhiteK().isChecked())
							{
								System.out.println("Check\n");
							}
							else if(board.getWhiteK().stalemate)
							{
								gameOver = true;
								System.out.println("Stalemate\nDraw");
							}
							whiteTurn = true;
						}
					}
				}
				catch(Exception e)
				{
					System.out.println("Illegal move, try again\n");
				}
			}	
		}
		scan.close();
	}

}
