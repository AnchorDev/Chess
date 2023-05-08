package Pieces;
import application.Main.color;

public class King extends Pawn{
	public King(int x, int y, int id, color pieceColor, String name, String filename) {
		super(x, y, id, pieceColor, name, filename);
	}
	public void LegalMoves(int[][] board)
	{
		ResetMoves(moves);
		for (int i = -1; i < 2; i++) 
		{
			Loop(board, 1, i);
			Loop(board, -1, i);
		}
		Loop(board, 0, -1);
		Loop(board, 0, 1);

	}
	public void Loop(int[][] board, int dirX, int dirY) 
	{
		Move newMove = new Move(this.x + dirX, this.y + dirY);
		if (newMove.x >=0 && newMove.x < 8 && newMove.y >=0 && newMove.y < 8) 
		{
				if (board[newMove.x][newMove.y] == -1)
				{
					moves.add(new Move(newMove.x, newMove.y));
				}
		}
		
	}
}
