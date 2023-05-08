package Pieces;
import application.Main.color;

public class Knight extends Pawn{
	public Knight(int x, int y, int id, color pieceColor, String name, String filename) {
		super(x, y, id, pieceColor, name, filename);
	}
	public void LegalMoves(int[][] board)
	{
		ResetMoves(moves);
		Loop(board, 1);
		Loop(board, -1);

	}
	public void Loop(int[][] board, int dirX) 
	{
		Move newMove[] = new Move[4];
		newMove[0]= new Move(this.x + (2*dirX), this.y + 1);
		newMove[1]= new Move(this.x + (2*dirX), this.y - 1);
		newMove[2]= new Move(this.x + 1, this.y + (2*dirX));
		newMove[3]= new Move(this.x - 1, this.y + (2*dirX));
		for (int i = 0; i < newMove.length; i++) 
		{
			if (newMove[i].x >=0 && newMove[i].x < 8 && newMove[i].y >=0 && newMove[i].y < 8) 
			{
				if (board[newMove[i].x][newMove[i].y] == -1)
				{
					moves.add(newMove[i]);
				}
			}
		}
		
	}
}

