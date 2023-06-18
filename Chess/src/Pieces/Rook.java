package Pieces;
import Logic.*;

public class Rook extends Piece{
	public Rook(int x, int y, int id, Side pieceColor) {
		super(x, y, id, pieceColor, PieceType.ROOK);
		castleable = true;
	}
	public void LegalMoves(char[][] board)
	{
		ResetMoves(moves);
		Loop(board, this.x, 1, true);
		Loop(board, this.x, -1, true);
		Loop(board, this.y, 1, false);
		Loop(board, this.y, -1, false);
	}
	/**
	 * Petla uzywana do liczenia legalnych ruchow
	 */
	public void Loop(char[][] board, int pos, int dir, boolean horizontal) 
	{
		for (int i = 1; i < 9; i++)
		{
			int newMove = pos + i * dir;
			if (newMove >=0 && newMove < 8) 
			{
				if(horizontal)
				{
					if (board[newMove][this.y] == 'x')
					{
						moves.add(new Move(newMove, this.y));
					}
					else
					{
						Side side = Side.whatSide(board[newMove][this.y]);
						if (side != this.getPieceSide()) 
						{
							moves.add(new Move(newMove, this.y));
							break;
						}
						else
						{
							break;
						}
					}
					
				}
				else
				{
					if (board[this.x][newMove] == 'x')
					{
						moves.add(new Move(this.x, newMove));
					}
					else
					{
						Side side = Side.whatSide(board[this.x][newMove]);
						if (side != this.getPieceSide()) 
						{
							moves.add(new Move(this.x, newMove));
							break;
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

}
