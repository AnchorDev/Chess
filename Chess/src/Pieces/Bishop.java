package Pieces;
import application.Main.color;

public class Bishop extends Pawn{
	public Bishop(int x, int y, int id, color pieceColor, String name, String filename) {
		super(x, y, id, pieceColor, name, filename);
	}
	public void LegalMoves(int[][] board)
	{
		ResetMoves(moves);
		Loop(board, 1, 1);
		Loop(board, 1, -1);
		Loop(board, -1, 1);
		Loop(board, -1, -1);
	}
	public void Loop(int[][] board, int dirX, int dirY) 
	{
		for (int i = 1; i < 9; i++)
		{
			Move newMove = new Move(this.x + i * dirX, this.y + i * dirY);
			if (newMove.x >=0 && newMove.x < 8 && newMove.y >=0 && newMove.y < 8) 
			{
					if (board[newMove.x][newMove.y] == -1)
					{
						moves.add(newMove);
					}
					else
					{
						break;
					}
				}
			}
		}
	}


