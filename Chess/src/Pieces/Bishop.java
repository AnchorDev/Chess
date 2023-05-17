package Pieces;
import Logic.*;

public class Bishop extends Piece{
	public Bishop(int x, int y, int id, Side pieceColor) {
		super(x, y, id, pieceColor, PieceType.BISHOP);
	}
	public void LegalMoves(char[][] board)
	{
		ResetMoves(moves);
		Loop(board, 1, 1);
		Loop(board, 1, -1);
		Loop(board, -1, 1);
		Loop(board, -1, -1);
	}
	public void Loop(char[][] board, int dirX, int dirY) 
	{
		for (int i = 1; i < 9; i++)
		{
			Move newMove = new Move(this.x + i * dirX, this.y + i * dirY);
			if (newMove.x >=0 && newMove.x < 8 && newMove.y >=0 && newMove.y < 8) 
			{
					if (board[newMove.x][newMove.y] == 'x')
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


