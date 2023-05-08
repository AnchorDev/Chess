package Pieces;
import application.Main.color;

public class Rook extends Pawn{
	public Rook(int x, int y, int id, color pieceColor, String name, String filename) {
		super(x, y, id, pieceColor, name, filename);
	}
	public void LegalMoves(int[][] board)
	{
		ResetMoves(moves);
		Loop(board, this.x, 1, true);
		Loop(board, this.x, -1, true);
		Loop(board, this.y, 1, false);
		Loop(board, this.y, -1, false);
	}
	public void Loop(int[][] board, int pos, int dir, boolean horizontal) 
	{
		for (int i = 1; i < 9; i++)
		{
			int newMove = pos + i * dir;
			if (newMove >=0 && newMove < 8) 
			{
				if(horizontal)
				{
					if (board[newMove][this.y] == -1)
					{
						moves.add(new Move(newMove, this.y));
					}
					else
					{
						break;
					}
				}
				else
				{
					if (board[this.x][newMove] == -1)
					{
						moves.add(new Move(this.x, newMove));
					}
					else
					{
						break;
					}
				}
			}
		}
	}

}
