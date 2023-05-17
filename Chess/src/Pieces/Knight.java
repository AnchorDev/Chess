package Pieces;
import Logic.*;

public class Knight extends Piece{
	public Knight(int x, int y, int id, Side pieceColor) {
		super(x, y, id, pieceColor, PieceType.KNIGHT);
	}
	public void LegalMoves(char[][] board)
	{
		ResetMoves(moves);
		Loop(board, 1);
		Loop(board, -1);

	}
	public void Loop(char[][] board, int dirX) 
	{
		Move newMove[] = new Move[4];
		newMove[0] = new Move(this.x + (2*dirX), this.y + 1);
		newMove[1] = new Move(this.x + (2*dirX), this.y - 1);
		newMove[2] = new Move(this.x + 1, this.y + (2*dirX));
		newMove[3] = new Move(this.x - 1, this.y + (2*dirX));
		for (int i = 0; i < newMove.length; i++) 
		{
			if (newMove[i].x >=0 && newMove[i].x < 8 && newMove[i].y >=0 && newMove[i].y < 8) 
			{
				if (board[newMove[i].x][newMove[i].y] == 'x')
				{
					moves.add(newMove[i]);
				}
				else
				{
					Side side = Side.whatSide(board[newMove[i].x][newMove[i].y]);
					if (side != this.getPieceSide()) 
					{
						moves.add(newMove[i]);
					}
				}
			}
		}
		
	}
}

