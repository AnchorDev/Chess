package Pieces;
import Logic.*;

public class King extends Piece{
	public King(int x, int y, int id, Side pieceColor) {
		super(x, y, id, pieceColor, PieceType.KING);
		castleable = true;
	}
	public void LegalMoves(char[][] board)
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
	/**
	 * Petla uzywana do liczenia legalnych ruchow
	 */
	public void Loop(char[][] board, int dirX, int dirY) 
	{
		Move newMove = new Move(this.x + dirX, this.y + dirY);
		if (newMove.x >=0 && newMove.x < 8 && newMove.y >=0 && newMove.y < 8) 
		{
				if (board[newMove.x][newMove.y] == 'x')
				{
					moves.add(new Move(newMove.x, newMove.y));
				}
				else
				{
					Side side = Side.whatSide(board[newMove.x][newMove.y]);
					if (side != this.getPieceSide()) 
					{
						moves.add(new Move(newMove.x, newMove.y));
					}
				}
		}
		
	}
}
